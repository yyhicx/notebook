package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "thymeleaf")
public class ThymeleafController {
  @GetMapping
  public ModelAndView index() {
    String message = "Hello, Thymeleaf!";
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", message);
    modelAndView.setViewName("index");
    return modelAndView;
  }
}
