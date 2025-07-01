package com.xevgnov.spring.beans.domain;

import com.xevgnov.spring.beans.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeatingTask implements Runnable {

    public static final int HEATING_TIMEOUT = 300;
    private final TemperatureService temperatureService;

    public HeatingTask(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < HEATING_TIMEOUT; i++) {
                log.info("Heating is on ...");
                Thread.sleep(1000);
                temperatureService.increaseTemperature();
            }
        } catch (InterruptedException e) {
            log.info("Heating is finished ...");
        }
    }
}

