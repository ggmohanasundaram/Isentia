package org.com.isentia.service.dao;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;

import java.util.List;

public interface AuthorDAO {

    public void save(Author author);

    public List<Author> findAll();

    public List<Article> findArticlesByAuthor(String... authorName);
}
