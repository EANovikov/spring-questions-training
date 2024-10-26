package com.xevgnov.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.xevgnov.beanlifecycle.service.DemoService;
import com.xevgnov.beanlifecycle.service.InnerDemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan("com.xevgnov.beanlifecycle.service")
public class ApplicationConfig {

  // Changes bean's lifesycle
  // @Scope("prototype")
  @Scope("singleton")
  @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
  public DemoService demoService(InnerDemoService innerDemoService) {
    log.info("about to create the bean DemoService");
    return new DemoService(innerDemoService);
  }

}
