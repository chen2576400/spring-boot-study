package com.chenning.springbootlearn.thread.future;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 16:36
 */
public class FutureDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(FutureDemo.class);

    public void test() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        // 在我们异步操作的同时一样可以做其他操作
        {
            // ......
        }

        // 获取线程结果
        try {
            String res = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<String> resList = new ArrayList<>();
        LOGGER.info("================================>>>>>>>>>>>test start");
        for (int i = 0; i < 5; i++) {
            String result = executorService.submit(new demoTask(i)).get();
            resList.add(result);
        }
        LOGGER.info(JSON.toJSONString(resList));
        LOGGER.info("================================>>>>>>>>>>>test end");
        executorService.shutdown();
    }

    public static void testFutureList() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<String> resList = Collections.synchronizedList(new ArrayList<>());
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Future<String>> submits = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new demoTask(i));
            submits.add(submit);
        }

//        for(Future<String> s:submits){
//            resList.add(s.get());
//        }

        // stream
        submits.parallelStream().forEach(p -> {
            try {
                String s = p.get();
                LOGGER.info("----------" + s);
                resList.add(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        LOGGER.info(JSON.toJSONString(resList));
        LOGGER.info("================================>>>>>>>>>>>test end");
        executorService.shutdown();
    }







    public static void testLamada()  {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer>  integerList=new ArrayList<>();
        LOGGER.info("================================>>>>>>>>>>>test start");

//        asList.stream().map(integer -> {
//            System.out.println("当前获取的是======>" + integer);
//            return integer;
//        }).collect(Collectors.toList());  加了.collect(Collectors.toList())才会打印

        List<Future<Integer>> futures = asList.stream().map(integer -> executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前获取的是" + integer);
            integerList.add(integer);
            return integer;
        })).collect(Collectors.toList());
        LOGGER.info("================================>>>>>>>>>>>test end");


        List<Integer> list = futures.parallelStream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        LOGGER.info(JSON.toJSONString("callable返回值"+list));
        LOGGER.info(JSON.toJSONString("集合返回值"+integerList));
        LOGGER.info("================================>>>>>>>>>>>test end");
        executorService.shutdown();
    }





    static class demoTask implements Callable<String> {

        private Integer param;

        demoTask(Integer param) {
            this.param = param;
        }

        demoTask() {
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(10);
            return param + "";
        }
    }


}
