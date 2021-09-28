package com.chenning.springbootlearn.queue;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author nchen
 * @Date 2021/9/22 10:48
 * @Version 1.0
 * @Description
 */
@Log4j2
public class Queue {


    /**
     * 阻塞队列
     * <p>
     * 数据结构：静态数组  容量固定必须指定长度，没有扩容机制，没有元素的下标null占位
     * <p>
     * 锁:ReentrantLock 存取是同一把锁  操作的是同一个数组对象
     * <p>
     * 阻塞   notEmpty 出队count为0时  无元素可取时 阻塞在该对象上
     * notFull 入队count为数组的length  放不进元素时，阻塞在该对象上
     * <p>
     * 入队  从队首开始添加元素，记录putIndex(到队尾时为0)  唤醒notEmpty
     * 出队  从队首开始取元素，记录taskIndex 唤醒notFull
     * <p>
     * 先进先出，读写互相排斥
     */
    public void ArrayBlockingQueue() {
        /*
         add  向队列里面添加元素  如果队列长度已满继续添加会报错
         put   向队列里面添加元素  如果队列长度已满继续添加会阻塞 等到队列有元素移除会自动继续添加
         offer (anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false.
         */



        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(6);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            executorService.execute(new produceThead(arrayBlockingQueue));
            executorService.execute(new customerThead(arrayBlockingQueue));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }


    /**
     * 阻塞队列
     * <p>
     * <p>
     * 数据结构： 链表node  可以指定容量 ，默认为Integer.MAX_VALUE,内部类 node存储元素
     * <p>
     * 锁分离：读取互不排斥，操作的是不同node对象  （读写分离两把锁）
     * <p>
     * 阻塞：同ArrayBlockingQueue
     * <p>
     * 入队：队尾入列  记录在last节点
     * 出队 队首出列  记录在head节点
     * <p>
     * 删除元素时两个锁一起加
     * <p>
     * 先进先出(刚入队的永远在最新的last节点  出队的取head节点)
     */
    public void LinkedBlockingDeque() {

    }


    public static void main(String[] args) {
        //Queue  queue=new Queue();
        //try {
        //    queue.ArrayBlockingQueue();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

    }



    public class produceThead implements  Runnable{
        private ArrayBlockingQueue<String> arrayBlockingQueue;
        public  produceThead(ArrayBlockingQueue<String> arrayBlockingQueue){
            this.arrayBlockingQueue=arrayBlockingQueue;
        }
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try {
                    arrayBlockingQueue.put("元素"+i);
                    log.info("存放元素"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public class customerThead implements  Runnable{
        private ArrayBlockingQueue<String> arrayBlockingQueue;
        public  customerThead(ArrayBlockingQueue<String> arrayBlockingQueue){
            this.arrayBlockingQueue=arrayBlockingQueue;
        }
        @Override
        public void run() {
            try {
                while (true){
                    String take = arrayBlockingQueue.take();
                    Thread.sleep(2000);
                    log.info("取元素"+take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
