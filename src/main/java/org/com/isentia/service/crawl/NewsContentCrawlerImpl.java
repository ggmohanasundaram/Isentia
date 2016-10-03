package org.com.isentia.service.crawl;

import org.com.isentia.model.Article;
import org.com.isentia.model.Author;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;

@Service("newsContentCrawler")
@PropertySource("classpath:application.properties")
public class NewsContentCrawlerImpl implements NewsContentCrawler {

    @Autowired
    private AuthorDataProducer authorDataProducer;
    @Autowired
    private AuthorDataConsumer authorDataConsumer;
    @Autowired
    private Environment environment;


    @Override
    public void extractAndStoreWebContent() throws Exception {

        String targetURL = environment.getRequiredProperty("website.url");
        Document doc = Jsoup.connect(targetURL).get();
        Map<String, Author> authorDetails = populateModelData(doc);
        authorDataProducer.setAuthors(authorDetails.values());
        Thread producer = new Thread(authorDataProducer);
        Thread consumer = new Thread(authorDataConsumer);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    private Map<String, Author> populateModelData(Document doc) throws IOException {
        Map<String, Author> authorHashMap = new HashMap<>();
        Document childDoc = null;
        try {
            Elements links = doc.select("a[href]").select("a[data-link-name=article]");
            for (Element link : links) {
                String url = link.attr("href");
                if (!url.contains("in-pictures") && !url.contains("video")) {
                    Article article = new Article();
                    article.setHeadLine(link.text());

                    article.setURL(url);
                    childDoc = Jsoup.connect(url).get();
                    article.setContent(childDoc.select("div[itemprop=articleBody]").text());
                    article.setPublishDate(childDoc.select("time[itemprop=datePublished").attr("datetime"));
                    String authorProfile = childDoc.select("meta[property=article:author]").attr("content");
                    Author author = authorHashMap.get(authorProfile);
                    if (author != null) {
                        article.setAuthorProfile(author);
                        author.getArticleList().add(article);
                    } else {
                        author = new Author();
                        author.setProfile(authorProfile);
                        author.setName(childDoc.select("meta[name=author]").attr("content"));
                        author.getArticleList().add(article);
                        article.setAuthorProfile(author);
                        authorHashMap.put(authorProfile, author);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorHashMap;
    }
}
