package org.daybreak.coccinella.domain.service.impl;

import java.util.*;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.model.Parser;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.EnterpriseRepository;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.daybreak.coccinella.webmagic.CrawlerDownloader;
import org.daybreak.coccinella.webmagic.CrawlerPage;
import org.daybreak.coccinella.webmagic.CrawlerQueueScheduler;
import org.daybreak.coccinella.webmagic.CrawlerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 *
 * @author Alan
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    protected static final Logger logger = LoggerFactory
            .getLogger(EnterpriseServiceImpl.class);
    
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
                            String css = parser.getCss();
                            String xpath = parser.getXpath();
                            String regex = parser.getRegex();

                            boolean filter = false;
                            Selectable selectable = page.getHtml();
                            logger.debug(selectable.toString());

                            if (StringUtils.isNotBlank(css)) {
                                if (selectable != null) {
                                    selectable = selectable.css(css);
                                    filter = true;
                                }
                            }
                            if (StringUtils.isNotBlank(xpath)) {
                                if (selectable != null) {
                                    selectable = selectable.xpath(xpath);
                                    filter = true;
                                }
                            }
                            if (StringUtils.isNotBlank(regex)) {
                                if (selectable != null) {
                                    selectable = selectable.regex(regex);
                                    filter = true;
                                }
                            }
                            
                            if (filter && selectable != null) {
                                page.putField(parser.getNameKey(), selectable.all());
                            }
                        }
                    }
                }

                @Override
                public Site getSite() {
                    Site me = Site.me();
                    me.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
                    return me;
                }
                
            }).setDownloader(new CrawlerDownloader()).setScheduler(new CrawlerQueueScheduler());
        
        List<Crawler> crawlers = aic.getCrawlers();
        long priority = crawlers.size();
        for (final Crawler crawler : crawlers) {
            String params = crawler.getParams();
            
            if (StringUtils.isEmpty(params)) {
                // 无参数的请求
                CrawlerRequest request = new CrawlerRequest(crawler);
                request.setPriority(priority);
                request.setCanRepeat(true);
                spider.addRequest(request);
            } else {
                // 带参数的请求
                List<CrawlerRequest> requestList = new ArrayList<>();
                Map<String, String> paramsMap = new HashMap<>();
                String[] paramArray = params.split(",");
                for (String nameValue : paramArray) {
                    String[] nameValueArray = nameValue.split("=");
                    if (nameValueArray.length == 2) {
                        String name = nameValueArray[0].trim();
                        String value = nameValueArray[1].trim();
                        if (value.startsWith("$")) {
                            Object it = resultMap.get((value.substring(1)));
                            if (it instanceof Iterable) {
                                boolean requestListEmpty = requestList.isEmpty();
                                int index = 0;
                                for (Object v : (Iterable) it) {
                                    if (requestListEmpty) {
                                        CrawlerRequest request = new CrawlerRequest(crawler);
                                        request.setPriority(priority);
                                        request.setCanRepeat(true);
                                        request.addParam(name, v.toString());
                                        requestList.add(request);
                                    } else {
                                        if (requestList.size() > index + 1) {
                                            CrawlerRequest request = requestList.get(index);
                                            if (request != null) {
                                                request.addParam(name, v.toString());
                                            }
                                        }
                                    }
                                    index++;
                                }
                            } else {
                                paramsMap.put(name, it.toString());
                            }
                        } else {
                            paramsMap.put(name, value);
                        }
                    }
                }

                if (requestList.isEmpty()) {
                    CrawlerRequest request = new CrawlerRequest(crawler);
                    request.setPriority(priority);
                    request.setCanRepeat(true);
                    for(Map.Entry<String, String> paramEntry : paramsMap.entrySet()) {
                        request.addParam(paramEntry.getKey(), paramEntry.getValue());
                    }
                    spider.addRequest(request);
                } else {
                    for (CrawlerRequest request : requestList) {
                        for(Map.Entry<String, String> paramEntry : paramsMap.entrySet()) {
                            request.addParam(paramEntry.getKey(), paramEntry.getValue());
                        }
                        spider.addRequest(request);
                    }
                }
            }
            
            spider.addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    Map<String, Object> itemMap = resultItems.getAll();
                    Object fullEnterpriseNameObj = itemMap.get(Enterprise.NAME_KEY);
                    String fullEnterpriseName = getFieldValue(fullEnterpriseNameObj);
                    if (StringUtils.isNotEmpty(fullEnterpriseName)) {
                        Enterprise enterprise = enterpriseRepository.findByAicAndName(aic, fullEnterpriseName);
                        if (enterprise == null) {
                            enterprise = new Enterprise();
                            enterprise.setName(fullEnterpriseName);
                            enterprise.setAic(aic);
                            enterprise.setCreateDate(new Date());
                        }
                        enterprise.setAddress(getFieldValue(itemMap.get(Enterprise.ADDRESS_KEY)));
                        enterprise.setAgent(getFieldValue(itemMap.get(Enterprise.AGENT_KEY)));
                        enterprise.setAgent(getFieldValue(itemMap.get(Enterprise.AGENT_KEY)));
                        enterprise.setApprovalDate(getFieldValue(itemMap.get(Enterprise.APPROVAL_DATE_KEY)));
                        enterprise.setCategory(getFieldValue(itemMap.get(Enterprise.CATEGORY_KEY)));
                        enterprise.setCurrentStatus(getFieldValue(itemMap.get(Enterprise.CURRENT_STATUS_KEY)));
                        enterprise.setEstablishmentDate(getFieldValue(itemMap.get(Enterprise.ESTABLISHMENT_DATE_KEY)));
                        enterprise.setPostalCode(getFieldValue(itemMap.get(Enterprise.POSTAL_CODE_KEY)));
                        enterprise.setRegisteredCapital(getFieldValue(itemMap.get(Enterprise.REGISTERED_CAPITAL_KEY)));
                        enterprise.setRegistrationAuthority(getFieldValue(itemMap.get(Enterprise.REGISTRATION_AUTHORITY_KEY)));
                        enterprise.setRegistrationNumber(getFieldValue(itemMap.get(Enterprise.REGISTRATION_NUMBER_KEY)));
                        enterprise.setScope(getFieldValue(itemMap.get(Enterprise.SCOPE_KEY)));
                        enterprise.setType(getFieldValue(itemMap.get(Enterprise.TYPE_KEY)));
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
    
    private String getFieldValue(Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof Iterable) {
            Iterator it = ((Iterable) o).iterator();
            if (it.hasNext()) {
                return it.next().toString();
            } else {
                return "";
            }
        }
        return o.toString();
    }
}
