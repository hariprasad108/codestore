package baeldung.hibernate.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

import baeldung.hibernate.application.PersistenceApplication;
import baeldung.persistence.dao.common.CartDAO;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-oracle.properties"})
@ComponentScan({ "baeldung.hibernate", "baeldung.persistence" })
public class HibernateAnnotationConfig {
    private final Logger logger = LoggerFactory.getLogger(PersistenceApplication.class);
    
    /** constructor */
    public HibernateAnnotationConfig() {
        super();
        logger.info("***HibernateAnnotationConfig from Constructor ***");
    }
    
    @Autowired
    private Environment env;

    private static final String DB_DRIVER_CLASS_KEY = "jdbc.driver_class_name";
    private static final String DB_URL_KEY = "jdbc.url";
    private static final String DB_PASSWORD_KEY = "jdbc.password";
    private static final String DB_USER_KEY = "jdbc.username";
    
    @Bean
    public CartDAO getCartDAO() {
        return new CartDAO();
    }
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
       entityManager.setDataSource(getDataSource());
       entityManager.setPackagesToScan(new String[] {"com.baeldung.persistence"});
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       entityManager.setJpaVendorAdapter(vendorAdapter);
       entityManager.setJpaProperties(getHibernateProperties());

       return entityManager;
    }
    
/*  @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        final LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(getDataSource());
        sessionFactory.scanPackages(new String[] {"baeldung.persistence"});
        sessionFactory.setProperties(getHibernateProperties());
        logger.info("+++ SessionFactory is greating you from bean initialization process +++");
        return sessionFactory.buildSessionFactory();
    }*/
    
   @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] {"baeldung.persistence.hibernate.model"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        logger.info("+++ SessionFactory is greating you from bean initialization process +++");
        return sessionFactory;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getRequiredProperty(DB_DRIVER_CLASS_KEY)));
        dataSource.setUrl(Preconditions.checkNotNull(env.getRequiredProperty(DB_URL_KEY)));
        dataSource.setUsername(Preconditions.checkNotNull(env.getRequiredProperty(DB_PASSWORD_KEY)));
        dataSource.setPassword(Preconditions.checkNotNull(env.getRequiredProperty(DB_USER_KEY)));
        /*dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setUsername("test");
        dataSource.setPassword("test");*/
        return dataSource;
    }
    
    @Bean(name = "hibernateTransactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.dialect")));
        properties.put(AvailableSettings.HBM2DDL_AUTO, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.hbm2ddl.auto")));
        properties.put(AvailableSettings.SHOW_SQL, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.show_sql")));
        properties.put(AvailableSettings.FORMAT_SQL, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.format_sql")));
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.batch.size")));
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, Preconditions.checkNotNull(env.getRequiredProperty("hibernate.current.session.context.class")));
        // Envers properties
        //properties.setProperty("org.hibernate.envers.audit_table_suffix", env.getProperty("envers.audit_table_suffix"));
        return properties;
    }
}