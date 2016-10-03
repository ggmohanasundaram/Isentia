package org.com.isentia.service.crawl;

import org.com.isentia.model.Author;


public final class AuthorDataWrapper {
    private boolean isValid;
    private Author author;

    public AuthorDataWrapper(boolean isValid, Author author) {

        this.isValid = isValid;
        this.author = author;
    }

    public boolean isValid() {
        return isValid;
    }

    public Author getAuthor() {
        return author;
    }
}
