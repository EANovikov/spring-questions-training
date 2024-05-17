package com.xevgnov.beanlifecycle.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      var beanDefinitionNames = Arrays.asList(beanFactory.getBeanDefinitionNames());
      log.info("Bean factory has got: {}", beanDefinitionNames);
      Optional<BeanDefinition> demoServiceDefinitionOpt = Optional.ofNullable(
        beanFactory.getBeanDefinition("demoService"));
      if(demoServiceDefinitionOpt.isPresent()){
        BeanDefinition demoServiceDefinition = demoServiceDefinitionOpt.get();
        log.info("demoService bean definition contains:");
        log.info("factory method name: {}", demoServiceDefinition.getFactoryMethodName());
        log.info("scope: {}", demoServiceDefinition.getScope());
        log.info("init method: {}", demoServiceDefinition.getInitMethodName());
      }
    }

}