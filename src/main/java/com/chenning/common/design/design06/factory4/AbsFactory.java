package com.chenning.common.design.design06.factory4;

import com.chenning.common.design.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 15:37
 * @Version 1.0
 * @Description
 */
public interface AbsFactory {
    //让下面的工厂子类来 具体实现
    public Pizza createPizza(String orderType);
}
