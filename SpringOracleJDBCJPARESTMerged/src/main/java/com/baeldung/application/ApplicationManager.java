package com.baeldung.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import com.baeldung.configuration.PersistenceConfig;

public class ApplicationManager  implements WebApplicationInitializer {
    AnnotationConfigApplicationContext context = null;
    PersistenceApplication application = null;

    public ApplicationManager() {
        super();
        try {
            context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
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
