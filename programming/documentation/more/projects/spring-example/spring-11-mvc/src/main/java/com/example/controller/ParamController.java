package com.example.controller;

import com.example.entity.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"param"})
public class ParamController {

  @GetMapping(value = "value")
  @ResponseBody
  public String setupForm1(String name, int age) {
    System.out.println("name = " + name + ", age = " + age);
    return "name = " + name + ", age = " + age;
  }

  @GetMapping(value = "data")
  @ResponseBody
  public String setupForm2(@RequestParam("name") String name, @RequestParam("stuAge") int age) {
    System.out.println("name = " + name + ", age = " + age);
    return "name = " + name + ", age = " + age;
  }

  @GetMapping(value = "multi")
  @ResponseBody
  public String setupForm3(@RequestParam("name") List<String> names) {
    System.out.println("names = " + names);
    return names.toString();
  }

  @PostMapping(value = "entity")
  @ResponseBody
  public String setupForm4(User user) {
    System.out.println(user.toString());
    return user.toString();
  }
}
