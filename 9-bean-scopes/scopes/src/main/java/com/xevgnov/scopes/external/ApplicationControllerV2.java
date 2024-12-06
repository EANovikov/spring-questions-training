package com.xevgnov.scopes.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.xevgnov.scopes.service.RandomDateService;

//to see the difference between application and singleton
// /v2/application vs /v1/application
@ApplicationScope
@RestController
@RequestMapping(path = "/application")
public class ApplicationControllerV2 {

    private static int counter;
    private RandomDateService randomDateService;

    public ApplicationControllerV2(@Qualifier("applicationRandomDateService") RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return String.format("%s instance %d has date: %s",
        this.getClass().getSimpleName(), counter, randomDateService.getDate());
    }

}
