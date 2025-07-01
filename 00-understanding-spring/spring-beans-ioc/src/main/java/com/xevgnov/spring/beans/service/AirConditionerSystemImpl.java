package com.xevgnov.spring.beans.service;

import com.xevgnov.spring.beans.domain.CoolingTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AirConditionerSystemImpl implements AirConditionerSystem {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ThreadLocal<Future<String>> cooling = new ThreadLocal<>();

    private final CoolingTask coolingTask;

    public AirConditionerSystemImpl(CoolingTask coolingTask) {
        this.coolingTask = coolingTask;
    }

    @Override
    public void coolingOn() {
        executorService.submit(coolingTask);
        log.info("Cooling status: ON");
    }

    @Override
    public void coolingOff() {
        executorService.shutdownNow();
        log.info("Cooling status: OFF");
    }
}
