package org.daybreak.coccinella.domain.repository;

import org.daybreak.coccinella.domain.model.Enterprise;
import org.daybreak.coccinella.domain.model.Parser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alan on 14-3-16.
 */
@Repository
public interface ParserRepository extends JpaRepository<Parser, Integer> {

    Parser findById(long id);
}
