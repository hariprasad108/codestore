package com.baeldung.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value={"com.baeldung"})
@Import({PersistenceConfig.class})
public class AppConfiguration {
    private final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
    
    public AppConfiguration() {
        super();
        logger.info("***AppConfiguration from SpringOracleJDBCJPA ***");
    }
}