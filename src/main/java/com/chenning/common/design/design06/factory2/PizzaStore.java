package com.chenning.common.design.design06.factory2;

/**
 * @Author nchen
 * @Date 2021/11/30 11:35
 * @Version 1.0
 * @Description
 */
public class PizzaStore {
    public static void main(String[] args) {

        //// 使用简单工厂模式
        //new OrderPizza(new SimpleFactory());
        //System.out.println("~~退出程序~~");
        //

        //使用静态工程模式
        new OrderPizza();

    }
}
