package com.chengning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling//开启对定时任务的支持
public class DatabaselookApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaselookApplication.class, args);
    }

}
