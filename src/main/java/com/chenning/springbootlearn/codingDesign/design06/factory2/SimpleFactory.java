package com.chenning.springbootlearn.codingDesign.design06.factory2;

import com.chenning.springbootlearn.codingDesign.design06.factory1.CheesePizza;
import com.chenning.springbootlearn.codingDesign.design06.factory1.GreekPizza;
import com.chenning.springbootlearn.codingDesign.design06.factory1.PepperPizza;
import com.chenning.springbootlearn.codingDesign.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 11:31
 * @Version 1.0
 * @Description
 */
public class SimpleFactory {
    //更加orderType 返回对应的Pizza 对象
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;

        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }

    //简单工厂模式 也叫 静态工厂模式

    public static Pizza createPizza2(String orderType) {

        Pizza pizza = null;

        System.out.println("使用简单工厂模式2");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }

        return pizza;
    }

}
