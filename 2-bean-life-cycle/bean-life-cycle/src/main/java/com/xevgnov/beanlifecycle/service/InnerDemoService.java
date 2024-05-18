package com.xevgnov.beanlifecycle.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InnerDemoService {

    public InnerDemoService() {
        log.info("call constructor");
    }

    public void demo() {
        log.info("Bean usage phase");
    }
}
