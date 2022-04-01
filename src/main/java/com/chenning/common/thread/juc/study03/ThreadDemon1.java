package com.chenning.common.thread.juc.study03;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-17 16:35
 *
 *
 * 多线程交互中(多个线程协同)，必须要防止多线程虚假唤醒，也即（判断只用 while ,不能用if）
 **/

class Share{
    //初始值
    private  int  number =0;
    
    //+1
    public synchronized void incr() throws InterruptedException {
        while (number !=0){
            this.wait();
        }

        System.out.println("incr方法+++++++");
        //如果number是0， 就+1
        number++;
        System.out.println(Thread.currentThread().getName()+"  :: "+number);

        //通知其他线程
        this.notifyAll();

    }


    //-1
    public synchronized void decr() throws InterruptedException {
        while (number !=1){
            this.wait();
        }

        System.out.println("decr---------");
        //如果number是0， 就+1
        number--;
        System.out.println(Thread.currentThread().getName()+"  :: "+number);

        //通知其他线程
        this.notifyAll();
    }

}


public class ThreadDemon1 {

    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();



        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();





        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CC").start();



        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DD").start();

    }
}
