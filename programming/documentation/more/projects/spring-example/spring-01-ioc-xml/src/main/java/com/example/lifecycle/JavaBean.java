package com.example.lifecycle;

public class JavaBean {
  // 周期方法：方法名随意，但是方法必须是 public void
  public void init() {
    System.out.println("Init");
  }

  public void destroy() {
    System.out.println("Destroy");
  }
}
