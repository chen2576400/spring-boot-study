package com.chenning.common.design.design09;

/**
 * @Author nchen
 * @Date 2021/11/30 17:44
 * @Version 1.0
 * @Description
 */
public class Proxy implements Sourceable {

    private Source source;
    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }


    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
