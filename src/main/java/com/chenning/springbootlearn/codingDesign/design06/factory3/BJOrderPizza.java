package com.chenning.springbootlearn.codingDesign.design06.factory3;

import com.chenning.springbootlearn.codingDesign.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 14:05
 * @Version 1.0
 * @Description
 */
public class BJOrderPizza extends OrderPizza {


    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }

}
