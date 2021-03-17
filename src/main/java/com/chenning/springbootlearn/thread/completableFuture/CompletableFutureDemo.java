package com.chenning.springbootlearn.thread.completableFuture;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 16:36
 */
public class CompletableFutureDemo {

    public static List<Integer> completableFutureDemo() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        /**
         * 默认是守护线程
         */
        List<CompletableFuture<Integer>> collect = list.stream().map(p ->
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("是否为守护线程 : " + Thread.currentThread().isDaemon());
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return p;
                })).collect(Collectors.toList());

        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }


    /**
     * 搭配线程池 （默认的线程池执行任务时候并不是守护线程 需要手动开启）
     *
     * @return
     */
    public static List<Integer> completableFutureDemo1() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        ExecutorService executor = Executors.newFixedThreadPool(list.size(),//创建一个线程池 大小为集合大小
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);//使用守护线程，这种方式不会阻止程序的关停
                        return t;
                    }
                });


//        /**
//         * 如果不设置则默认线程池开启的线程不是守护线程
//         */
//        {
//            executor.submit(new Callable<Object>() {
//                @Override
//                public Object call() throws Exception {
//                    System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
//                    return null;
//                }
//            });
//        }

        /**
         * 默认是守护线程
         */
        List<CompletableFuture<Integer>> completableFutures = list.stream().map(p ->
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("是否为守护线程 : " + Thread.currentThread().isDaemon());
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return p;
                }, executor)).collect(Collectors.toList());

        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }


    /**
     * 传统写法带回调的
     *
     * @return
     */
    public static List<Integer> completableFutureDemo2() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();

        /**
         * 默认是守护线程
         */
        for (Integer s : list) {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(new Timestamp(System.currentTimeMillis()) + "是否为守护线程 : " + Thread.currentThread().isDaemon());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s;
            });

            //当所有CompletableFuture的调用完成后 会进入（）  这个是在同一时间调用  我这里调用五次 这里也会走五次 (可用于线程回调)
            //这里可以直接加到CompletableFuture.supplyAsync（（）->) 方法后面,这里只是直观的方便观察
            completableFuture.whenComplete((integer, throwable) -> {
                System.out.println("程序执行完毕后=========>" + new Timestamp(System.currentTimeMillis()) + "()" + integer);
            });
            completableFutures.add(completableFuture);
        }


        // join 和 get 方法一样 这里都是阻塞的
        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    /**
     * lamada 写法回调
     *
     * @return
     */
    public static List<Integer> completableFutureDemo3() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        ExecutorService executor = Executors.newFixedThreadPool(list.size(),//创建一个线程池 大小为集合大小
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);//使用守护线程，这种方式不会阻止程序的关停
                        return t;
                    }
                });

        List<CompletableFuture<Integer>> completableFutures = list.stream().map(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("使用executorService 时是否为守护线程 : " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        }, executor).whenComplete((integer, throwable) -> System.out.println("程序执行完毕后=========>" + new Timestamp(System.currentTimeMillis()) + "()" + integer)))
                .collect(Collectors.toList());

        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    /**
     * 传统调用某个方法 写法回调
     *
     * @return
     */
    public static List<Integer> completableFutureDemo4() {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for ( int i = 0; i < 5; i++) {
            final Integer k=i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Integer integer = taskJob(k);
                    return integer;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }).exceptionally(throwable -> {
                System.out.println(new Timestamp(System.currentTimeMillis())+"出错了呀======");return 100;}).whenComplete((integer, throwable) -> System.out.println("程序执行完毕后=========>" + new Timestamp(System.currentTimeMillis()) + "()" + integer));
            futures.add(future);
        }
          return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    public static Integer taskJob(Integer i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return 5/0;
    }
}
