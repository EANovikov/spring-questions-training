package com.xevgnov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xevgnov.service.DateTimeService;
import com.xevgnov.service.DateTimeServiceImpl;

public class Application {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ApplicationConfig.class)) {
            DateTimeService dateTimeService = context.getBean(DateTimeServiceImpl.class);
            //DateTimeService dateTimeService = context.getBean("dateTimeServiceImpl", DateTimeServiceImpl.class);
            //DateTimeService dateTimeService = (DateTimeService) context.getBean("dateTimeServiceImpl");
            dateTimeService.printDateTime();
            context.close();
            context.registerShutdownHook();
        }
    }
}