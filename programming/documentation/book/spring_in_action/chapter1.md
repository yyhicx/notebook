# Spring起步

*   赫拉克里特：唯一不变的就是变化。
*   过去最常见的应用形式：基于浏览器的Web应用，后端由关系型数据库作为支撑。现在的应用形式：开发面向云的由`微服务`组成的应用，这些应用将数据保存到各种类型的数据库中；同时支持`反应式编程`，它致力于通过非阻塞操作提供更好的扩展性。

1.  [什么是Spring](#什么是spring)
2.  [初始化并编写Spring应用](#初始化并编写spring应用)
3.  [俯瞰Spring风景线](#俯瞰spring风景线)

## 什么是Spring

Spring及其一系列的相关库提供了Web框架、各种持久化可选方案、安全框架、与其他系统集成、运行时监控、微服务支持、反应式编程模型，以及众多现代应用开发所需的其他特性。

任何实际的应用程序都是由很多组件组成的，每个组件负责整个应用功能的一部分，这些组件需要与其他的应用元素协调以完成自己的任务。当应用程序运行时，需要以某种方式创建并引入这些组件。

Spring的核心是提供了一个容器（`Container`）。它们通常被称为`Spring应用上下文`（`Spring Application Context`），会创建和管理应用的组件。这些组件也可以称为`bean`，会在Spring应用上下文中装配起来，从而形成一个完整的应用程序。

将`bean`装配在一起的行为是通过一种基于依赖注入（`Dependency Injection`）的模式实现的。

Spring提供了自动配置机制，完成了大量繁琐的配置工作，让开发者可以专注于业务逻辑的实现。

## 初始化并编写Spring应用

Spring项目的结构：

*   `src/main/java`：包含应用的源代码。
    *   `XXXApplication.java`：这是Spring Boot主类，它会启动该项目。

        ```java
        package com.example.tacocloud;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

        // @SpringBootApplication 注解明确这是一个 Spring Boot 应用，同时它是一个组合注解，包含了 @SpringBootConfiguration、@EnableAutoConfiguration 和 @ComponentScan 三个注解。
        // @SpringBootConfiguration 注解用于声明当前类是一个 Spring Boot 配置类。
        // @EnableAutoConfiguration 注解用于启用 Spring Boot 的自动配置机制，它会根据应用的类路径、配置等条件，自动配置 Spring 应用。
        // @ComponentScan 注解用于启用 Spring 组件扫描，它会扫描当前包及其子包下面的注解了 @Component、@Service、@Repository 等注解的类。
        @SpringBootApplication
        public class TacoCloudApplication {
          public static void main(String[] args) {
            SpringApplication.run(TacoCloudApplication.class, args);
          }
        }
        ```

    *   `XXXController.java`：

        ```java
        package com.example.tacocloud;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;

        // @Controller 主要目的是让组件扫描将这个类识别为一个组件，并创建一个 HomeController 实例作为 Spring 应用上下文中的 bean。
        @Controller
        public class HomeController {
          // @GetMapping 注解用于映射 HTTP GET 请求，并将其映射到一个方法上。
          @GetMapping("/")
          public String home() {
            return "home";
          }
        }
        ```

*   `src/main/resources`：包含应用的资源文件。
    *   `static`：在这个文件夹下，你可以存放任何为浏览器提供服务的静态内容（图片、CSS、JavaScript文件等），该文件夹初始为空。
    *   `templates`：这个文件夹中存放用来渲染内容到浏览器的模板文件，这个文件夹初始为空。

        ```html
        <!-- 使用 Thymeleaf 模板引擎渲染 HTML -->
        <!-- home.html -->
        <!DOCTYPE html>
        <html xmlns = "http://www.w3.org/1999/xhtml"
              xmlns:th = "http://www.thymeleaf.org">
          <head>
            <title>Taco Cloud</title>
          </head>
          <body>
            <h1>Welcome to...</h1>
            <img th:src = "@{/images/TacoCloud.png}"/>
          </body>
        </html>
        ```

    *   `application.properties`：这是Spring Boot配置文件，它包含了应用的配置信息。
*   `src/test/java`：包含应用的测试代码。
    *   `XXXApplicationTests.java`：这是Spring Boot测试类，它会测试该项目。

        ```java
        package com.example.tacocloud;

        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;

        @SpringBootTest
        class TacoCloudApplicationTests {
          @Test
          void contextLoads() {}
        }
        ```

    *   `XXXControllerTest.java`：

        ```java
        package com.example.tacocloud;

        import static org.hamcrest.Matchers.containsString;
        import static
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static
            org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
        import static
            org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        import static
            org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.test.web.servlet.MockMvc;

        @WebMvcTest(HomeController.class)
        public class HomeControllerTest {
          @Autowired
          private MockMvc mockMvc;

          @Test
          public void testHomePage() throws Exception {
            mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // 验证响应状态码为 200
                .andExpect(view().name("home"))  // 验证视图名称为 "home"
                .andExpect(content().string(containsString("Welcome to...")));  // 验证响应内容包含 "Welcome to..."
          }
        }
        ```

*   `mvnw`和`mvnw.cmd`：这是`Maven`包装器（`wrapper`）脚本。借助这些脚本，即使你的机器上没有安装`Maven`，也可以构建项目。
    *   `./mvnw spring-boot:run`：运行Spring Boot应用，打开浏览器访问`http://localhost:8080`查看应用效果。
    *   `./mvnw test`：运行Spring Boot应用的测试用例。
    *   `./mvnw compile`：编译项目的源代码，将`src/main/java`下的文件编译为`class`文件，并输出到`target`目录下。
    *   `./mvnw clean`：删除`target`目录及其内容，进行构建前的清理工作。
    *   `./mvnw package`：将项目打包成可执行的`jar`文件，并输出到`target`目录下。
*   `pom.xml`：`Maven`项目对象模型（`Project Object Model`）文件，它描述了项目的依赖关系和配置信息。

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.5</version>
        <relativePath/> <!-- lookup parent from repository -->
      </parent>
      <groupId>com.example</groupId>
      <artifactId>taco-cloud</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <name>taco-cloud</name>
      <description>taco-cloud</description>
      <properties>
        <java.version>17</java.version>
      </properties>
      <dependencies>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-devtools</artifactId>
          <scope>runtime</scope>
          <optional>true</optional>
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
        </dependency>
      </dependencies>
      <build>
        <plugins>
          <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
          </plugin>
        </plugins>
      </build>
    </project>
    ```

初始化Spring使用的依赖：

*   了解`Spring Boot Web`：
    *   Spring的MVC框架提供了一种基于注解的编程模型，使得编写控制器（`Controller`）、视图（`View`）、模型（`Model`）变得简单。
    *   嵌入式`Tomcat`服务器可以快速启动，并提供了一个方便的开发环境。
*   了解`Spring Boot Thymeleaf`：
    *   `Thymeleaf`是一个Java模板引擎，它可以将模板文件（`HTML`）和数据模型（`Model`）结合起来，生成最终的`HTML`页面。
    *   `Thymeleaf`的语法类似于`JSP`，但它更加强大，可以处理复杂的模板逻辑。
*   了解`Spring Boot DevTools`：为Spring开发人员提供了一些便利的开发期工具和特性。
    *   代码变更后应用会自动重启。
    *   当面向浏览器的资源（如HTML、CSS、JavaScript文件）发生变化时，浏览器会自动刷新。
    *   自动禁用模板缓存。
    *   如果使用`H2`数据库，则内置了`H2`控制台，打开浏览器访问`http://localhost:8080/h2-console`，可以方便地查看数据库内容。

## 俯瞰Spring风景线

Spring自带了一个强大的Web框架，名为`Spring MVC`。

*   控制器（`Controller`）是处理请求并以某种方式进行信息响应的类。在面向浏览器的应用中，控制器会填充可选的数据模型并将请求传递给一个视图，以便于生成返回给浏览器的`HTML`；在面向`RESTful`的应用中，控制器会将请求数据转换为`JSON`格式，并返回给客户端。
*   视图（`View`）是用来渲染模型的模板文件。在`Thymeleaf`模板引擎中，视图会将模型数据填充到模板文件中，并生成最终的`HTML`页面。
*   模型（`Model`）是用来存储数据的对象。控制器和视图通过模型对象共享数据。

`Spring Data`：

*   Spring核心提供了基本的数据持久化支持，但`Spring Data`提供了非常令人惊叹的功能：将应用程序的数据存储库（`Repository`）定义为简单的Java接口，在定义存储和检索数据的方法时使用一种特定的命名约定。
*   `Spring Data`能够处理多种不同类型的数据库，包括关系型数据库，文档数据库，图数据库等。

`Spring Security`：解决了应用程序通用的安全性需求，包括身份验证、授权和`API`安全性。

`Spring Integration`和`Spring Batch`：

*   `Spring Integration`解决了实时集成问题，包括消息传递、事件驱动、数据流等。
*   `Spring Batch`解决了批处理集成问题，包括大数据处理、异步处理等。

`Spring Cloud`：用来开发分布式的云应用，由微服务组成的多个独立部署单元来组合形成应用程序。

`Spring Native`：使用`GraalVM`原生镜像编译器将`Spring Boot`项目编译成原生可执行的文件，从而使镜像的启动速度更快，占用内存更少。
