package org.com.isentia.service.dao;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("articleDAO")
public class ArticleDAOImpl implements ArticleDAO {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void save(Article article) {
        mongoOperations.save(article);
    }

    @Override
    public List<Article> findAll() {
        return mongoOperations.findAll(Article.class);
    }

    @Override
    public String findAuthorByArticleName(String articleName) {
        Query query = new Query();
        Criteria criteria = Criteria.where("headLine").is(articleName);
        query.addCriteria(criteria);
        List<Article> articles = mongoOperations.find(query, Article.class);
        if (articles != null && articles.size() > 0) {
            Article article = articles.get(0);
            if (article != null) {
                return article.getAuthorProfile().getName();
            }
        }
        return "";
    }

    @Override
    public List<Article> findAllArticlesWithPartialName(String keyword) {
        Query query = new Query();
        Criteria criteria = Criteria.where("headLine").regex(keyword);
        query.addCriteria(criteria);
        List<Article> articles = mongoOperations.find(query, Article.class);
        return articles;
    }

    @Override
    public List<Article> findArticlesWithName(String... articleName) {
        Query query = new Query();
        Criteria criteria = Criteria.where("headLine").in(articleName);
        query.addCriteria(criteria);
        List<Article> articles = mongoOperations.find(query, Article.class);
        return articles;
    }

    @Override
    public List<Article> findArticlesByDate(String date) throws ParseException {
        Query query = new Query();
        Criteria criteria = Criteria.where("publishDate").regex(date);
        query.addCriteria(criteria);
        List<Article> articles = mongoOperations.find(query, Article.class);
        return articles;
    }
}
