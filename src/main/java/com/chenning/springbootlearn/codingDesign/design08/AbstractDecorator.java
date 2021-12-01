package com.chenning.springbootlearn.codingDesign.design08;

/**
 * @Author nchen
 * @Date 2021/11/30 16:46
 * @Version 1.0
 * @Description
 */
public abstract class AbstractDecorator implements Human{
    //持有被装饰类的引用
    private Human human;

    public AbstractDecorator(){

    }

    //构造函数注入被装饰者
    public AbstractDecorator(Human human) {
        System.out.println("抽象构造函数");
        this.human = human;
    }

    //调用被装饰类的方法
    @Override
    public void run() {
        human.run();
    }

}
