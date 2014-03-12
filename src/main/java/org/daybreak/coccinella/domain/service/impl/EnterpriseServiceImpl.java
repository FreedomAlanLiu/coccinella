package org.daybreak.coccinella.domain.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.CrawlTask;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.CrawlTaskRepository;
import org.daybreak.coccinella.domain.repository.EnterpriseRepository;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.daybreak.coccinella.webmagic.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

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
    public Page<Enterprise> searchEnterprises(String province, String name, int page, int size) {
        AIC aic = aicRepository.findByProvince(province);
        Page<Enterprise> enterprises = enterpriseRepository.findByAicAndNameLike(aic, name, 
                new PageRequest(page, size));
        if (enterprises == null) {
            crawlEnterprises(aic);
        }
        return enterprises;
    }

    @Override
    public Enterprise crawlEnterprise() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void crawlEnterprises(AIC aic) {
        List<CrawlTask> crawlTasks = aic.getCrawlTaskList();
        for (CrawlTask task : crawlTasks) {
            Request request;
            if (task.getMethod() == CrawlTask.HttpMethod.GET) {
                request = new Request(task.getUrl());
            } else {
                request = new PostRequest(task.getUrl());
                String params = task.getParams();
                String[] paramArray = params.split(",");
                for (String nameValue : paramArray) {
                    String[] nameValueArray = nameValue.split("=");
                    if (nameValueArray.length == 2) {
                        ((PostRequest) request).addParam(nameValueArray[0].trim(), nameValueArray[1].trim());
                    }
                }
            }

            Spider spider = Spider.create(new PageProcessor() {

                @Override
                public void process(us.codecraft.webmagic.Page page) {

                }

                @Override
                public Site getSite() {
                    return Site.me();
                }

            }).addRequest(request);
        }
    }
}
