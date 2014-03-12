package org.daybreak.coccinella.domain.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.CrawlTaskRepository;
import org.daybreak.coccinella.domain.repository.EnterpriseRepository;
import org.daybreak.coccinella.domain.service.EnterpriseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
            
        }
        return enterprises;
    }

    @Override
    public Enterprise crawlEnterprise() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private List<Enterprise> crawlEnterprises() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
