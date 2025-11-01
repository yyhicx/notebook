package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example")
// 作用等于 <aop:aspectj-autoproxy /> 配置类上开启 Aspectj 注解支持
@EnableAspectJAutoProxy
public class AppConfig {
}

