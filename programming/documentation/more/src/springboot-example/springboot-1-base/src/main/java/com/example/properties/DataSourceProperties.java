package com.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.jdbc.datasource")
@Data
public class DataSourceProperties {
  private String driverClassName;
  private String url;
  private String username;
  private String password;
}
