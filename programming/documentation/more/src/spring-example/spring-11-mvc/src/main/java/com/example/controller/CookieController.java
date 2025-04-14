package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cookie")
public class CookieController {

  @GetMapping("/data")
  @ResponseBody
  public String data(@CookieValue("JSESSIONID") String cookie) {
    System.out.println("Cookie value: " + cookie);
    return cookie;
  }

}
