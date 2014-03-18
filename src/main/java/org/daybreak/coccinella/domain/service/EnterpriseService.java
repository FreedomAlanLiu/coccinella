package org.daybreak.coccinella.domain.service;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.springframework.data.domain.Page;

/**
 *
 * @author Alan
 */
public interface EnterpriseService {
    
    public Page<Enterprise> searchEnterprises(String province, String name, boolean cache, int page, int size);
    
    public void crawlEnterprises(String province, String enterpriseName);
}
