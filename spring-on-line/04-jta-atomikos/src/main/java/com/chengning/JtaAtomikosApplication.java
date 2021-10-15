package com.chengning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果在一个项目中，配置了两个数据库，那么两个数据库如何配置，以及事务怎么控制？
 * 这里我们使用jta-atomikos 来实现分布式事物管理。
 *
 */
@SpringBootApplication
public class JtaAtomikosApplication
{
    public static void main(String[] args) {
        SpringApplication.run(JtaAtomikosApplication.class, args);
    }
}
