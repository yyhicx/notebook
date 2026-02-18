# SpringBoot

## SpringBoot基础篇

Spring Initializer：是一个在线工具，用于创建Spring Boot项目。

*   [Spring Initializer](https://start.spring.io/)
*   [Alibaba Spring Initializer](https://start.aliyun.com)

SpringBoot入门程序开发：

*   SpringBoot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用的初始搭建以及开发过程。
    *   Spring的缺点：依赖配置繁冗，开发效率低。
    *   SpringBoot的优点：简化依赖配置，开发效率高。
*   入门案例：
    *   src: projects/springboot-example/springboot-01-quickstart
    *   IDEA配置：
        *   Settings -> Editor -> Code Style -> Java -> GoogleJavaStyle
        *   Settings -> Editor -> File Encodings -> Set `UTF-8 with NO BOM` and `Transparent native-to-ascii conversion`
        *   Settings -> Build, Execution, Deployment -> Build Tools -> Maven -> Set `Maven home path`, `User settings file` and `Local repository`
        *   Settings -> Tools -> Actions on Save -> Set `Reformat code` and `Optimize imports`
        *   Project Stucture -> Project Settings -> Project -> Set `SDK` and `Language level`
    *   项目结构：

        ```txt
        springboot-example
        |-- springboot-01-quickstart
        |   |-- src
        |   |   |-- main
        |   |   |   |-- java
        |   |   |   |   |-- com.example
        |   |   |   |       |-- controller
        |   |   |   |       |   |-- TestController.java
        |   |   |   |       |-- SpringBoot01QuickstartApplication.java
        |   |   |   |-- resources
        |   |   |       |-- application.properties
        |   |   |-- test
        |   |       |-- java
        |   |       |   |-- com.example
        |   |       |       |-- SpringBoot01QuickstartApplicationTest.java
        |   |       |-- resources
        |   |-- .gitignore
        |   |-- pom.xml
        |-- .gitignore
        |-- pom.xml
        ```

    *   源码：
        *   TestController.java：

            ```java
            package com.example.controller;

            import org.springframework.web.bind.annotation.GetMapping;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.RestController;

            @RestController
            @RequestMapping("/test")
            public class TestController {

              @GetMapping("/hello")
              public String hello() {
                return "Hello, World!";
              }
            }
            ```

        *   SpringBoot01QuickstartApplication.java：

            ```java
            package com.example;

            import org.springframework.boot.SpringApplication;
            import org.springframework.boot.autoconfigure.SpringBootApplication;

            @SpringBootApplication
            public class SpringBoot01QuickstartApplication {

              public static void main(String[] args) {
                SpringApplication.run(SpringBoot01QuickstartApplication.class, args);
              }
            }
            ```

        *   application.properties：

            ```txt
            server.port=8080
            ```

        *   SpringBoot01QuickstartApplicationTest.java：

            ```java
            package com.example;

            public class SpringBoot01QuickstartApplicationTest {
            }
            ```

        *   .gitignore：

            ```txt
            target/
            !.mvn/wrapper/maven-wrapper.jar
            !**/src/main/**/target/
            !**/src/test/**/target/

            ### IntelliJ IDEA ###
            .idea/modules.xml
            .idea/jarRepositories.xml
            .idea/compiler.xml
            .idea/libraries/
            *.iws
            *.iml
            *.ipr

            ### Eclipse ###
            .apt_generated
            .classpath
            .factorypath
            .project
            .settings
            .springBeans
            .sts4-cache

            ### NetBeans ###
            /nbproject/private/
            /nbbuild/
            /dist/
            /nbdist/
            /.nb-gradle/
            build/
            !**/src/main/**/build/
            !**/src/test/**/build/

            ### VS Code ###
            .vscode/

            ### Mac OS ###
            .DS_Store
            ```

        *   子模块的pom.xml：

            ```xml
            <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

              <parent>
                <groupId>com.example</groupId>
                <artifactId>springboot-example</artifactId>
                <version>1.0.0</version>
                <relativePath>../pom.xml</relativePath>
              </parent>

              <modelVersion>4.0.0</modelVersion>

              <groupId>com.example</groupId>
              <artifactId>springboot-01-quickstart</artifactId>
              <version>1.0.0</version>
              <packaging>jar</packaging>

              <dependencies>
                <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-web</artifactId>
                </dependency>
              </dependencies>

              <build>
                <plugins>
                  <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version>${springboot.version}</version>
                    <executions>
                      <execution>
                        <goals>
                          <goal>repackage</goal>
                        </goals>
                      </execution>
                    </executions>
                    <configuration>
                      <mainClass>com.example.SpringBoot01QuickstartApplication</mainClass>
                    </configuration>
                  </plugin>
                </plugins>
              </build>

            </project>
            ```

        *   项目的pom.xml：在引入`spring-boot-dependencies`依赖时有两种方式，第一种是继承`spring-boot-starter-parent`，第二种是直接引入`spring-boot-dependencies`依赖。

            ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-v4_0_0.xsd">

              <!-- 方式一：继承 spring-boot-starter-parent -->
              <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>2.6.1</version>
                <relativePath/>
              </parent>

              <modelVersion>4.0.0</modelVersion>

              <groupId>com.example</groupId>
              <artifactId>springboot-example</artifactId>
              <version>1.0.0</version>
              <packaging>pom</packaging>
              <name>springboot-example</name>
              <description>springboot-example</description>

              <modules>
                <module>springboot-01-quickstart</module>
              </modules>

              <properties>
                <java.version>1.8</java.version>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <!-- Maven 会在构建生命周期的 test 和 test-compile 阶段跳过所有测试的执行 -->
                <skipTests>true</skipTests>

                <springboot.version>2.6.1</springboot.version>
              </properties>


              <!-- 方式二：引入 spring-boot-dependencies -->
              <!--  <dependencyManagement>-->
              <!--    <dependencies>-->
              <!--      <dependency>-->
              <!--        <groupId>org.springframework.boot</groupId>-->
              <!--        <artifactId>spring-boot-dependencies</artifactId>-->
              <!--        <version>${springboot.version}</version>-->
              <!--        <type>pom</type>-->
              <!--        <scope>import</scope>-->
              <!--      </dependency>-->
              <!--    </dependencies>-->
              <!--  </dependencyManagement>-->

            </project>
            ```

*   入门案例解析：
    *   Parent（依赖管理）：
        *   在Maven项目中，通常通过继承`spring-boot-starter-parent`来开发SpringBoot程序。
        *   `spring-boot-starter-parent`本身又继承了`spring-boot-dependencies`，这个项目（指的是`spring-boot-dependencies`）在POM的`<dependencyManagement>`部分预定义了大量的依赖及其经过测试的、相互兼容的版本。这有效地统一了项目的依赖管理，避免了常见的版本冲突问题。
        *   需要注意的是，不同版本的`spring-boot-starter-parent`所管理的依赖版本列表通常不同，即管理的依赖的版本号可能不同。
        *   除了继承parent，对于无法继承父POM的项目，也可以通过在POM中直接引入`spring-boot-dependencies`并指定type为pom和scope为import的方式，实现相同的依赖版本管理效果。
    *   Starter（依赖引入与自动配置）：
        *   当需要为SpringBoot项目添加功能时，我们通常引入对应的starter坐标。
        *   每个starter都是一组依赖的集合，它包含了实现特定功能所必需的核心库及其传递依赖。例如，`spring-boot-starter-web`就自动包含了SpringMVC、Jackson、以及嵌入式Tomcat等。
        *   Starter的另一大核心作用是提供自动配置（`Auto-Configuration`）。SpringBoot会根据项目中引入的Starter，在启动时自动配置所需的Bean和配置项，实现了开箱即用。
        *   Starter的命名有约定：官方提供的通常命名为`spring-boot-starter-xxx`，而第三方提供的则建议命名为`xxx-spring-boot-starter`。
    *   引导类：
        *   SpringBoot的引导类是SpringBoot项目的入口，运行main方法就可以启动项目。
        *   SpringBoot项目运行后初始化Spring容器，默认情况下扫描引导类所在包及子包下的所有类。
        *   引导类本身是一个配置类，通常被`@SpringBootApplication`注解标注。该注解是一个复合注解，它主要由三个核心注解组成，分别是`@SpringBootConfiguration`、`@EnableAutoConfiguration`和`@ComponentScan`。
            *   `@SpringBootConfiguration`：表明该类是一个配置类。
            *   `@EnableAutoConfiguration`：启用SpringBoot的自动配置机制。
            *   `@ComponentScan`：启用组件扫描，默认扫描当前类所在包及其子包。
    *   内嵌Web服务器：
        *   通过引入`spring-boot-starter-web`依赖，其中包含`spring-boot-starter-tomcat`，因此项目就内嵌了Tomcat，启动项目时就会启动内嵌的Tomcat服务器。
        *   内嵌服务器的工作原理是将服务器（如Tomcat、Jetty）本身作为一个对象运行，并将该对象交给Spring容器管理。
        *   变更内嵌服务器是通过在依赖中排除（`<exclusions>`）掉默认的Tomcat依赖，然后添加新服务器（如Jetty或Undertow）的Starter依赖来实现。
        *   SpringBoot Web Starter内支持三种服务器，分别是Tomcat（默认）、Jetty和Undertow。
            *   Tomcat：Apache出品，应用广泛，生态系统成熟，是默认且最常用的选择。
            *   Jetty：更轻量、模块化程度更高，适用于长连接较多的场景（如WebSocket）。
            *   Undertow：由RedHat开发，以高性能和低内存消耗著称，在高并发负载下表现优异。
    *   注意：
        *   Parent和Starter的机制共同目标是简化项目配置和依赖管理：
            *   Parent的作用是进行统一的依赖管理（定义版本），它并不直接引入依赖，而是为项目提供一份经测试兼容的依赖版本清单。
            *   Starter的作用是进行功能性的依赖引入，它根据功能需求，自动引入一组配置好的依赖坐标，并结合自动配置， 开发者无需手动逐个指定和配置。
        *   在实际开发SpringBoot项目中，引入由Parent管理的依赖时仅需指定groupId和artifactId，版本（version）由Parent统一管理。除非Parent中未定义该依赖的版本，或者您需要覆盖Parent中定义的版本，这时候才需要显式地自行添加版本号。

## SpringBoot实用篇

## SpringBoot原理篇

## 知识扩展

REST开发：

*   REST简介：
    *   REST（Representational State Transfer，表述性状态转移）是一种软件架构风格，RESTful API是符合REST风格开发的API。
        *   传统风格资源描述形式：`http://localhost/user/getById?id=1`、`http://localhost/user/saveUser`。
        *   REST风格资源描述形式：`http://localhost/user/1`、`http://localhost/user`。
    *   REST的优点：书写简单；隐藏资源的访问行为，无法通过地址得知对资源是何种操作。
    *   按照REST风格访问资源时使用行为动作区分对资源进行何种操作：

        | URL                        | Description | HTTP Method |
        | -------------------------- | ----------- | ----------- |
        | `http://localhost/users`   | Get All     | GET         |
        | `http://localhost/users/1` | Get One     | GET         |
        | `http://localhost/users`   | Create      | POST        |
        | `http://localhost/users`   | Update      | PUT         |
        | `http://localhost/users/1` | Delete      | DELETE      |

    *   注意：
        *   上述行为是约定方式，约定不是规范，可以打破，所以称为REST风格，而不是REST规范。
        *   描述模块的名称通常使用复数，表示此类资源，而非单个资源，例如`users`、`books`、`accounts`。
