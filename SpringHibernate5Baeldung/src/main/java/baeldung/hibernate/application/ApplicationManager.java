package baeldung.hibernate.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import baeldung.hibernate.config.HibernateAnnotationConfig;


public class ApplicationManager  implements WebApplicationInitializer {
    private final Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    AnnotationConfigApplicationContext context = null;
    PersistenceApplication application = null;

    public ApplicationManager() {
        super();
        logger.info("*** ApplicationManager Constructor ***");
        try {
            context = new AnnotationConfigApplicationContext(HibernateAnnotationConfig.class);
            application = context.getBean(PersistenceApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void finalize() {
        if (context != null) {
          context.close();
        }
    }
    
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        logger.info("--- Context on Startup: " + context.getContextPath());
    }

    public AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public PersistenceApplication getApplication() {
        return application;
    }
}
