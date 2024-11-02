package com.xevgnov.scopes.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.xevgnov.scopes.service.RandomDateService;

@ApplicationScope
@RestController
@RequestMapping(path = "/application-v2")
public class ApplicationControllerV2 {

    private static int counter;
    private RandomDateService randomDateService;

    public ApplicationControllerV2(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate() {
        return counter + " -> " + randomDateService.getDate();
    }

}
