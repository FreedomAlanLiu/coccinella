/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Alan
 */
public class OschinaBlogPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        /*page.putField("name",
                page.getHtml()
                    .xpath("/html/body/div/table[@class='list']/tbody/tr/th[@class='result']/table[@class='con'][1]/tbody/tr[1]/td")
                    .regex("[\\u4e00-\\u9fa5]+（[\\u4e00-\\u9fa5]+）[\\u4e00-\\u9fa5]+"));*/


        String filePath = ".";
        if (page instanceof ImagePage) {
            ImagePage imgPage = (ImagePage) page;
            try {
                ImageIO.write(imgPage.getImage(), "jpg", new File("C:\\rand.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.sgs.gov.cn");
    }

    @Test
    public void testCrawl() {
        /*CrawlerRequest req = new CrawlerRequest("http://www.sgs.gov.cn/lz/etpsInfo.do?method=doSearch");
        req.addParam("searchType", "1");
        req.addParam("keyWords", "泓远软件");*/

        Request request = new Request("http://qyxy.baic.gov.cn/CheckCodeYunSuan?currentTimeMillis=" + System.currentTimeMillis());

        Spider.create(new OschinaBlogPageProcesser())
                .setDownloader(new ImageDownloader())
                .addRequest(request)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
