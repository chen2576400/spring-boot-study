package com.chenning.springbootlearn.codingDesign.design06.factory4;

import com.chenning.springbootlearn.codingDesign.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 15:34
 * @Version 1.0
 * @Description
 */
public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("北京的奶酪pizza");
        System.out.println(" 北京的奶酪pizza 准备原材料");
    }

}
