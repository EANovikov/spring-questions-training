package com.xevgnov.spring.beans;

import com.xevgnov.spring.beans.service.SmartHomeController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();
        SmartHomeController smartHomeController = context.getBean(SmartHomeController.class);
        smartHomeController.switchOn(24);
    }
}