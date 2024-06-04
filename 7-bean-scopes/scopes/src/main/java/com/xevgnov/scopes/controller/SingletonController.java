package com.xevgnov.scopes.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.config.BeanDefinition;
import com.xevgnov.scopes.service.RandomDateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Scope(BeanDefinition.SCOPE_SINGLETON)
@RestController
@RequestMapping(path = "/singleton")
public class SingletonController {
    
    private static int counter;
    private RandomDateService randomDateService;

    public SingletonController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate(){
       return counter + " -> " + randomDateService.getDate();
    }

}
