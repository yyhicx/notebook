package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.example.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

//  @Bean
//  public HandlerMapping handlerMapping() {
//    return new RequestMappingHandlerMapping();
//  }
//
//  @Bean
//  public HandlerAdapter handlerAdapter() {
//    return new RequestMappingHandlerAdapter();
//  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

}
