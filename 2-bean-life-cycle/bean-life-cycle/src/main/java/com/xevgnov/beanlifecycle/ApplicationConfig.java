package com.xevgnov.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xevgnov.beanlifecycle.service.DemoService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@ComponentScan("com.xevgnov.service")
public class ApplicationConfig {
    
    @Bean(initMethod = "init")
    public DemoService demoService(){
        return new DemoService();
    }
    private void init(){
      log.info("call init method");
    }

}
