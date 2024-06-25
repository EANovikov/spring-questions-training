package com.xevgnov.proxy.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApplicationConfiguration {
  @Bean
  public ObjectMapper mapper(){
    return new ObjectMapper();
  }
}
