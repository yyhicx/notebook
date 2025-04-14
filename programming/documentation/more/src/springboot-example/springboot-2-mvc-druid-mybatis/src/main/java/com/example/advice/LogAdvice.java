package com.example.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LogAdvice {

  @Pointcut("execution(* com.example.controller.*.*(..))")
  public void pointcut() {}

  @Before(value = "pointcut()")
  public void before(JoinPoint joinPoint) {
    System.out.println("LogAdvice before...");
    System.out.println("JoinPoint: " + joinPoint);
  }
}
