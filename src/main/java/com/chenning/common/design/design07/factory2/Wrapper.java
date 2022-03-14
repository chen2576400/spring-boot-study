package com.chenning.common.design.design07.factory2;

/**
 * @Author nchen
 * @Date 2021/11/30 15:58
 * @Version 1.0
 * @Description
 */
public class Wrapper implements Targetable {
    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
