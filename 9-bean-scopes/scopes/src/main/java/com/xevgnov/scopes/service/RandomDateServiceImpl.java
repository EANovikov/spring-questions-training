package com.xevgnov.scopes.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

// 1) switch to SCOPE_SINGLETON to see the same output for /application-v2 and /application
// These controllers uses different context 
// which demonstrates the different behawiour for Application scope VS Singletone scope

// 2) switch to SCOPE_SINGLETON to see the difference between Request scope and Prototype scope
// RandomDateService will keep a single istance while RequestController will keep creating its new instances

//@Scope(BeanDefinition.SCOPE_SINGLETON)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class RandomDateServiceImpl implements RandomDateService {

    private LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public String getDate() {
        return localDateTime.toString();
    }

}
