package com.xevgnov.beanlifecycle.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoService {
    
    public void demo(){
        log.info("Bean usage phase");
    }

    @PostConstruct
    public void setUp(){
        log.info("Bean PostConstruct call");
    }

    @PreDestroy
    public void tearDown(){
        log.info("Bean PreDestroy call");
    }

}
