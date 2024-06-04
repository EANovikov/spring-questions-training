package com.xevgnov.scopes.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.beans.factory.config.BeanDefinition;
import com.xevgnov.scopes.service.RandomDateService;

@SessionScope
@RestController
@RequestMapping(path = "/session")
public class SessionController {

    private static int counter;
    private RandomDateService randomDateService;

    public SessionController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate(){
       return counter + " -> " + randomDateService.getDate();
    }

}
