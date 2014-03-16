package org.daybreak.coccinella.domain.service;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Parser;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
public interface AicService {

    public List<AIC> loadAics();

    public AIC loadAic(long id);

    public AIC saveAic(AIC aic);

    public void deleteAic(long id);

    public Crawler loadCrawler(long id);

    public Crawler saveCrawler(Crawler crawler);

    public void deleteCrawler(Crawler crawler);

    public Parser loadParser(long id);

    public Parser saveParser(Parser parser);
}
