package com.xevgnov.spring.beans.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmartHomeControllerImpl implements SmartHomeController {

    private final HeatingSystem heatingSystem;
    private final AirConditionerSystem airConditionerSystem;
    private final TemperatureService temperatureService;

    public SmartHomeControllerImpl(HeatingSystem heatingSystem, AirConditionerSystem airConditionerSystem, TemperatureService temperatureService) {
        this.heatingSystem = heatingSystem;
        this.airConditionerSystem = airConditionerSystem;
        this.temperatureService = temperatureService;
    }

    @Override
    public void switchOff() {
        heatingSystem.heatingOff();
        airConditionerSystem.coolingOff();
        log.info("SmartHomeController: finished the job");
    }

    @Override
    public void switchOn(int desiredTemperature) {
        int currentTemperature = temperatureService.getTemperature();
        log.info("SmartHomeController: current temperature is {}, desired temperature is {}", currentTemperature, desiredTemperature);
        if (currentTemperature > desiredTemperature) {
            log.info("SmartHomeController: about to on cooling");
            airConditionerSystem.coolingOn();
            heatingSystem.heatingOff();
        } else if (currentTemperature < desiredTemperature) {
            log.info("SmartHomeController: about to on heating");
            heatingSystem.heatingOn();
            airConditionerSystem.coolingOff();
        } else {
            log.info("SmartHomeController: no temperature change needed");
        }
        while (currentTemperature != desiredTemperature) {
            currentTemperature = temperatureService.getTemperature();
        }
        log.info("SmartHomeController: current temperature is equal to the desired temperature");
        switchOff();
    }
}
