package com.example.test;

import com.example.fb.HappyMachine;
import com.example.ioc.HappyComponent;
import com.example.lifecycle.JavaBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoCTest {

  @Test
  public void createIoCAndGetBean() {
    // 第一种方法创建 IoC 容器
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-01.xml", "spring-02.xml");

    // 第二种方法创建 IoC 容器
    // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
    // context.setConfigLocations("spring-01.xml", "spring-02.xml");
    // context.refresh();

    // 第一种方法获取 Bean
    // 返回值需要强制转换，不推荐
    // HappyComponent happyComponent = (HappyComponent) context.getBean("happyComponent");
    // happyComponent.doWork();

    // 第二种方法获取 Bean
    // 要求同类型（当前类、接口实现类）只能有一个对象交给 IoC 容器管理
    // HappyComponent happyComponent = context.getBean(HappyComponent.class);
    // happyComponent.doWork();

    // 第三种方法获取 Bean
    HappyComponent happyComponent = context.getBean("happyComponent", HappyComponent.class);
    happyComponent.doWork();
  }

  @Test
  public void lifeCycle() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-03.xml");
    JavaBean javaBean = context.getBean(JavaBean.class);
    context.close();  // 只有执行这条命令，javaBean 才会执行 destroy-method
  }

  @Test
  public void testFactoryBean() {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-04.xml");

    // 注意：直接根据声明 FactoryBean 的 id，获取的是 getObject 方法返回的对象
    HappyMachine happyMachine = context.getBean("happyMachine", HappyMachine.class);
    System.out.println(happyMachine);

    // 如果想要获取 FactoryBean 对象，直接在 id 前添加 & 符号即可！
    // &happyMachine 这是一种固定的约束
    Object bean = context.getBean("&happyMachine");
    System.out.println("bean = " + bean);
  }
}
