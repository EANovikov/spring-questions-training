package com.xevgnov.scopes.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.scopes.service.RandomDateService;

@Scope("cache")
@RestController
@RequestMapping(path = "/custom")
public class CustomScopeController {

    private static int counter;
    private RandomDateService randomDateService;

    public CustomScopeController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return counter + " -> " + randomDateService.getDate();
    }

}
