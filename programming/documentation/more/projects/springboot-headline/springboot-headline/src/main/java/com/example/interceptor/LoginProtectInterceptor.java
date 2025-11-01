package com.example.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.example.utils.JwtHelper;
import com.example.utils.Result;
import com.example.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectInterceptor implements HandlerInterceptor {
  private final JwtHelper jwtHelper;

  @Autowired
  public LoginProtectInterceptor(JwtHelper jwtHelper) {
    this.jwtHelper = jwtHelper;
  }

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response, @NonNull Object handler)
    throws Exception {
    // 放行 OPTIONS 预检
    if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
      return true;
    }

    String rawToken = request.getHeader("Authorization");
    System.out.println("rawToken: " + rawToken);
    String token = rawToken.replace("Bearer ", "");
    if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
      Result result = Result.build(null, ResultCodeEnum.NOT_LOGIN);
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(result);
      response.getWriter().write(json);
      // 拦截
      return false;
    } else {
      // 放行
      return true;
    }
  }
}
