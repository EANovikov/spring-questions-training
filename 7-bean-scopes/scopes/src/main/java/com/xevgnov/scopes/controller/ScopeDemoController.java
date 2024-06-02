package com.xevgnov.scopes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.scopes.service.RandomDayOfWeekProviderService;

@RestController
public class ScopeDemoController {
    
    private RandomDayOfWeekProviderService dayService;

    public ScopeDemoController(RandomDayOfWeekProviderService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/{number}")
    public List<String> getDays(@PathVariable int number){
       List<String> days = new ArrayList<>();
       while(number > 0){
        days.add(dayService.getDay());
        number--;
       }
       return days;
    }

}
