package com.chenning.springbootlearn.codingDesign.design06.factory3;

import com.chenning.springbootlearn.codingDesign.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 14:06
 * @Version 1.0
 * @Description
 */
public class LDOrderPizza extends OrderPizza {


    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        // TODO Auto-generated method stub
        return pizza;
    }

}
