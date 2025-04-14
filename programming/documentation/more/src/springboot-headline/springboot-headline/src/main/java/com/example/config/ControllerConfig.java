package com.example.config;

import com.example.interceptor.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {
  private final LoginProtectInterceptor loginProtectInterceptor;

  @Autowired
  public ControllerConfig(LoginProtectInterceptor loginProtectInterceptor) {
    this.loginProtectInterceptor = loginProtectInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/headline/**");
  }

  /**
   * 解决跨域问题
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")  // 允许所有路径
      .allowedOrigins("http://localhost:5173")  // 允许的前端地址
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的 HTTP 方法
      .allowedHeaders("*")  // 允许的请求头
      .allowCredentials(true)  // 允许携带凭证（如 cookies）
      .maxAge(3600);  // 预检请求的缓存时间
  }
}
