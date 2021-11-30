package com.chenning.springbootlearn.codingDesign.design06.factory1;

/**
 * @Author nchen
 * @Date 2021/11/30 11:13
 * @Version 1.0
 * @Description
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 给希腊披萨 准备原材料 ");
    }
}
