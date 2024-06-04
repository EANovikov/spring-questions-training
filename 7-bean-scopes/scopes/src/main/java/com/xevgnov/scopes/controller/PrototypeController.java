package com.xevgnov.scopes.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.config.BeanDefinition;
import com.xevgnov.scopes.service.RandomDateService;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping(path = "/prototype")
public class PrototypeController {

    private static int counter;
    private RandomDateService randomDateService;

    public PrototypeController(RandomDateService randomDateService) {
        this.randomDateService = randomDateService;
        counter++;
    }

    @GetMapping
    public String getDate(){
       return counter + " -> " + randomDateService.getDate();
    }

}
