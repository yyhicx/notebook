package com.example.config;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class MapperConfig {

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
    // 实例化 SqlSessionFactory 工厂
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    // 设置连接池
    sqlSessionFactoryBean.setDataSource(dataSource);

    // 设置 MyBatis 配置文件
    Resource resource = new ClassPathResource("mybatis-config.xml");
    sqlSessionFactoryBean.setConfigLocation(resource);

    return sqlSessionFactoryBean;
  }

  /**
   * 配置 Mapper 实例扫描工厂
   */
  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    // 设置 mapper 接口和 xml 文件所在的共同包
    mapperScannerConfigurer.setBasePackage("com.example.mapper");
    return mapperScannerConfigurer;
  }

}
