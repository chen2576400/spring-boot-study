package com.chenning.common.crud;

import org.springframework.context.annotation.Bean;

/**
 * @description: 代码初始化顺序
 * @author: Mr.Nchen
 * @create: 2022-04-02 10:24
 **/
public class CodeInitBean {


    ////静态代码块>构造代码块>构造函数>普通代码块　
    static {
        //静态代码块在类被加载的时候就运行了(new 或者 变量调用 甚至 class.forName都会触发加载)，而且只运行一次，并且优先于各种代码块以及构造函数。如果一个类中有多个静态代码块，会按照书写顺序依次执行
        System.out.println("=======================================CodeInitBean静态代码块儿");
    }


    {   //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行
        System.out.println("=======================================CodeInitBean构造代码块");
    }


    public CodeInitBean() {
        System.out.println("=======================================CodeInitBean构造函数");
    }



    /*   反编译后的代码
  public class CodeInitBean {

        public CodeInit() {
           System.out.println("=======================================CodeInitBean构造代码块");
           System.out.println("=======================================CodeInitBean构造函数");
        }

        static {
            System.out.println("=======================================CodeInit静态代码块儿");
        }
    }*/
}
