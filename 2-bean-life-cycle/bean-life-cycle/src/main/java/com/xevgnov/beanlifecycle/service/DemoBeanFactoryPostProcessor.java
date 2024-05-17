package com.xevgnov.beanlifecycle.service;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      var beanDefinitionNames = Arrays.asList(beanFactory.getBeanDefinitionNames());
      log.info("Bean factory has got: {}", beanDefinitionNames);
    }

}