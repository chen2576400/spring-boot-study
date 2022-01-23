package com.chenning.common.design.design06.factory4;

import com.chenning.common.design.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 15:36
 * @Version 1.0
 * @Description
 */
public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("伦敦的胡椒pizza");
        System.out.println(" 伦敦的胡椒pizza 准备原材料");
    }

}
