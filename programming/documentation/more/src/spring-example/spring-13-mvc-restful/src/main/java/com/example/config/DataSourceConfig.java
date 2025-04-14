package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

  @Value("${example.username}")
  private String user;
  @Value("${example.password}")
  private String password;
  @Value("${example.url}")
  private String url;
  @Value("${example.driver}")
  private String driver;


  // 数据库连接池配置
  @Bean
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUsername(user);
    dataSource.setPassword(password);
    dataSource.setUrl(url);
    dataSource.setDriverClassName(driver);
    return dataSource;
  }

}
