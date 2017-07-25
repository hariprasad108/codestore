package com.baeldung;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import com.baeldung.configuration.AppConfiguration;

/** class to initialize of context cannot be component */
public class ApplicationManager  implements WebApplicationInitializer {
    AnnotationConfigApplicationContext context = null;
    PersistenceApplication application = null;

    public ApplicationManager() {
        super();
        try {
            context = new AnnotationConfigApplicationContext(AppConfiguration.class);
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
    }

    public AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public PersistenceApplication getApplication() {
        return application;
    }
}
