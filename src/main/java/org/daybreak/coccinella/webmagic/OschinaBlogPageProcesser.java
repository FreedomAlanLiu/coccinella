/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 *
 * @author Alan
 */
public class OschinaBlogPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        page.putField("name",
                page.getHtml()
                    .xpath("/html/body/div/table[@class='list']/tbody/tr/th[@class='result']/table[@class='con'][1]/tbody/tr[1]/td")
                    .regex("[\\u4e00-\\u9fa5]+（[\\u4e00-\\u9fa5]+）[\\u4e00-\\u9fa5]+"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.sgs.gov.cn");
    }

    public static void main(String[] args) {
        PostRequest req = new PostRequest("http://www.sgs.gov.cn/lz/etpsInfo.do?method=doSearch");
        req.addParam("searchType", "1");
        req.addParam("keyWords", "泓远软件");
        
        Spider.create(new OschinaBlogPageProcesser())
                .setDownloader(new HttpClientPostDownloader())
                .addRequest(req)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
