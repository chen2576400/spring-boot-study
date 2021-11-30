package com.chenning.springbootlearn.codingDesign.design03;

/**
 * @Author nchen
 * @Date 2021/11/30 9:58
 * @Version 1.0
 * @Description
 * 方式二
 * 改进的思路分析
 * 思路：把创建Shape类做成抽象类，并提供一个抽象的draw方法，
 * 让子类去实现即可，
 * 这样我们有新的图形种类时，只需要让新的图形类继承Shape，
 * 并实现draw方法即可，
 * 使用方的代码就不需要修 -> 满足了开闭原则
 */
public class Ocp02 {
    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor2 graphicEditor = new GraphicEditor2();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        graphicEditor.drawShape(new OtherGraphic());
    }

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor2 {
    //接收Shape对象，调用draw方法
    public void drawShape(Shape2 s) {
        s.draw();
    }


}

//Shape类，基类
abstract class Shape2 {
    int m_type;

    public abstract void draw();//抽象方法
}

class Rectangle extends Shape2 {
    Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制矩形 ");
    }
}

class Circle extends Shape2 {
    Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle extends Shape2 {
    Triangle() {
        super.m_type = 3;
    }
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制三角形 ");
    }
}

//新增一个图形
class OtherGraphic extends Shape2 {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        System.out.println(" 绘制其它图形 ");
    }
}

