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
import us.codecraft.webmagic.selector.Selectable;

/**
 *
 * @author Alan
 */
public class MyPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        if (page instanceof CrawlerPage) {
            CrawlerPage crawlerPage = (CrawlerPage) page;
            Crawler crawler = crawlerPage.getCrawler();

            if (crawler.getUrl().contains("GSJ=toList2")) {
                Selectable selectable = page.getHtml();
                page.putField("page", page.getHtml());
                //page.putField("etpsName", selectable.css(".link6666cc").xpath("//a/@onclick").regex("'(.*?)'").all());
            }
        }
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
        c1.setUrl("http://www.zjecredit.org/zjecredit/index.do");
        c1.setMethod(HttpMethod.GET);
        c1.setEncode("utf-8");

        Crawler c2 = new Crawler();
        c2.setUrl("http://www.zjecredit.org/zjecredit/searchaction.do?GSJ=toLookUP2");
        c2.setMethod(HttpMethod.POST);
        c2.setParams("comName=房地产开发有限公司,people=");
        c2.setEncode("utf-8");
        c2.setReferer("http://www.zjecredit.org/zjecredit/index.do");

        Crawler c3 = new Crawler();
        c3.setUrl("http://www.zjecredit.org/zjecredit/searchaction.do?GSJ=toList2");
        c3.setMethod(HttpMethod.POST);
        c3.setEncode("utf-8");
        c3.setReferer("http://www.zjecredit.org/zjecredit/pages/search/waiting.jsp");

        CrawlerRequest req1 = new CrawlerRequest(c1);
        req1.setPriority(12);
        
        CrawlerRequest req2 = new CrawlerRequest(c2);
        req2.setPriority(10);
        req2.addParam("comName", "房地产开发有限公司");
        req2.addParam("people", "");

        CrawlerRequest req3 = new CrawlerRequest(c3);
        req3.setPriority(8);

        Spider.create(new MyPageProcessor())
                .setDownloader(new CrawlerDownloader())
                .addRequest(req1)
                .addRequest(req2)
                .addRequest(req3)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
