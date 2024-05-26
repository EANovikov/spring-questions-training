package com.xevgnov.stereotypes.controller;

import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.stereotypes.domain.CounterDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
public class DemoRestController {

    private static int counter;

    @GetMapping("/demo")
    public CounterDto getMethodName() {
        return CounterDto.builder()
        .info("REST controller called")
        .count(counter++)
        .build();
    }
    
}
