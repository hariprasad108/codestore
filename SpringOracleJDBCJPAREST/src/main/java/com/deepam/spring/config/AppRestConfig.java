package com.deepam.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.baeldung.ApplicationManager;
import com.baeldung.configuration.AppConfiguration;
import com.baeldung.configuration.PersistenceConfig;
import com.deepam.app.AppInitializer;

@Configuration
// @EnableJpaRepositories
@EnableWebMvc
/*@Import({ AppConfiguration.class })*/
@ComponentScan(basePackages = { "com.deepam.spring.controller" })
public class AppRestConfig extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(AppRestConfig.class);

    public AppRestConfig() {
        super();
        logger.info("***AppRestConfig from SpringOracleJDBCJPAREST ***");
    }

    /*@Override TODO
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");*/
        
    /*@Bean
    public ApplicationManager applicationManager() {
        return new ApplicationManager();
    }*/
}