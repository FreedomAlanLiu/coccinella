package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.AIC;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alan
 */
public interface AICRepository extends JpaRepository<AIC, Integer> {
    
    AIC findByName(String name);
    
    AIC findByProvince(String province);
}
