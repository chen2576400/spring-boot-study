package com.chengning.test;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulateTest {

    //java原子包,利用了CAS算法保证了数据的原子性,
    static  AtomicInteger successCount=new AtomicInteger(0);//线程安全的计数器
    static  AtomicInteger sum=new AtomicInteger(0);//线程安全的计数器(调用总次数)

    static int crudSum=0;//非线程安全(调用总次数)

    public static void main(String[] args) throws Exception{
        //总共的线程数
        int threadSize=100;
        //每秒并发数
        final  int count=20;
        //同步执行器,必须等所有线程都完成任务,才能执行后面的代码
        CountDownLatch downLatch=new CountDownLatch(threadSize);
        ExecutorService fixedThreadPool=Executors.newFixedThreadPool(threadSize);
        for(int i=0;i<threadSize;i++) {
            int finalI = i;
            fixedThreadPool.submit(() -> {
                //没经过20的整数倍时候休眠1s
                if(finalI%count==0){  //可看成绝对值的取余数运算
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject("http://localhost:1005/deduct_stock", String.class);
                        {//方法总调用次数
                            sum.incrementAndGet();
                            crudSum++;
                        }
                if("success".equals(result)){
                    successCount.incrementAndGet();//以原子方式将当前值递增1并在递增后返回新值。它相当于i ++操作。
                }
                downLatch.countDown();
            });
        }
        downLatch.await();//await() 能够阻塞线程 直到调用N次end.countDown() 方法才释放线程  不然会直接异步处理直接输出后面逻辑
        System.out.println("购买商品成功的次数:"+successCount.get());
        System.out.println("调用的总数:"+sum.get()+"=="+crudSum);
        fixedThreadPool.shutdown();
    }
}
