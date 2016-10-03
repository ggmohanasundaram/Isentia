package org.com.isentia.service.dao;

import org.com.isentia.model.Article;

import java.text.ParseException;
import java.util.List;


public interface ArticleDAO {
    void save(Article article);

    List<Article> findAll();

    String findAuthorByArticleName(String articleName);

    List<Article> findAllArticlesWithPartialName(String keyword);

    List<Article> findArticlesWithName(String... articleName);

    List<Article> findArticlesByDate(String date) throws ParseException;
}
