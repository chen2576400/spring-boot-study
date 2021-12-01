package com.chenning.springbootlearn.codingDesign.design08;

/**
 * @Author nchen
 * @Date 2021/11/30 17:11
 * @Version 1.0
 * @Description
 */
public class ClientTest {
    public static void main(String[] args) {
        //创建被装饰的类
        Human human = new Man();

        //创建装饰的类，并添加被装饰类的引用
        Human superMan = new ManDecorator(human);

        //执行增强后的run方法
        superMan.run();
    }

}
