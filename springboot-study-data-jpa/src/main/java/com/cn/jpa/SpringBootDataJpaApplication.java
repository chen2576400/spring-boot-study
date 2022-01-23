package com.cn.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 14:54
 **/
@SpringBootApplication
@EnableJpaAuditing
public class SpringBootDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

}
