package com.chengning.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author nchen
 * @Date 2021/10/15 10:30
 * @Version 1.0
 * @Description
 */
@Component
public class CodeInitBean {
////静态代码块>构造代码块>构造函数>普通代码块　
    static {
        //静态代码块在类被加载的时候就运行了，而且只运行一次，并且优先于各种代码块以及构造函数。如果一个类中有多个静态代码块，会按照书写顺序依次执行
        System.out.println("=======================================CodeInitBean静态代码块儿");
    }


    {   //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行
        System.out.println("=======================================CodeInitBean构造代码块");
    }


    public CodeInitBean(){
        System.out.println("=======================================CodeInitBean构造函数");
    }


    @Bean //这里就可以将CodeInit注入到容器
    public CodeInit getCodeInit(){
       return new CodeInit();
    }
}
