package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alan on 14-3-16.
 */
@Repository
public interface CrawlerRepository extends JpaRepository<Crawler, Integer> {

    Crawler findById(long id);
}
