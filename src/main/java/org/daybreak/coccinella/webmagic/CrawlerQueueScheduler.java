package org.daybreak.coccinella.webmagic;

import org.apache.http.annotation.ThreadSafe;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Alan on 14-3-17.
 */
@ThreadSafe
public class CrawlerQueueScheduler implements Scheduler {

    private Logger logger = Logger.getLogger(getClass());

    private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

    private Set<String> urls = new HashSet<String>();

    @Override
    public synchronized void push(Request request, Task task) {
        if (logger.isDebugEnabled()) {
            logger.debug("push to queue " + request.getUrl());
        }
        if (request instanceof CrawlerRequest) {
            CrawlerRequest crawlerRequest = (CrawlerRequest) request;
            if (crawlerRequest.isCanRepeat()) {
                urls.add(request.getUrl());
                queue.add(request);
            } else {
                if (urls.add(request.getUrl())) {
                    queue.add(request);
                }
            }
        } else {
            if (urls.add(request.getUrl())) {
                queue.add(request);
            }
        }
    }

    @Override
    public synchronized Request poll(Task task) {
        return queue.poll();
    }
}
