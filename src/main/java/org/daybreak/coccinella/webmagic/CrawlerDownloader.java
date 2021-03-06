/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 *
 * @author Alan
 */
public class CrawlerDownloader extends HttpClientDownloader {

    private final Logger logger = Logger.getLogger(getClass());

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<>();

    private final HttpClientGenerator httpClientGenerator = new HttpClientGenerator();
    
    public CrawlerDownloader() {
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
        logger.info("downloading page " + request.getUrl());
        RequestBuilder requestBuilder = null;
        if (request instanceof CrawlerRequest) {
            CrawlerRequest crawlerRequest = (CrawlerRequest) request;
            if (StringUtils.isNotBlank(crawlerRequest.getCrawler().getReferer())) {
                site.addHeader(HttpHeaders.REFERER, crawlerRequest.getCrawler().getReferer());
            }
            if (crawlerRequest.getCrawler().getMethod() == HttpMethod.GET) {
                requestBuilder = RequestBuilder.get().setUri(request.getUrl());
            } else if (crawlerRequest.getCrawler().getMethod() == HttpMethod.POST) {
                try {
                    requestBuilder = RequestBuilder
                            .post()
                            .setUri(crawlerRequest.getUrl())
                            .setEntity(crawlerRequest.createEntity());
                } catch (UnsupportedEncodingException ex) {
                    logger.warn("The encoding is not supported: " + crawlerRequest.getCrawler().getEncode());
                    return null;
                }
            }
        }

        if (requestBuilder == null) {
            return null;
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
                // 消息头的信息
                Header[] resHeaders = httpResponse.getAllHeaders();
                for (int i = 0; i < resHeaders.length; i++) {
                    if (resHeaders[i].getName().equals("Set-Cookie")) {
                        String cookie = resHeaders[i].getValue();
                        String cookieName = cookie.split("=")[0];
                        String cookieValue = cookie.split("=")[1].split(";")[0];
                        site.addCookie(cookieName, cookieValue);
                    }
                }

                //charset
                if (charset == null) {
                    String value = httpResponse.getEntity().getContentType().getValue();
                    charset = UrlUtils.getCharset(value);
                }
                return handleResponse(request, charset, httpResponse, task);
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
                return null;
            }
        } catch (IOException e) {
            logger.warn("download page " + request.getUrl() + " error", e);
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

    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        if (request instanceof CrawlerRequest) {
            CrawlerRequest crawlerRequest = (CrawlerRequest) request;
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), charset);
            Page page = new CrawlerPage(crawlerRequest.getCrawler());
            page.setRawText(content);
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
            page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            return page;
        }
        return null;
    }
}
