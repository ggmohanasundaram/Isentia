package org.com.isentia.service.dao;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("authorDAO")
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void save(Author author) {
        mongoOperations.save(author);
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = mongoOperations.findAll(Author.class);
        return authorList;
    }

    @Override
    public List<Article> findArticlesByAuthor(String... authorName) {
        List<Article> articles = new ArrayList<>();
        Query query = new Query();
        Criteria criteria = Criteria.where("name").in(authorName);
        query.addCriteria(criteria);
        List<Author> authors = mongoOperations.find(query, Author.class);
        for (Author author : authors) {
            articles.addAll(author.getArticleList());
        }
        return articles;
    }
}
