/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.daybreak.coccinella.domain.model.Crawler;
import org.springframework.http.HttpMethod;
import us.codecraft.webmagic.Request;

/**
 *
 * @author Alan
 */
public class CrawlerRequest extends Request {

    private final List<NameValuePair> params = new ArrayList<>();

    private Crawler crawler;

    private boolean canRepeat;
    
    public CrawlerRequest(Crawler crawler) {
        super(crawler.getUrl());
        this.crawler = crawler;
    }

    public void addParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public HttpEntity createEntity() throws UnsupportedEncodingException {
        return new UrlEncodedFormEntity(getParams(), crawler.getEncode());
    }

    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public boolean isCanRepeat() {
        return canRepeat;
    }

    public void setCanRepeat(boolean canRepeat) {
        this.canRepeat = canRepeat;
    }
}
