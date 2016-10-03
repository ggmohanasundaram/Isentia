package org.com.isentia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "Article")
public class Article {
    @Id
    private String URL;
    @Field
    private String headLine;
    @Field
    private String content;
    @Field
    private String publishDate;
    @DBRef
    private Author authorProfile;

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getContent() {
        return content;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setAuthorProfile(Author authorProfile) {
        this.authorProfile = authorProfile;
    }

    public Author getAuthorProfile() {
        return authorProfile;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Article)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Article article = (Article) obj;
        return this.getURL().equals(article.getURL());
    }

    @Override
    public int hashCode() {
        return this.getURL().hashCode();
    }
}
