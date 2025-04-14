package com.example.controller;

import com.example.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"json"})
public class JsonController {

  @PostMapping(value = {"entity"})
  @ResponseBody
  public String setupForm(@RequestBody User user) {
    System.out.println(user.toString());
    return user.toString();
  }

}
