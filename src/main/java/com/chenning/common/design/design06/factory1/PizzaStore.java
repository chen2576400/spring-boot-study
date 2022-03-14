package com.chenning.common.design.design06.factory1;

/**
 * @Author nchen
 * @Date 2021/11/30 11:26
 * @Version 1.0
 * @Description
 * 统的方式的优缺点
 * 1) 优点是比较好理解，简单易操作。
 * 2) 缺点是违反了设计模式的ocp原则，即对扩展开放，
 * 对修改关闭。即当我们给类增
 * 加新功能的时候，尽量不修改代码，或者尽可能少修改代码.
 * 3) 比如我们这时要新增加一个Pizza的种类(Pepper披萨)，
 * 我们需要做如下修改.

//新增 写
public class CheesePizza extends Pizza {
@Override
public void prepare() {
// TODO Auto-generated method stub
setName("奶酪pizza");
System.out.println(name + " preparing;");
}}

//增加一段代码 OrderPizza.java //写
if (ordertype.equals("greek")) {
pizza = new GreekPizza();
} else if (ordertype.equals("pepper")) {
pizza = new PepperPizza();
} else if (ordertype.equals("cheese")) {
pizza = new CheesePizza();
} else {
break;
}}
4) 改进的思路分析
分析：修改代码可以接受，但是如果我们在其它的地方
也有创建Pizza的代码，就意味着，也需要修改，而创
建Pizza的代码，往往有多处。
思路：把创建Pizza对象封装到一个类中，这样我们有
新的Pizza种类时，只需要修改该类就可，其它有创
建到Pizza对象的代码就不需要修改了.-> 简单工厂模式

 *
 *
 */
public class PizzaStore {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new OrderPizza();

    }
}
