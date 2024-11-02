package com.xevgnov.scopes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.xevgnov.scopes.service.RandomDateService;

//to see the difference between Request scope and Prototype scope switch RandomDateService to SCOPE_SINGLETON 
// RandomDateService will keep a single istance while RequestController will keep creating its new instances
@RequestScope
@RestController
@RequestMapping(path = "/request")
public class RequestController {

    private static int counter;
    private RandomDateService randomDateService;

    public RequestController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate(){
       return counter + " -> " + randomDateService.getDate();
    }

}
