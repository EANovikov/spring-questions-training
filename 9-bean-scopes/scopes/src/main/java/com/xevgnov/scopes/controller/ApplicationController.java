package com.xevgnov.scopes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.xevgnov.scopes.service.RandomDateService;

// To see the difference between Application and Singletone swithch RandomDateService to Singltone
// Use both ApplicationController and ApplicationControllerV2 which refer to different contexts
@ApplicationScope
@RestController
@RequestMapping(path = "/application")
public class ApplicationController {

    private static int counter;
    private RandomDateService randomDateService;

    public ApplicationController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return counter + " -> " + randomDateService.getDate();
    }

}
