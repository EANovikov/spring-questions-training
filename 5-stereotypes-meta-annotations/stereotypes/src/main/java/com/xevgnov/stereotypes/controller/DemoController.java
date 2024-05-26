package com.xevgnov.stereotypes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/gui")
public class DemoController {

  private static int counter;

    @GetMapping("/demo")
    public String demo(Model model){
      model.addAttribute("count", counter++);
      return "demo";
    }
}
