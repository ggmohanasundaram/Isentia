package org.com.isentia.service.search;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.com.isentia.service.dao.ArticleDAO;
import org.com.isentia.service.dao.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("newsSearch")
public class NewsSearchImpl implements NewsSearch {

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    ArticleDAO articleDAO;

    @Override
    public List<Author> getAllAuthors() {
        return authorDAO.findAll();
    }

    @Override
    public List<Article> getAllArticles() {
        return articleDAO.findAll();
    }

    @Override
    public List<Article> findArticlesByAuthor(String... authorName) {
        return authorDAO.findArticlesByAuthor(authorName);
    }

    @Override
    public String findAuthorByArticleName(String articleName) {
        return articleDAO.findAuthorByArticleName(articleName);
    }

    @Override
    public List<Article> findAllArticlesWithPartialName(String keyword) {
        return articleDAO.findAllArticlesWithPartialName(keyword);
    }

    @Override
    public List<Article> findArticlesWithName(String... articleName) {
        return articleDAO.findArticlesWithName(articleName);
    }

    @Override
    public List<Article> findArticlesByDate(String date) throws ParseException {
        return articleDAO.findArticlesByDate(date);
    }
}
