package com.chenning.common.design.design06.factory4;

import com.chenning.common.design.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 15:37
 * @Version 1.0
 * @Description
 */
public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }

}
