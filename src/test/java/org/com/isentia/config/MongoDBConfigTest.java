package org.com.isentia.config;

import org.com.isentia.customexceptios.PropertyLoaderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MongoDBConfigTest {
    @Mock
    private static Environment environment;

    @InjectMocks
    MongoDBConfig mongoDBConfig = new MongoDBConfig();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void initialize() {
        when(environment.getRequiredProperty("mongo.host")).thenReturn("localhost");
        when(environment.getRequiredProperty("mongo.port")).thenReturn("27017");
        when(environment.getRequiredProperty("mongo.username")).thenReturn("usename");
        when(environment.getRequiredProperty("mongo.database")).thenReturn("databasename");
        when(environment.getRequiredProperty("mongo.password")).thenReturn("password");
    }

    @Test
    public void mongoClientFactoryBeanShouldReturnCleint() throws Exception {
        MongoClientFactoryBean mongoClientFactoryBean = mongoDBConfig.mongoClientFactoryBean();
        Assert.assertNotNull(mongoClientFactoryBean);

    }

    @Test
    public void mongoClientFactoryBeanShouldThrowException() throws Exception {
        when(environment.getRequiredProperty("mongo.port")).thenReturn(null);
        exception.expect(PropertyLoaderException.class);
        exception.expectMessage(contains("Problem in Loading Mongo Factory bean"));
        MongoClientFactoryBean mongoClientFactoryBean = mongoDBConfig.mongoClientFactoryBean();
        Assert.assertNotNull(mongoClientFactoryBean);

    }
}
