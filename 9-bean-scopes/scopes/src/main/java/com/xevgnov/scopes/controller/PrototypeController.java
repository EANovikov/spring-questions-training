package com.xevgnov.scopes.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.scopes.service.RandomDateService;

//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping(path = "/v1/prototype")
public class PrototypeController {

    private static int counter;
    private RandomDateService randomDateService;

    public PrototypeController(@Qualifier("prototypeRandomDateService") RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    // @GetMapping
    // public String getDate() {
    //     return String.format("%s instance %d has date: %s",
    //     this.getClass().getSimpleName(), counter, randomDateService.getDate());
    // }


    //To see the difference between prototype and request scopes
    @GetMapping
    public String getDate() {
        return String.format("%s instance %d has dates: %s & %s",
        this.getClass().getSimpleName(), counter, randomDateService.getDate(), randomDateService.getDate());
    }
}
