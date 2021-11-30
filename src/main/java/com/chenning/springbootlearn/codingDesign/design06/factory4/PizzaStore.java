package com.chenning.springbootlearn.codingDesign.design06.factory4;

/**
 * @Author nchen
 * @Date 2021/11/30 15:39
 * @Version 1.0
 * @Description
 */
public class PizzaStore {
    public static void main(String[] args) {
        //new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }

}
