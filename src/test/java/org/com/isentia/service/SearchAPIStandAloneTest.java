package org.com.isentia.service;

import org.com.isentia.config.CrawlerApplicationConfig;
import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.com.isentia.service.search.NewsSearch;
import org.com.isentia.service.search.NewsSearchImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.util.List;

public class SearchAPIStandAloneTest {

    @Ignore /** This is Ene to End/stand alone test for Search API
     Kindly comment the @Ignore to run**/
    @Test
    public void testSearchAPI() throws ParseException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CrawlerApplicationConfig.class);
        NewsSearch newsSearch = (NewsSearchImpl) context.getBean("newsSearch");
        List<Author> allAuthors = newsSearch.getAllAuthors();
        System.out.println(allAuthors.size());
        List<Article> allArticles = newsSearch.getAllArticles();
        System.out.println(allArticles.size());
        List<Article> articlesByAuthor = newsSearch.findArticlesByAuthor("","Jason Wilson");
        System.out.println(articlesByAuthor.size());
        String authorName = newsSearch.findAuthorByArticleName("Portlandia is ruining Portland Feminist bookstore publicly cuts ties with show");
        System.out.println(authorName);
        List<Article> allArticlesWithName = newsSearch.findArticlesWithName("Portlandia is ruining Portland Feminist bookstore publicly cuts ties with show",
                "Sydney Admirers excluded in Droudis murder investigation, court told");
        System.out.println(allArticlesWithName.size());
        List<Article> allArticlesWithPartialName = newsSearch.findAllArticlesWithPartialName("Portlandia");
        System.out.println(allArticlesWithPartialName.size());
        List<Article> articlesByDate = newsSearch.findArticlesByDate("2016-10-01");
        System.out.println(articlesByDate.size());
    }
}
