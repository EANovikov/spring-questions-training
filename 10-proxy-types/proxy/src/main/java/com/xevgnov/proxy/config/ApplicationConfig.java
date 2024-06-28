package com.xevgnov.proxy.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public String asJson(Object object) {
        try {
            return objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert {} to JSON", object);
            return object.toString();
        }
    }

}
