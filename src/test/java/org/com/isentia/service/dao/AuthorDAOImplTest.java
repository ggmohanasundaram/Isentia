package org.com.isentia.service.dao;

import com.mongodb.DBObject;
import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AuthorDAOImplTest {

    @Mock
    MongoOperations mongoOperations;

    @Captor
    ArgumentCaptor<Query> queryArgumentCaptor;

    @InjectMocks
    AuthorDAOImpl authorDAO = new AuthorDAOImpl();

    @Test
    public void testSave() {
        authorDAO.save(new Author());
        verify(mongoOperations, times(1)).save(any(Author.class));
    }

    @Test
    public void testFindAll() {
        authorDAO.findAll();
        verify(mongoOperations, times(1)).findAll(Author.class);
    }

    @Test
    public void testFindArticlesByAuthor() {
        List<Author> authorList = new ArrayList<>();
        when(mongoOperations.find(any(Query.class), any(Class.class))).thenReturn(authorList);
        authorDAO.findArticlesByAuthor("");
        verify(mongoOperations, times(1)).find(any(Query.class), any(Class.class));
        verify(mongoOperations).find(queryArgumentCaptor.capture(), any(Class.class));
        DBObject queryObject = queryArgumentCaptor.getValue().getQueryObject();
        assertThat(1, is(queryObject.keySet().size()));
        assertThat(true, is(queryObject.keySet().contains("name")));

    }


}
