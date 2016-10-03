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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ArticleDAOImplTest {

    @Mock
    MongoOperations mongoOperations;

    @Captor
    ArgumentCaptor<Query> queryArgumentCaptor;

    @InjectMocks
    ArticleDAOImpl articleDAO = new ArticleDAOImpl();

    @Test
    public void testSave() {
        articleDAO.save(new Article());
        verify(mongoOperations, times(1)).save(any(Article.class));
    }

    @Test
    public void testFindAll() {
        articleDAO.findAll();
        verify(mongoOperations, times(1)).findAll(Article.class);
    }

    @Test
    public void testFindAuthorByArticleName() {
        Article article = new Article();
        article.setHeadLine("someName");
        article.setAuthorProfile(new Author());
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article);
        when(mongoOperations.find(any(Query.class), any(Class.class))).thenReturn(articles);
        articleDAO.findAuthorByArticleName("someName");
        verify(mongoOperations, times(1)).find(any(Query.class), any(Class.class));
        verify(mongoOperations).find(queryArgumentCaptor.capture(), any(Class.class));
        DBObject queryObject = queryArgumentCaptor.getValue().getQueryObject();
        assertThat(1, is(queryObject.keySet().size()));
        assertThat(true, is(queryObject.keySet().contains("headLine")));

    }

    @Test
    public void testfindAllArticlesWithPartialName() {

        ArrayList<Article> articles = new ArrayList<>();
        when(mongoOperations.find(any(Query.class), any(Class.class))).thenReturn(articles);
        articleDAO.findAllArticlesWithPartialName("someName");
        verify(mongoOperations, times(1)).find(any(Query.class), any(Class.class));
        verify(mongoOperations).find(queryArgumentCaptor.capture(), any(Class.class));
        DBObject queryObject = queryArgumentCaptor.getValue().getQueryObject();
        assertThat(1, is(queryObject.keySet().size()));
        assertThat(true, is(queryObject.keySet().contains("headLine")));
    }

    @Test
    public void testFindArticlesWithName() {

        ArrayList<Article> articles = new ArrayList<>();
        when(mongoOperations.find(any(Query.class), any(Class.class))).thenReturn(articles);
        articleDAO.findArticlesWithName("someName");
        verify(mongoOperations, times(1)).find(any(Query.class), any(Class.class));
        verify(mongoOperations).find(queryArgumentCaptor.capture(), any(Class.class));
        DBObject queryObject = queryArgumentCaptor.getValue().getQueryObject();
        assertThat(1, is(queryObject.keySet().size()));
        assertThat(true, is(queryObject.keySet().contains("headLine")));

    }

    @Test
    public void testFindArticlesByDate() throws ParseException {

        ArrayList<Article> articles = new ArrayList<>();
        when(mongoOperations.find(any(Query.class), any(Class.class))).thenReturn(articles);
        articleDAO.findArticlesByDate("someName");
        verify(mongoOperations, times(1)).find(any(Query.class), any(Class.class));
        verify(mongoOperations).find(queryArgumentCaptor.capture(), any(Class.class));
        DBObject queryObject = queryArgumentCaptor.getValue().getQueryObject();
        assertThat(1, is(queryObject.keySet().size()));
        assertThat(true, is(queryObject.keySet().contains("publishDate")));

    }
}
