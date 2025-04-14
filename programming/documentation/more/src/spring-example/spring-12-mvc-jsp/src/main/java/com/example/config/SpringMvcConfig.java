package com.example.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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

}
