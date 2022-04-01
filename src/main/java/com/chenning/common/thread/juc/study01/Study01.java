package com.chenning.common.thread.juc.study01;

/**
 * @description: 用户线程和守护线程
 * @author: Mr.Nchen
 * @create: 2022-03-17 14:19
 *
 *
 *
 * 用户线程：主线程结束了，用户线程还在进行，jvm还处于存活状态
 * 守护线程：没有用户线程了，都是守护线程，jvm就会结束
 *
 *
 **/
public class Study01 {

    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": :" + Thread.currentThread().isDaemon());
            while (true) {
                System.out.println("======");
            }
        }, "aa");
//        aa.setDaemon(true);
        aa.start();
        System.out.println(Thread.currentThread().getName()+" over");
    }



}
