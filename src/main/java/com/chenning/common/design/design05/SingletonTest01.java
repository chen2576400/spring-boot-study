package com.chenning.common.design.design05;

/**
 * @Author nchen
 * @Date 2021/11/30 10:36
 * @Version 1.0
 * @Description   饿汉式（静态常量）
 * 优缺点说明：
 * 1) 优点：这种写法比较简单，就是在类装载的时候就完成实例化。
 * 避免了线程同步问题。
 * 2) 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的
 * 效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
 * 3) 这种方式基于classloder机制避免了多线程的同步问
 * 题，不过，instance在类装载时就实例化，在单例模
 * 式中大多数都是调用getInstance方法， 但是导致类装载
 * 的原因有很多种，因此不能确定有其他的方式（或者其
 * 他的静态方法）导致类装载，这时候初始化instance就没有
 * 达到lazy loading的效果
 * 4) 结论：这种单例模式可用，可能造成内存浪费
 *
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        //测试
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }

}

//饿汉式(静态变量)

class Singleton1 {

    //1. 构造器私有化, 外部不能new
    private Singleton1() {

    }

    //2.本类内部创建对象实例
    private final static Singleton1 instance = new Singleton1();

    //3. 提供一个公有的静态方法，返回实例对象
    public static Singleton1 getInstance() {
        return instance;
    }
}
