package com.xevgnov.scopes.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.xevgnov.scopes.service.RandomDateService;

//@SessionScope
@RestController
@RequestMapping(path = "/v1/session")
public class SessionController {

    private static int counter;
    private RandomDateService randomDateService;

    public SessionController(@Qualifier("sessionRandomDateService") RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return String.format("%s instance %d has date: %s",
        this.getClass().getSimpleName(), counter, randomDateService.getDate());
    }

}
