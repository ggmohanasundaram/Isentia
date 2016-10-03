import org.com.isentia.config.CrawlerApplicationConfig;
import org.com.isentia.service.crawl.NewsContentCrawler;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CrawlerServiceStandAloneTest {

    @Ignore /** This is Ene to End/stand alone test for Crawler Application
     Kindly comment the @Ignore to run**/
    @Test
    public void testCrawler() throws Exception {
        try {
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(CrawlerApplicationConfig.class);
            NewsContentCrawler newsContentCrawler = (NewsContentCrawler) context.getBean("newsContentCrawler");
            newsContentCrawler.extractAndStoreWebContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
