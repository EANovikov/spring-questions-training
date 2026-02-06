package com.xevgnov.spring.beans;

import com.xevgnov.spring.beans.domain.CoolingTask;
import com.xevgnov.spring.beans.domain.HeatingTask;
import com.xevgnov.spring.beans.service.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();
        SmartHomeController smartHomeController = context.getBean(SmartHomeController.class);
        smartHomeController.switchOn(22);
    }

// Manual device usage: no Spring needed
//    public static void main(String[] args) {
//        TemperatureService temperatureService = new TemperatureServiceImpl();
//        CoolingTask coolingTask = new CoolingTask(temperatureService);
//        HeatingTask heatingTask = new HeatingTask(temperatureService);
//        AirConditionerSystem airConditionerSystem = new AirConditionerSystemImpl(coolingTask);
//        HeatingSystem heatingSystem  = new HeatingSystemImpl(heatingTask);
//        heatingSystem.heatingOn();
//    }
}