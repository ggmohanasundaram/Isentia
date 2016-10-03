package org.com.isentia.service.search;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.com.isentia.service.dao.ArticleDAO;
import org.com.isentia.service.dao.AuthorDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NewsSearchAPITest {

    @Mock
    AuthorDAO authorDAO;

    @Mock
    ArticleDAO articleDAO;

    @InjectMocks
    NewsSearchImpl newsSearch = new NewsSearchImpl();

    @Test
    public void testGetAllAuthors() {
        List<Author> allAuthors = newsSearch.getAllAuthors();
        verify(authorDAO, times(1)).findAll();
    }

    @Test
    public void testGetAllArticles() {
        List<Article> allArticles = newsSearch.getAllArticles();
        verify(articleDAO, times(1)).findAll();
    }

    @Test
    public void testFindAuthorByArticleName() {
        String authorByArticleName = newsSearch.findAuthorByArticleName("someArticles");
        verify(articleDAO, times(1)).findAuthorByArticleName(any(String.class));
    }

    @Test
    public void testFindArticlesByAuthor() {
        List<Article> articles = newsSearch.findArticlesByAuthor("someAuthor");
        verify(authorDAO, times(1)).findArticlesByAuthor(any(String.class));
    }

    @Test
    public void testFindAllArticlesWithPartialName() {
        List<Article> articles = newsSearch.findAllArticlesWithPartialName("someKeyWord");
        verify(articleDAO, times(1)).findAllArticlesWithPartialName(any(String.class));
    }

    @Test
    public void testFindArticlesWithName() {
        List<Article> articles = newsSearch.findArticlesWithName("someKeyWord");
        verify(articleDAO, times(1)).findArticlesWithName(any(String.class));
    }

    @Test
    public void testFindArticlesByDate() throws ParseException {
        List<Article> articles = newsSearch.findArticlesByDate("someKeyWord");
        verify(articleDAO, times(1)).findArticlesByDate(any(String.class));
    }
}
