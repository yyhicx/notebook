package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("login")
  public Result login(@RequestBody User user) {
    return userService.login(user);
  }

  @GetMapping("getUserInfo")
  public Result userInfo(@RequestHeader("Authorization") String rawToken) {
    return userService.getUserInfo(rawToken.replace("Bearer ", ""));
  }

  @PostMapping("register")
  public Result register(@RequestBody User user) {
    return userService.register(user);
  }

  @PostMapping("checkUsername")
  public Result checkUsername(String username) {
    return userService.checkUsername(username);
  }

  @GetMapping("checkLogin")
  public Result checkLogin(@RequestHeader("Authorization") String rawToken) {
    return userService.checkLogin(rawToken.replace("Bearer ", ""));
  }
}
