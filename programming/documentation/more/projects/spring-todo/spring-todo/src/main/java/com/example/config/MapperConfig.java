package com.example.config;

import com.github.pagehelper.PageInterceptor;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.logging.commons.JakartaCommonsLoggingImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  // 配置 SqlSessionFactoryBean
  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
    // 实例化 SqlSessionFactoryBean
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    // 设置连接池
    sqlSessionFactoryBean.setDataSource(dataSource);

    // 设置 MyBatis
    org.apache.ibatis.session.Configuration configuration =
        new org.apache.ibatis.session.Configuration();

    // <settings>
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setLogImpl(JakartaCommonsLoggingImpl.class);
    configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
    sqlSessionFactoryBean.setConfiguration(configuration);

    // <typeAliases>
    sqlSessionFactoryBean.setTypeAliasesPackage("com.example.entity");

    // <plugins>
    PageInterceptor pageInterceptor = new PageInterceptor();

    Properties properties = new Properties();
    properties.setProperty("helperDialect", "mysql");
    pageInterceptor.setProperties(properties);
    sqlSessionFactoryBean.addPlugins(pageInterceptor);

    return sqlSessionFactoryBean;
  }

  // 配置 Mapper 实例扫描工厂
  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    // 设置 mapper 接口和 xml 文件所在的共同包
    mapperScannerConfigurer.setBasePackage("com.example.mapper");
    return mapperScannerConfigurer;
  }

}
