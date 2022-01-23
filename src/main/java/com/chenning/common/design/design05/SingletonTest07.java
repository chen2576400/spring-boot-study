package com.chenning.common.design.design05;

/**
 * @Author nchen
 * @Date 2021/11/30 10:50
 * @Version 1.0
 * @Description  静态内部类
 *
 * 优缺点说明：
 * 1) 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 * 2) 静态内部类方式在Singleton类被装载时并不会立即实例化，
 * 而是在需要实例化
 * 时，调用getInstance方法，才会装载SingletonInstance类，
 * 从而完成Singleton的实例化。
 * 3) 类的静态属性只会在第一次加载类的时候初始化，所以在这
 * 里，JVM帮助我们保证了线程的安全性，在类进行初始化时，
 * 别的线程是无法进入的。
 * 4) 优点：避免了线程不安全，利用静态内部类特点实现延迟加载，
 * 效率高
 * 5) 结论：推荐使用.
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton7 instance = Singleton7.getInstance();
        Singleton7 instance2 = Singleton7.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

    }

}

// 静态内部类完成， 推荐使用

//静态内部类的优点是：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。即当Singleton7第一次被加载时，
// 并不需要去加载SingletonInstance，只有当getInstance()方法第一次被调用时，
//才会去初始化INSTANCE,第一次调用getInstance()方法会导致虚拟机加载SingletonInstance类，SingletonInstance类只会执行一次
// 这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
//



class Singleton7 {

    //构造器私有化
    private Singleton7() {}

    //写一个静态内部类,该类中有一个静态属性 Singleton
    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    //提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE

    public static  Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
