package com.xevgnov.scopes.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.scopes.service.RandomDateService;

import jakarta.annotation.PostConstruct;

// @Scope(BeanDefinition.SCOPE_SINGLETON)
@RestController
@RequestMapping(path = "/v1/singleton")
public class SingletonController {

    private static int counter;
    private RandomDateService randomDateService;

    public SingletonController(@Qualifier("singletonRandomDateService") RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return String.format("%s instance %d has date: %s",
        this.getClass().getSimpleName(), counter, randomDateService.getDate());
    }

}
