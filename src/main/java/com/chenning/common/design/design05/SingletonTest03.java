package com.chenning.common.design.design05;

/**
 * @Author nchen
 * @Date 2021/11/30 10:42
 * @Version 1.0
 * @Description  懒汉式(线程不安全)
 * 优缺点说明：
 * 1) 起到了Lazy Loading的效果，但是只能在单线程下使用。
 * 2) 如果在多线程下，一个线程进入了if (singleton == null)判断语
 * 句块，还未来得及往下执行，另一个线程也通过了这个判断语
 * 句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
 * 3) 结论：在实际开发中，不要使用这种方式.
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        System.out.println("懒汉式1 ， 线程不安全~");
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance2 = Singleton3.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());
    }

}

class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {}

    //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
    //即懒汉式
    public static Singleton3 getInstance() {
        if(instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

}
