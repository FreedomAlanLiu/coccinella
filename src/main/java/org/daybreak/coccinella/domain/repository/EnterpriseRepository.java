package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alan on 14-3-11.
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

    Page<Enterprise> findByAicAndNameLike(AIC aic, String name, Pageable pageable);
    
    Enterprise findByAicAndName(AIC aic, String name);
}
