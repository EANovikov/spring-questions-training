package com.xevgnov.spring.beans.domain;

import com.xevgnov.spring.beans.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoolingTask implements Runnable {

    public static final int COOLING_TIMEOUT = 300;
    private final TemperatureService temperatureService;

    public CoolingTask(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < COOLING_TIMEOUT; i++) {
                log.info("Cooling is on ...");
                Thread.sleep(1000);
                temperatureService.decreaseTemperature();
            }
        } catch (InterruptedException e) {
            log.info("Cooling is finished ...");
        }
    }

}
