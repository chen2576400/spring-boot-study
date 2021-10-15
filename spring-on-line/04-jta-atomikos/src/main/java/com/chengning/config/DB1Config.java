package com.chengning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author nchen
 * @Date 2021/10/15 10:24
 * @Version 1.0
 * @Description 将application.properties配置文件中配置自动封装到实体类字段中
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.db1")
@Data
public class DB1Config {
    private  String username;
    private String password;
    private String jdbcUrl;

}
