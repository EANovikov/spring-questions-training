package com.xevgnov.spring.beans.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final AtomicInteger temperature = new AtomicInteger(ThreadLocalRandom.current().nextInt(5, 35));

    @Override
    public int getTemperature() {
        return temperature.get();
    }

    @Override
    public void increaseTemperature() {
        int currentTemperature = temperature.incrementAndGet();
        log.info("Current temperature after increasing is: {}", currentTemperature);
    }


    @Override
    public void decreaseTemperature() {
        int currentTemperature = temperature.decrementAndGet();
        log.info("Current temperature after decreasing is: {}", currentTemperature);
    }
}
