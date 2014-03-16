package org.daybreak.coccinella.domain.service.impl;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.model.Crawler;
import org.daybreak.coccinella.domain.model.Parser;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.repository.CrawlerRepository;
import org.daybreak.coccinella.domain.repository.ParserRepository;
import org.daybreak.coccinella.domain.service.AicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
@Service
public class AicServiceImpl implements AicService {

    @Inject
    private AICRepository aicRepository;

    @Inject
    private CrawlerRepository crawlerRepository;

    @Inject
    private ParserRepository parserRepository;

    @Override
    public List<AIC> loadAics() {
        return aicRepository.findAll();
    }

    @Override
    public AIC loadAic(long id) {
        return aicRepository.findById(id);
    }

    @Override
    @Transactional
    public AIC saveAic(AIC aic) {
        return aicRepository.save(aic);
    }

    @Override
    @Transactional
    public void deleteAic(long id) {
        AIC aic = aicRepository.findById(id);
        if (aic != null) {
            aicRepository.delete(aic);
        }
    }

    @Override
    public Crawler loadCrawler(long id) {
        return crawlerRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteCrawler(Crawler crawler) {
        crawlerRepository.delete(crawler);
    }

    @Override
    @Transactional
    public Crawler saveCrawler(Crawler crawler) {
        return crawlerRepository.save(crawler);
    }

    @Override
    public Parser loadParser(long id) {
        return parserRepository.findById(id);
    }

    @Override
    public Parser saveParser(Parser parser) {
        return parserRepository.save(parser);
    }
}
