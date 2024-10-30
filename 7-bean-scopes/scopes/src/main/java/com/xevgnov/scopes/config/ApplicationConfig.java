package com.xevgnov.scopes.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xevgnov.scopes.custom.CacheBeanFactoryPostProcessor;

@Configuration
public class ApplicationConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CacheBeanFactoryPostProcessor();
    }

}
