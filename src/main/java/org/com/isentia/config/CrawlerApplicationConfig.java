package org.com.isentia.config;

import org.com.isentia.customexceptios.PropertyLoaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "org.com.isentia")
public class CrawlerApplicationConfig {


}
