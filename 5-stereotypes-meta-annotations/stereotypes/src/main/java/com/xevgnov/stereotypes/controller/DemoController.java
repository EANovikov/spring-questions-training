package com.xevgnov.stereotypes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String demo(Model model){
      model.addAttribute("name", this.getClass().getName());
      return "demo";
    }
}
