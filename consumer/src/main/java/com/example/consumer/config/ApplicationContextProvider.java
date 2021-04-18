package com.example.consumer.config;

import com.example.consumer.engine.Consumer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yamraaj
 * this class is responsible for running consumer
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
        Consumer consumer = ac.getBean(Consumer.class);
        consumer.initConsumer();
    }
}