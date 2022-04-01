package com.chenning.common.thread.juc.study02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: sync的使用
 * @author: Mr.Nchen
 * @create: 2022-03-17 14:34
 * <p>
 * <p>
 * synchronized 一般锁的是共享的对象 或者方法
 **/
public class Ticket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "苦逼的我").start();
        new Thread(station, "牛逼的你们").start();
        new Thread(station, "可恶的黄牛党").start();

    }
}


class BuyTicket implements Runnable {

    //票
    private Integer ticketNums = 10;
    boolean flag = true;//外部停止方式
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
/*//        synchronized (ticketNums) {
        synchronized (this) {
            //买票
            while (flag) {
                try {
                    buy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }*/


        {
            try {
                lock.lock();
                while (flag) {
                    buy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void buy() throws InterruptedException {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        //模拟延时
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }

}




