package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "test")
public class TestController {

  @GetMapping(value = "home")
  public String home(Model model) {
    System.out.println("TestController.home");
    model.addAttribute("msg", "Hello, SpringMVC!");
    return "home";
  }

}
