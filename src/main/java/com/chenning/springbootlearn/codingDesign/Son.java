package com.chenning.springbootlearn.codingDesign;

/**
 * @Author nchen
 * @Date 2021/11/30 15:21
 * @Version 1.0
 * @Description
 *
 * java中父类和子类初始化顺序
 * 顺序
 * 1. 父类中静态成员变量和静态代码块
 *
 * 2. 子类中静态成员变量和静态代码块
 *
 * 3. 父类中普通成员变量和代码块，父类的构造函数
 *
 * 4. 子类中普通成员变量和代码块，子类的构造函数

 * 其中“和”字两端的按照代码先后顺序执行。
 *
 *
 */
public class Son extends Father{
    public String SStr1 = "Son1";
    protected String SStr2 = "Son2";
    private String SStr3 = "Son3";

    //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行
    {
        System.out.println("Son common block be called  Son构造代码块");
    }

    //静态代码块在类被加载的时候就运行了，而且只运行一次，并且优先于各种代码块以及构造函数。如果一个类中有多个静态代码块，会按照书写顺序依次执行
    static {
        System.out.println("Son static block be called  Son静态代码块");
    }

    public Son() {
        System.out.println("Son constructor be called  Son构造函数");
    }

    public static void main(String[] args) {
        new Son();
        System.out.println("==========================");
        new Son();
    }
}
