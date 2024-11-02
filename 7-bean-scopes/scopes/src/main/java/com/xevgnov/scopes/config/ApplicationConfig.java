package com.xevgnov.scopes.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xevgnov.scopes.custom.CacheBeanFactoryPostProcessor;

@ComponentScan("com.xevgnov.scopes")
@EnableAutoConfiguration
@Configuration
public class ApplicationConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CacheBeanFactoryPostProcessor();
    }

}
