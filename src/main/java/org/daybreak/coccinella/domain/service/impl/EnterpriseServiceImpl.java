package org.daybreak.coccinella.domain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.CrawlTask;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.model.HtmlParser;
import org.daybreak.coccinella.domain.model.Parser;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.CrawlTaskRepository;
import org.daybreak.coccinella.domain.repository.EnterpriseRepository;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.daybreak.coccinella.webmagic.HttpClientPostDownloader;
import org.daybreak.coccinella.webmagic.PostRequest;
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

/**
 *
 * @author Alan
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    
    @Inject
    AICRepository aicRepository;
    
    @Inject
    CrawlTaskRepository crawlTaskRepository;
    
    @Inject
    EnterpriseRepository enterpriseRepository;
    
    @Override
    public Page<Enterprise> searchEnterprises(String province, String enterpriseName, int page, int size) {
        AIC aic = aicRepository.findByProvince(province);
        Page<Enterprise> enterprises = enterpriseRepository.findByAicAndNameLike(aic, "%" + enterpriseName + "%", 
                new PageRequest(page, size));
        if (enterprises == null) {
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
                    Set<Parser> parserSet = aic.getParserSet();
                    for (Parser parser : parserSet) {
                        if (parser instanceof HtmlParser) {
                            HtmlParser htmlParser = (HtmlParser) parser;
                            String xpath = htmlParser.getXpath();
                            String regex = htmlParser.getRegex();
                            Html html = page.getHtml();
                            if (StringUtils.isNotBlank(xpath)) {
                                html.xpath(xpath);
                            }
                            if (StringUtils.isNotBlank(regex)) {
                                html.regex(regex);
                            }
                            page.putField(htmlParser.getNameKey(), html.all());
                        }
                    }
                }

                @Override
                public Site getSite() {
                    return Site.me();
                }
                
            });
        
        List<CrawlTask> crawlTasks = aic.getCrawlTaskList();
        long priority = crawlTasks.size();
        for (final CrawlTask task : crawlTasks) {
            String params = task.getParams();
            
            // 无参数的请求
            if (StringUtils.isEmpty(params)) {
                Request request;
                if (task.getMethod() == CrawlTask.HttpMethod.GET) {
                    request = new Request(task.getUrl());
                } else {
                    request = new PostRequest(task.getUrl());
                }
                request.setPriority(priority);
                spider.addRequest(request);
            }
            
            // 带参数的请求
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
                                Request request;
                                if (task.getMethod() == CrawlTask.HttpMethod.GET) {
                                    request = new Request(task.getUrl());
                                } else {
                                    request = new PostRequest(task.getUrl());
                                    ((PostRequest) request).addParam(name, v.toString());
                                }
                                request.setPriority(priority);
                                spider.addRequest(request);
                            }
                        } else {
                            Request request;
                            if (task.getMethod() == CrawlTask.HttpMethod.GET) {
                                request = new Request(task.getUrl());
                            } else {
                                request = new PostRequest(task.getUrl());
                                ((PostRequest) request).addParam(name, it.toString());
                            }
                            request.setPriority(priority);
                            spider.addRequest(request);
                        }
                    }
                }
            }
            
            if (task.getMethod() == CrawlTask.HttpMethod.POST) {
                spider.setDownloader(new HttpClientPostDownloader());
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
