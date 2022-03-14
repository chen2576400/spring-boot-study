package com.chenning.common.design.design06.factory3;

import com.chenning.common.design.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 14:02
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
