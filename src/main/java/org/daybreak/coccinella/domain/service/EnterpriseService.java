package org.daybreak.coccinella.domain.service;

import org.daybreak.coccinella.domain.model.Enterprise;
import org.springframework.data.domain.Page;

/**
 *
 * @author Alan
 */
public interface EnterpriseService {
    
    public Page<Enterprise> searchEnterprises(String province, String name, int page, int size);
    
    public Enterprise crawlEnterprise();
}
