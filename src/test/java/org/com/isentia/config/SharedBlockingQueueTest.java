package org.com.isentia.config;

import org.com.isentia.service.crawl.AuthorDataWrapper;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SharedBlockingQueueTest {

    @Test
    public void testBlockingQueueInitialSize() {
        SharedBlockingQueue sharedBlockingQueue = new SharedBlockingQueue();
        BlockingQueue<AuthorDataWrapper> authorDataWrappers = sharedBlockingQueue.initializeQueue();
        assertThat(authorDataWrappers.remainingCapacity(), is(10));
    }
}
