package com.chenning.common.design.design03;

/**
 * @Author nchen
 * @Date 2021/11/29 17:58
 * @Version 1.0
 * @Description
 * 方式1的优缺点
 * 1) 优点是比较好理解，简单易操作。
 * 2) 缺点是违反了设计模式的ocp原则，即对扩展开放(提供方)，
 * 对修改关闭(使用方)。
 * 即当我们给类增加新功能的时候，尽量不修改代码，或者尽可
 * 能少修改代码.
 * 3) 比如我们这时要新增加一个图形种类 三角形，我们需
 * 要做如下修改，修改的地方
 * 较多
 * 4) 代码演示
 */
public class Ocp01 {

    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor1 graphicEditor = new GraphicEditor1();
        graphicEditor.drawShape(new Rectangle1());
        graphicEditor.drawShape(new Circle1());
        graphicEditor.drawShape(new Triangle1());
    }

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor1 {
    //接收Shape对象，然后根据type，来绘制不同的图形
    public void drawShape(Shape1 s) {
        if (s.m_type == 1)
            drawRectangle(s);
        else if (s.m_type == 2) {
            drawCircle(s);
        } else if (s.m_type == 3) {
            drawTriangle(s);
        }
    }

    //绘制矩形
    public void drawRectangle(Shape1 r) {
        System.out.println(" 绘制矩形 ");
    }

    //绘制圆形
    public void drawCircle(Shape1 r) {
        System.out.println(" 绘制圆形 ");
    }

    //绘制三角形
    public void drawTriangle(Shape1 r) {
        System.out.println(" 绘制三角形 ");
    }
}

//Shape类，基类
class Shape1 {
    int m_type;
}

class Rectangle1 extends Shape1 {
    Rectangle1() {
        super.m_type = 1;
    }
}

class Circle1 extends Shape1 {
    Circle1() {
        super.m_type = 2;
    }
}

//新增画三角形
class Triangle1 extends Shape1 {
    Triangle1() {  //无修饰符的情况姑且称为default，访问范围是package，就是同一个包中的类可访问
        super.m_type = 3;
    }

}
