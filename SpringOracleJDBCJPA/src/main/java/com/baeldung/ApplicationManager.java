package com.baeldung;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baeldung.configuration.AppConfiguration;

public class ApplicationManager {
    AnnotationConfigApplicationContext context = null;
    MainApplication application = null;

    public ApplicationManager() {
        try {
            context = new AnnotationConfigApplicationContext(AppConfiguration.class);
            application = context.getBean(MainApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void finalize() {
        if (context != null) {
          context.close();
        }
    }

    public AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public MainApplication getApplication() {
        return application;
    }
}
