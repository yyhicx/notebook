package com.example.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class ExamplePointcut {
  // 仅仅提取出管理事物的切入点表达式用于示例
  @Pointcut(value = "execution(* com.example.service.*.*.*(int,int))")
  public void transactionPointcut(){}
}
