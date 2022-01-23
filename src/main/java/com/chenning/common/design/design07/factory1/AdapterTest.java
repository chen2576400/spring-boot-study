package com.chenning.common.design.design07.factory1;

/**
 * @Author nchen
 * @Date 2021/11/30 15:54
 * @Version 1.0
 * @Description 这样Targetable接口的实现类就具有了Source类的功能。
 */
public class AdapterTest {
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
