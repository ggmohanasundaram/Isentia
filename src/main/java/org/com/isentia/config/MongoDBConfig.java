package org.com.isentia.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import org.com.isentia.customexceptios.PropertyLoaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;


@Configuration
@PropertySource("classpath:database.properties")
public class MongoDBConfig {

    @Autowired
    Environment environment;

    @Autowired
    private Mongo mongo;

    /*@Bean
    public MongoClient mongoClient() throws PropertyLoaderException {

        MongoClientURI uri = new MongoClientURI(
                "mongodb://demo:isentia123@aws-us-east-1-portal.23.dblayer.com:15732/NewsDatabase");
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;
    }*/
    @Bean
    public MongoClientFactoryBean mongoClientFactoryBean() throws PropertyLoaderException {

        try {

            MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();

            factoryBean.setHost(environment.getRequiredProperty("mongo.host"));
            factoryBean.setPort(Integer.parseInt(environment.getRequiredProperty("mongo.port")));
            MongoCredential credential = MongoCredential.createCredential(environment.getRequiredProperty("mongo.username"),
                    environment.getRequiredProperty("mongo.database"), environment.getRequiredProperty("mongo.password").toCharArray());
            factoryBean.setCredentials(new MongoCredential[]{credential});
            return factoryBean;
        } catch (Exception e) {
            throw new PropertyLoaderException("Problem in Loading Mongo Factory bean " + e);
        }
    }

    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, environment.getRequiredProperty("mongo.database"));
        return mongoTemplate;
    }
}
