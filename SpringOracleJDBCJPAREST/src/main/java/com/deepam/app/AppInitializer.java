package com.deepam.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.baeldung.PersistenceApplication;
import com.baeldung.configuration.AppConfiguration;
import com.baeldung.configuration.PersistenceConfig;
import com.deepam.spring.config.AppRestConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final Logger logger = LoggerFactory.getLogger(AppInitializer.class);
    
    Class[] classes = new Class[] {AppRestConfig.class};
    
    public AppInitializer() {
        super();
        logger.info("***+++ AppInitializer from SpringOracleJDBCJPAREST +++***");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class[] getRootConfigClasses() {
        return classes;
    }

    @Override
    protected Class[] getServletConfigClasses() {
        return classes;
    }

}