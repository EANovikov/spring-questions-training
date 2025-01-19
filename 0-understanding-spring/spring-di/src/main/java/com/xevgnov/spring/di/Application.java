package com.xevgnov.spring.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xevgnov.spring.di.service.DateTimeService;
import com.xevgnov.spring.di.service.DateTimeServiceImpl;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();
        DateTimeService dateTimeService = context.getBean(DateTimeServiceImpl.class);
        dateTimeService.printDateTime();
    }
}