package com.chenning.common.design.design07.factory2;

/**
 * @Author nchen
 * @Date 2021/11/30 15:59
 * @Version 1.0
 * @Description
 */
public class AdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
