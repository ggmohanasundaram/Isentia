package org.com.isentia.config;

import org.com.isentia.service.crawl.AuthorDataWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Configuration("sharedBlockingQueue")
public class SharedBlockingQueue {

    @Bean
    public BlockingQueue<AuthorDataWrapper> initializeQueue() {
        return new ArrayBlockingQueue<AuthorDataWrapper>(10);
    }
}
