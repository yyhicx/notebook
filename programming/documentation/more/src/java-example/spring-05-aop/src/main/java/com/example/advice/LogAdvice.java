package com.example.advice;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Aspect 表示这个类是一个切面类
@Aspect
// @Component 注解保证这个切面类能够放入 IoC 容器
@Component
// @Order 注解设置切面类的执行顺序，值越小优先级越高
@Order(20)
public class LogAspect {

  // 类中切入点表达式重用
  @Pointcut("execution(* com.example.service.*.*.*(int,int))")
  public void declarePointcut() {}

  // @Before 注解：声明当前方法是前置通知方法
  // value 属性：指定切入点表达式，由切入点表达式控制当前通知方法要作用在哪一个目标方法上
  // 在前置通知方法形参位置声明一个 JoinPoint 类型的参数，Spring 就会将这个对象传入
  // 根据 JoinPoint 对象就可以获取目标方法名称、实际参数列表
  @Before(value = "declarePointcut()")
  public void printLogBeforeCore(JoinPoint joinPoint) {
    // 1. 通过 JoinPoint 对象获取目标方法签名对象
    // 方法的签名：一个方法的全部声明信息
    Signature signature = joinPoint.getSignature();

    // 2. 通过方法的签名对象获取目标方法的详细信息
    String methodName = signature.getName();
    System.out.println("methodName = " + methodName);

    int modifiers = signature.getModifiers();
    String modifierStr = Modifier.toString(modifiers);
    System.out.println("modifiers = " + modifierStr);

    String declaringTypeName = signature.getDeclaringTypeName();
    System.out.println("declaringTypeName = " + declaringTypeName);

    // 3. 通过 JoinPoint 对象获取外界调用目标方法时传入的实参列表
    Object[] args = joinPoint.getArgs();

    // 4. 由于数组直接打印看不到具体数据，所以转换为 List 集合
    List<Object> argList = Arrays.asList(args);

    System.out.println("[AOP前置通知] " + methodName + "方法开始了，参数列表：" + argList);
  }

  // @AfterReturning 注解标记返回通知方法
  // 在返回通知中获取目标方法返回值分两步：
  //   1. 在 @AfterReturning 注解中通过 returning 属性设置一个名称
  //   2. 使用 returning 属性设置的名称在通知方法中声明一个对应的形参
  @AfterReturning(
    value = "declarePointcut()",
    returning = "targetMethodReturnValue"
  )
  public void printLogAfterSuccess(JoinPoint joinPoint, int targetMethodReturnValue) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("[AOP返回通知] " + methodName + "方法成功结束了，返回值是：" + targetMethodReturnValue);
  }

  // @AfterThrowing 注解标记异常通知方法
  // 在异常通知中获取目标方法抛出的异常分两步：
  //   1. 在 @AfterThrowing 注解中声明一个 throwing 属性设定形参名称
  //   2. 使用 throwing 属性指定的名称在通知方法声明形参，Spring 会将目标方法抛出的异常对象从这里传给我们
  @AfterThrowing(
    value = "declarePointcut()",
    throwing = "targetMethodException"
  )
  public void printLogAfterException(JoinPoint joinPoint, Exception targetMethodException) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("[AOP异常通知] " + methodName + "方法抛异常了，异常类型是：" + targetMethodException.getClass().getName());
  }

  @After(value = "declarePointcut()")
  public void printLogFinallyEnd() {
    System.out.println("[AOP后置通知] 方法最终结束了");
  }

}
