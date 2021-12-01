package com.chenning.springbootlearn.codingDesign.design08;

/**
 * @Author nchen
 * @Date 2021/11/30 16:47
 * @Version 1.0
 * @Description
 */
public class ManDecorator extends AbstractDecorator {
    public ManDecorator(Human human) {
        super(human);
        System.out.println("构造函数");
    }

    //装饰类增加的功能
    private void fly() {
        System.out.println("人可以飞");
    }

    //增强了功能的run方法
    @Override
    public void run() {
        super.run();
        fly();
    }

}
