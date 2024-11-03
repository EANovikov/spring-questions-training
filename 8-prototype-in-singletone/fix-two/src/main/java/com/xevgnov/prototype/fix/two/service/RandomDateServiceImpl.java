package com.xevgnov.prototype.fix.two.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(value = BeanDefinition.SCOPE_PROTOTYPE,
       proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class RandomDateServiceImpl implements RandomDateService {

    private LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public String getDate() {
        return localDateTime.toString();
    }

}
