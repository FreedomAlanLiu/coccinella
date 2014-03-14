package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.AIC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alan
 */
@Repository
public interface AICRepository extends JpaRepository<AIC, Integer> {
    
    AIC findByName(String name);
    
    AIC findByProvince(String province);
}
