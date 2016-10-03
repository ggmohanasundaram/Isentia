package org.com.isentia.service.crawl;

import org.com.isentia.customexceptios.ServiceException;
import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.com.isentia.service.dao.ArticleDAO;
import org.com.isentia.service.dao.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


@Configuration("authorDataConsumer")
public class AuthorDataConsumer implements Runnable {

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    private BlockingQueue<AuthorDataWrapper> blockingQueue;

    @Override
    public void run() {

        try {
            pullDataToStoreFromQueue();
        } catch (ServiceException e) {
            return;
        }
    }

    private void pullDataToStoreFromQueue() throws ServiceException {
        try {
            while (true) {
                AuthorDataWrapper authorDataWrapper = blockingQueue.take();
                if (authorDataWrapper.isValid()) {
                    Author author = authorDataWrapper.getAuthor();
                    Set<Article> articleList = author.getArticleList();
                    for (Article article : articleList) {
                        articleDAO.save(article);
                    }
                    authorDAO.save(author);

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            throw new ServiceException("Problem whild Storing the Data into DB-"+e);
        }
    }
}
