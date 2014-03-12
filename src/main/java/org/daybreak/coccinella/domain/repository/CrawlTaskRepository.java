package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.AIC;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alan
 */
public interface CrawlTaskRepository extends JpaRepository<AIC, Integer> {
    
}
