package com.xevgnov.scopes.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.xevgnov.scopes.service.RandomDateService;

//to see the difference between Request scope and Prototype scope switch RandomDateService to SCOPE_SINGLETON 
// RandomDateService will keep a single istance while RequestController will keep creating its new instances
//@RequestScope
@RestController
@RequestMapping(path = "/v1/request")
public class RequestController {

    private static int counter;
    private RandomDateService randomDateService;

    public RequestController(@Qualifier("requestRandomDateService") RandomDateService randomDateService) {
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
