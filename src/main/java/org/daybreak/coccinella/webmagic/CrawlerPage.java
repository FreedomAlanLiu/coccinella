package org.daybreak.coccinella.webmagic;

import org.daybreak.coccinella.domain.model.Crawler;
import us.codecraft.webmagic.Page;

/**
 * Created by Alan on 14-3-15.
 */
public class CrawlerPage extends Page {

    private Crawler crawler;

    public CrawlerPage(Crawler crawler) {
        super();
        this.crawler = crawler;
    }

    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }
}
