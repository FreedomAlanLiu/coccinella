/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import com.google.common.collect.Sets;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.selector.PlainText;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Alan
 */
public class ImageDownloader extends HttpClientDownloader {

    private final Logger logger = Logger.getLogger(getClass());

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<>();

    private final HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    public ImageDownloader() {
        super();
    }
    
    @Override
    public Page download(Request request, Task task) {
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
            headers = site.getHeaders();
        } else {
            acceptStatCode = Sets.newHashSet(200);
        }
        logger.info("downloading image " + request.getUrl());
        RequestBuilder requestBuilder;
        if (request instanceof CrawlerRequest) {
            CrawlerRequest crawlerRequest = (CrawlerRequest) request;
            try {
                requestBuilder = RequestBuilder
                        .post()
                        .setUri(crawlerRequest.getUrl())
                        .setEntity(crawlerRequest.createEntity());
            } catch (UnsupportedEncodingException ex) {
                logger.warn("The encoding is not supported: " + crawlerRequest.getEncode());
                return null;
            }
        } else {
            requestBuilder = RequestBuilder.get().setUri(request.getUrl());
        }
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectionRequestTimeout(site.getTimeOut())
                .setSocketTimeout(site.getTimeOut())
                .setConnectTimeout(site.getTimeOut())
                .setCookieSpec(CookieSpecs.BEST_MATCH);
        if (site != null && site.getHttpProxy() != null) {
            requestConfigBuilder.setProxy(site.getHttpProxy());
        }
        requestBuilder.setConfig(requestConfigBuilder.build());
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = getHttpClient(site).execute(requestBuilder.build());
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (acceptStatCode.contains(statusCode)) {
                ImagePage imagePage = new ImagePage(ImageIO.read(httpResponse.getEntity().getContent()));
                imagePage.setRawText("");
                imagePage.setUrl(new PlainText(request.getUrl()));
                imagePage.setRequest(request);
                imagePage.setStatusCode(httpResponse.getStatusLine().getStatusCode());
                return imagePage;
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
                return null;
            }
        } catch (IOException e) {
            logger.warn("download image " + request.getUrl() + " error", e);
            if (site.getCycleRetryTimes() > 0) {
                return addToCycleRetry(request, site);
            }
            return null;
        } finally {
            try {
                if (httpResponse != null) {
                    //ensure the connection is released back to pool
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (IOException e) {
                logger.warn("close response fail", e);
            }
        }
    }

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    private Page addToCycleRetry(Request request, Site site) {
        Page page = new Page();
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes >= site.getCycleRetryTimes()) {
                return null;
            }
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        }
        return page;
    }
}
