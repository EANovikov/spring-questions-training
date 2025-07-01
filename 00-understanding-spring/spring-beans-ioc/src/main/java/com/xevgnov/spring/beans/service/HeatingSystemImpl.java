package com.xevgnov.spring.beans.service;

import com.xevgnov.spring.beans.domain.HeatingTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class HeatingSystemImpl implements HeatingSystem {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final HeatingTask heatingTask;

    public HeatingSystemImpl(HeatingTask heatingTask) {
        this.heatingTask = heatingTask;
    }

    @Override
    public void heatingOn() {
       executorService.submit(heatingTask);
        log.info("Heating status: ON");
    }

    @Override
    public void heatingOff() {
        executorService.shutdownNow();
        log.info("Heating status: OFF");
    }

}
