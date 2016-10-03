package org.com.isentia.service.crawl;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.com.isentia.service.dao.ArticleDAO;
import org.com.isentia.service.dao.AuthorDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorDataConsumerTest {

    @Mock
    private BlockingQueue<AuthorDataWrapper> blockingQueue;

    @Mock
    AuthorDAO authorDAO;

    @Mock
    ArticleDAO articleDAO;


    @InjectMocks
    AuthorDataConsumer authorDataConsumer = new AuthorDataConsumer();


    @Test
    public void consumerShouldStopPullingDocumentFromQueue() throws InterruptedException {
        Author author = new Author();
        when(blockingQueue.take()).thenReturn(new AuthorDataWrapper(false, author));
        authorDataConsumer.run();
    }

    @Test
    public void consumerShouldPullingDocumentFromQueueAndCallDAO() throws InterruptedException {
        Author author = new Author();
        author.setProfile("");
        Article article = new Article();
        article.setURL("");
        author.getArticleList().add(article);
        Mockito.doThrow(new RuntimeException("")).when(authorDAO).save(any(Author.class));
        when(blockingQueue.take()).thenReturn(new AuthorDataWrapper(true, author));
        authorDataConsumer.run();
        verify(authorDAO, times(1)).save(any(Author.class));
        verify(articleDAO, times(1)).save(any(Article.class));
    }

}
