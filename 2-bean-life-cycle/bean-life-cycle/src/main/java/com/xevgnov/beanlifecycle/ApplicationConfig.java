package com.xevgnov.beanlifecycle;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.xevgnov.beanlifecycle.service.DemoBeanFactoryPostProcessor;
import com.xevgnov.beanlifecycle.service.DemoService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@ComponentScan("com.xevgnov.service")
public class ApplicationConfig {
 

    // @Scope("prototype")
    @Bean(initMethod = "initBean", destroyMethod = "destroyBean")
    public DemoService demoService(){
      log.info("about to create the bean DemoService");
        return new DemoService();
    }
    
    @Bean
    public static BeanFactoryPostProcessor processBeanDefinitions() {
      return new DemoBeanFactoryPostProcessor();
    }

}
