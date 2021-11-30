package com.chenning.springbootlearn.codingDesign.design06.factory3;

/**
 * @Author nchen
 * @Date 2021/11/30 14:06
 * @Version 1.0
 * @Description
 *
 * 思路1
 * 使用简单工厂模式，创建不同的简单工厂类，比如
 * BJPizzaSimpleFactory、LDPizzaSimpleFactory 等等.从当前
 * 这个案例来说，也是可以的，但是考虑到项目的规模，以及软
 * 件的可维护性、可扩展性并不是特别好
 *
 * 思路2
 * 使用工厂方法模式
 *
 * 工厂方法模式
 * 工厂方法模式介绍
 * 工厂方法模式设计方案：将披萨项目的实例化功能抽象成抽象方
 * 法，在不同的口味点餐子类中具体实现。
 * 工厂方法模式：定义了一个创建对象的抽象方法，由子
 * 类决定要实例化的类。工厂方法模式将对象的实例化推迟到子类。
 */
public class PizzaStore {

    public static void main(String[] args) {
        String loc = "bj";
        if (loc.equals("bj")) {
            //创建北京口味的各种Pizza
            new BJOrderPizza();
        } else {
            //创建伦敦口味的各种Pizza
            new LDOrderPizza();
        }
    }

}
