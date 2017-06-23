package test.java.com.baeldung.persistence.dao.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.baeldung.persistence.dao.IFooDao;
import com.baeldung.persistence.dao.impl.FooHibernateDao;
import com.baeldung.persistence.model.Foo;
import com.baeldung.spring.PersistenceConfig;

public class HibernateTestMain {
    private final Logger log = LoggerFactory.getLogger(HibernateTestMain.class);
    
    AnnotationConfigApplicationContext ctx;

    private SessionFactoryImpl sessionFactory;

    private Session session;
    private IFooDao fooDao;

    public HibernateTestMain() {
        ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        sessionFactory = (SessionFactoryImpl) ctx.getBean("sessionFactory");
        session = sessionFactory.openSession();
        log.info("Session opened successfuly");
        //fooDao = (IFooDao) ctx.getBean("fooHibernateDao");
    }
    
    public void finalize() {
        //session.close();        
    }

    public static void main(String[] args) {
        HibernateTestMain ht = new HibernateTestMain();
        Foo foo = new Foo(RandomStringUtils.randomAlphabetic(1).toUpperCase()
            + RandomStringUtils.randomAlphabetic(10).toLowerCase());
        ht.session.persist(foo);
        foo = new Foo(RandomStringUtils.randomAlphabetic(1).toUpperCase()
            + RandomStringUtils.randomAlphabetic(10).toLowerCase());
        ht.session.persist(foo);
        ht.log.info(foo.toString());
        
        foo = new Foo(RandomStringUtils.randomAlphabetic(1).toUpperCase());
        //ht.fooDao.create(foo);
        //Foo result = ht.fooDao.findOne(1);
        //ht.log.info(result.toString());
        ht.log.info("Finished");
    }
}