package com.xevgnov.prototype.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.config.BeanDefinition;
import com.xevgnov.prototype.service.RandomDateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Scope(BeanDefinition.SCOPE_SINGLETON)
@RestController
@RequestMapping(path = "/singleton")
public class SingletonController {

    private RandomDateService randomDateService;

    public SingletonController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
    }

    @GetMapping
    public String getDate(){
       return randomDateService.getDate();
    }

}
