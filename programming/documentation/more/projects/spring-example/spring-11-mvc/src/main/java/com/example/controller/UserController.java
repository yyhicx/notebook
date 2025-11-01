package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//public class UserController {
//
//  @RequestMapping(value = {"/user/login"})
//  @ResponseBody
//  public String login() {
//    System.out.println("UserController.login");
//    return "Login Success!";
//  }
//
//  @RequestMapping(value = {"/user/register"})
//  @ResponseBody
//  public String register() {
//    System.out.println("UserController.register");
//    return "Register Success!";
//  }
//
//}

@Controller
@RequestMapping(value = {"user"})
public class UserController {

  @RequestMapping(value = {"login"}, method = RequestMethod.POST)
  @ResponseBody
  public String login() {
    System.out.println("UserController.login");
    return "Login Success!";
  }

  @RequestMapping(value = {"register"}, method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String register() {
    System.out.println("UserController.register");
    return "Register Success!";
  }

  @RequestMapping(value = {"logout"})
  @ResponseBody
  public String logout() {
    System.out.println("UserController.logout");
    return "Logout Success!";
  }

}
