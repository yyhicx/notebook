package com.example.di;

class UserDao {
}

// 基于构造函数的依赖注入
public class UserService {
  private UserDao userDao;
  private int age;
  private String name;

  public UserService(UserDao userDao, int age, String name) {
    this.userDao = userDao;
    this.age = age;
    this.name = name;
  }
}
