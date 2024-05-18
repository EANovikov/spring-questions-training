package com.xevgnov.beanlifecycle.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoService implements InitializingBean, DisposableBean {

    private InnerDemoService innerDemoService;

    public DemoService(InnerDemoService innerDemoService) {
        log.info("call constructor");
        this.innerDemoService = innerDemoService;
    }

    public void demo() {
        innerDemoService.demo();
        log.info("Bean usage phase");
    }

    @PostConstruct
    public void setUp() {
        log.info("call PostConstruct annotated method");
    }

    @PreDestroy
    public void tearDown() {
        log.info("call PreDestroy annotated method");
    }

    public void initBean() {
        log.info("call init method");
    }

    public void destroyBean() {
        log.info("call destroy method");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("call InitializingBean.afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.info("call DisposableBean.destroy");
    }

}