package org.com.isentia.service.search;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;

import java.text.ParseException;
import java.util.List;


public interface NewsSearch {
    public List<Author> getAllAuthors();

    public List<Article> getAllArticles();

    public List<Article> findArticlesByAuthor(String... authorName);

    public String findAuthorByArticleName(String articleName);

    public List<Article> findAllArticlesWithPartialName(String keyword);

    public List<Article> findArticlesWithName(String... articleName);

    public List<Article> findArticlesByDate(String date) throws ParseException;
}
