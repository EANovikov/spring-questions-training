package com.xevgnov.proxy.bean.post.processor.use.config;

import com.xevgnov.proxy.bean.post.processor.use.service.TemperatureService;
import com.xevgnov.proxy.bean.post.processor.use.service.TemperatureServiceToCelsiusProxy;

@Component
public class TemperatureServiceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TemperatureService) {
            return new TemperatureServiceToCelsiusProxy((TemperatureService) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
}
