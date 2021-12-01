package com.chenning.springbootlearn.codingDesign.design07.factory3;

/**
 * @Author nchen
 * @Date 2021/11/30 16:11
 * @Version 1.0
 * @Description
 */
public class WrapperTest {

    public static void main(String[] args) {
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();
    }
}
