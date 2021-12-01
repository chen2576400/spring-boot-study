package com.chenning.springbootlearn.codingDesign.design09;

/**
 * @Author nchen
 * @Date 2021/11/30 17:45
 * @Version 1.0
 * @Description
 */
public class ProxyTest {
    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
