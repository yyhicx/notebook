package com.example.controller;

import com.example.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class HelloController {

  @Autowired
  private DataSourceProperties dataSourceProperties;

  @GetMapping
  public String hello(){
    return "Hello, Spring Boot 3!";
  }

  @GetMapping(value = "jdbc")
  public String helloJDBC(){
    return dataSourceProperties.toString();
  }
}
