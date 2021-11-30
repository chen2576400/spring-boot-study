package com.chenning.springbootlearn.codingDesign;

/**
 * @Author nchen
 * @Date 2021/11/30 15:21
 * @Version 1.0
 * @Description
 */
public class Father {
    public String fStr1 = "father1";
    protected String fStr2 = "father2";
    private String fStr3 = "father3";

    //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行
    {
        System.out.println("Father common block be called  Father构造代码块");
    }

    //静态代码块在类被加载的时候就运行了，而且只运行一次，并且优先于各种代码块以及构造函数。如果一个类中有多个静态代码块，会按照书写顺序依次执行
    static {
        System.out.println("Father static block be called  Father静态代码块");
    }

    public Father() {
        System.out.println("Father constructor be called Father构造函数");
    }
}
