package org.com.isentia.service.crawl;

import org.com.isentia.customexceptios.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsContentCrawlerImplTest {

    @Mock
    private AuthorDataProducer authorDataProducer;
    @Mock
    private AuthorDataConsumer authorDataConsumer;
    @Mock
    private Environment environment;

    @InjectMocks
    NewsContentCrawlerImpl newsContentCrawler = new NewsContentCrawlerImpl();

    @Test
    public void testPopulateModelData() throws Exception {

        when(environment.getRequiredProperty("website.url")).thenReturn("https://www.theguardian.com/au");
        newsContentCrawler.extractAndStoreWebContent();
        verify(authorDataProducer,times(1)).run();
        verify(authorDataConsumer,times(1)).run();
    }
}
