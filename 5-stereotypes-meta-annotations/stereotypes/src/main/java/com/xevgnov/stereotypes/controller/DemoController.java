package com.xevgnov.stereotypes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller")
public class DemoController {

    @GetMapping
    public String demo(){
      return "index";
    }
}
