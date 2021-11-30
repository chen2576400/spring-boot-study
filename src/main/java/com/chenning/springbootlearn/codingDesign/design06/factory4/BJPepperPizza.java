package com.chenning.springbootlearn.codingDesign.design06.factory4;

import com.chenning.springbootlearn.codingDesign.design06.factory1.Pizza;

/**
 * @Author nchen
 * @Date 2021/11/30 15:35
 * @Version 1.0
 * @Description
 */
public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的胡椒pizza");
        System.out.println(" 北京的胡椒pizza 准备原材料");
    }

}
