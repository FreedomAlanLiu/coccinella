/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import org.daybreak.coccinella.domain.model.Crawler;
import org.springframework.http.HttpMethod;

/**
 *
 * @author Alan
 */
public class MyPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        page.putField("page", page.getHtml().toString());
    }

    @Override
    public Site getSite() {
        Site me = Site.me();
        me.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
        return me;
    }

    @Test
    public void testCrawl() {
        Crawler c1 = new Crawler();
        c1.setUrl("http://www.sgs.gov.cn/lz/etpsInfo.do?method=index");
        c1.setMethod(HttpMethod.GET);
        c1.setEncode("utf-8");
        
        Crawler c2 = new Crawler();
        c2.setUrl("http://www.sgs.gov.cn/lz/etpsInfo.do?method=doSearch");
        c2.setMethod(HttpMethod.POST);
        c2.setParams("searchType=1,keyWords=泓远软件");
        c2.setEncode("utf-8");
        c2.setReferer("http://www.sgs.gov.cn/lz/etpsInfo.do?method=index");
        
        Crawler c3 = new Crawler();
        c3.setUrl("http://www.sgs.gov.cn/lz/etpsInfo.do?method=viewDetail");
        c3.setMethod(HttpMethod.POST);
        c3.setParams("etpsId=150000022000060700017");
        c3.setEncode("utf-8");
        c3.setReferer("http://www.sgs.gov.cn/lz/etpsInfo.do?method=doSearch");
        
        CrawlerRequest req1 = new CrawlerRequest(c1);
        req1.setPriority(10);
        CrawlerRequest req2 = new CrawlerRequest(c2);
        req2.addParam("searchType", "1");
        req2.addParam("keyWords", "泓远软件");
        req2.setPriority(8);
        CrawlerRequest req3 = new CrawlerRequest(c3);
        req3.addParam("etpsId", "150000022000060700017");
        req3.setPriority(6);

        Spider.create(new MyPageProcessor())
                .setDownloader(new CrawlerDownloader())
                .addRequest(req1)
                .addRequest(req2)
                .addRequest(req3)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
