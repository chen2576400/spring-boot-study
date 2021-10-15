package com.chengning.controller;

import org.springframework.stereotype.Component;

/**
 * @Author nchen
 * @Date 2021/10/15 10:33
 * @Version 1.0
 * @Description
 */
@Component
public class BeanDao {


    public void todo(){
        System.out.println("我是自定义dao的输出方法");
    }
}
