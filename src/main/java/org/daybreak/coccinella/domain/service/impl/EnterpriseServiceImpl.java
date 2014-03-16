package org.daybreak.coccinella.domain.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.model.Parser;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.EnterpriseRepository;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.daybreak.coccinella.webmagic.CrawlerDownloader;
import org.daybreak.coccinella.webmagic.CrawlerPage;
import org.daybreak.coccinella.webmagic.CrawlerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 *
 * @author Alan
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    
    @Inject
    AICRepository aicRepository;
    
    @Inject
    EnterpriseRepository enterpriseRepository;
    
    @Override
    public Page<Enterprise> searchEnterprises(String province, String enterpriseName, int page, int size) {
        AIC aic = aicRepository.findByProvince(province);
        Page<Enterprise> enterprises = enterpriseRepository.findByAicAndNameLike(aic, "%" + enterpriseName + "%", 
                new PageRequest(page, size));
        if (enterprises == null || !enterprises.hasContent()) {
            crawlEnterprises(aic, enterpriseName);
            enterpriseRepository.findByAicAndNameLike(aic, "%" + enterpriseName + "%", 
                new PageRequest(page, size));
        }
        return enterprises;
    }
    
    @Override
    public void crawlEnterprises(String province, String enterpriseName) {
        AIC aic = aicRepository.findByProvince(province);
        crawlEnterprises(aic, enterpriseName);
    }

    /**
     * 抓取企业信息
     * 
     * @param aic
     * @param enterpriseName 
     */
    private void crawlEnterprises(final AIC aic, String enterpriseName) {
        final Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(Enterprise.SEARCH_ENTERPRISE_NAME_KEY, enterpriseName);
        
        Spider spider = Spider.create(new PageProcessor() {
            
                @Override
                public void process(us.codecraft.webmagic.Page page) {
                    if (page instanceof CrawlerPage) {
                        CrawlerPage crawlerPage = (CrawlerPage) page;
                        List<Parser> parsers = crawlerPage.getCrawler().getParsers();
                        for (Parser parser : parsers) {
                            String xpath = parser.getXpath();
                            String regex = parser.getRegex();

                            Selectable html = page.getHtml();
                            if (StringUtils.isNotBlank(xpath)) {
                                html = html.xpath(xpath);
                            }
                            if (StringUtils.isNotBlank(regex)) {
                                html = html.regex(regex);
                            }
                            page.putField(parser.getNameKey(), html.all());
                        }
                    }
                }

                @Override
                public Site getSite() {
                    Site me = Site.me();
                    me.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
                    return Site.me();
                }
                
            }).setDownloader(new CrawlerDownloader());
        
        List<Crawler> crawlers = aic.getCrawlers();
        long priority = crawlers.size();
        for (final Crawler crawler : crawlers) {
            String params = crawler.getParams();
            
            if (StringUtils.isEmpty(params)) {
                // 无参数的请求
                CrawlerRequest request = new CrawlerRequest(crawler);
                request.setPriority(priority);
                spider.addRequest(request);
            } else {
                // 带参数的请求
                CrawlerRequest request = null;
                String[] paramArray = params.split(",");
                for (String nameValue : paramArray) {
                    String[] nameValueArray = nameValue.split("=");
                    if (nameValueArray.length == 2) {
                        String name = nameValueArray[0].trim();
                        String value = nameValueArray[1].trim();
                        if (value.startsWith("$")) {
                            Object it = resultMap.get((value.substring(1)));
                            if (it instanceof Iterable) {
                                for (Object v : (Iterable) it) {
                                    if (request == null) {
                                        request = new CrawlerRequest(crawler);
                                        request.setPriority(priority);
                                    }
                                    request.addParam(name, v.toString());
                                }
                            } else {
                                if (request == null) {
                                    request = new CrawlerRequest(crawler);
                                    request.setPriority(priority);
                                }
                                request.addParam(name, it.toString());
                            }
                        } else {
                            if (request == null) {
                                request = new CrawlerRequest(crawler);
                                request.setPriority(priority);
                            }
                            request.addParam(name, value);
                        }
                    }
                }
                if (request == null) {
                    return;
                }
                spider.addRequest(request);
            }
            
            spider.addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    Map<String, Object> itemMap = resultItems.getAll();
                    Object fullEnterpriseNameObj = itemMap.get(Enterprise.NAME_KEY);
                    if (fullEnterpriseNameObj != null) {
                        Enterprise enterprise = enterpriseRepository.findByAicAndName(aic, fullEnterpriseNameObj.toString());
                        if (enterprise == null) {
                            enterprise = new Enterprise();
                            enterprise.setName(fullEnterpriseNameObj.toString());
                            enterprise.setAic(aic);
                            enterprise.setCreateDate(new Date());
                        }
                        enterprise.setAddress(itemMap.get(Enterprise.ADDRESS_KEY).toString());
                        enterprise.setAgent(itemMap.get(Enterprise.AGENT_KEY).toString());
                        enterprise.setAgent(itemMap.get(Enterprise.AGENT_KEY).toString());
                        enterprise.setApprovalDate(itemMap.get(Enterprise.APPROVAL_DATE_KEY).toString());
                        enterprise.setCategory(itemMap.get(Enterprise.CATEGORY_KEY).toString());
                        enterprise.setCurrentStatus(itemMap.get(Enterprise.CURRENT_STATUS_KEY).toString());
                        enterprise.setEstablishmentDate(itemMap.get(Enterprise.ESTABLISHMENT_DATE_KEY).toString());
                        enterprise.setPostalCode(itemMap.get(Enterprise.POSTAL_CODE_KEY).toString());
                        enterprise.setRegisteredCapital(itemMap.get(Enterprise.REGISTERED_CAPITAL_KEY).toString());
                        enterprise.setRegistrationAuthority(itemMap.get(Enterprise.REGISTRATION_AUTHORITY_KEY).toString());
                        enterprise.setRegistrationNumber(itemMap.get(Enterprise.REGISTRATION_NUMBER_KEY).toString());
                        enterprise.setScope(itemMap.get(Enterprise.SCOPE_KEY).toString());
                        enterprise.setType(itemMap.get(Enterprise.TYPE_KEY).toString());
                        enterprise.setUpdateDate(new Date());
                        // 保存到数据库
                        enterpriseRepository.save(enterprise);
                    }
                    resultMap.putAll(itemMap);
                }
            }).run();
            
            // 优先级递减
            priority--;
        }
    }
}
