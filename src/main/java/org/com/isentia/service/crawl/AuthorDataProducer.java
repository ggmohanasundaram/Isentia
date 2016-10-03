package org.com.isentia.service.crawl;

import org.com.isentia.customexceptios.ServiceException;
import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;


@Configuration("authorDataProducer")
public class AuthorDataProducer implements Runnable {

    private Collection<Author> authors;
    @Autowired
    private BlockingQueue<AuthorDataWrapper> blockingQueue;

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    @Override
    public void run() {
        try {
            addAuthorInQueueToStore();
        } catch (Exception e) {
            return;
        }
    }

    private void addAuthorInQueueToStore() throws InterruptedException, ServiceException {
        try {
            for (Author author : authors) {
                blockingQueue.put(new AuthorDataWrapper(true, author));
            }
            blockingQueue.put(new AuthorDataWrapper(false, null));
        } catch (Exception e) {
            throw new ServiceException("Problem in Producer--" + e);
        }
    }


}



