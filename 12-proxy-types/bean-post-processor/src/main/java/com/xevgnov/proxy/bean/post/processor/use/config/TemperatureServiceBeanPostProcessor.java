package com.xevgnov.proxy.bean.post.processor.use.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.xevgnov.proxy.bean.post.processor.use.service.TemperatureService;
import com.xevgnov.proxy.bean.post.processor.use.service.TemperatureServiceToCelsiusProxy;

@Component
public class TemperatureServiceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TemperatureService) {
            // returning Proxy class instead of an original one
            return new TemperatureServiceToCelsiusProxy((TemperatureService) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
}
