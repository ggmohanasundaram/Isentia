package org.com.isentia.service.crawl;

import org.com.isentia.model.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthorDataProducerTest {

    @Mock
    private BlockingQueue<AuthorDataWrapper> blockingQueue;

    @InjectMocks
    AuthorDataProducer authorDataProducer = new AuthorDataProducer();

    @Test
    public void shouldProduceDataIntoQueue() throws InterruptedException {
        List<Author> list = new ArrayList<>();
        Author author = new Author();
        list.add(author);
        list.add(author);
        list.add(author);
        authorDataProducer.setAuthors(list);
        authorDataProducer.run();
        verify(blockingQueue,times(4)).put(any(AuthorDataWrapper.class));

    }
}
