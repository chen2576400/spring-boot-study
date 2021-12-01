package com.chenning.springbootlearn.codingDesign.design07.factory1;

/**
 * @Author nchen
 * @Date 2021/11/30 15:53
 * @Version 1.0
 * @Description
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
