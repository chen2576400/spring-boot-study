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


    public static void testLamada() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerList = new ArrayList<>();
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
        LOGGER.info(JSON.toJSONString("callable返回值" + list));
        LOGGER.info(JSON.toJSONString("集合返回值" + integerList));
        LOGGER.info("================================>>>>>>>>>>>test end");
        executorService.shutdown();
    }


    /**
     * @param taskCount 线程数
     */
    public static void testList(Integer taskCount) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10001; i++) {
            list.add(i);
        }

        int start = 0; //开始
        int end = 0;   //结束
        int listSize = list.size();//集合长度
        int remainder = listSize % taskCount;  //取余数
        int taskDataSize = listSize / taskCount;//每个集合要处理长度（最后的余数交给最后一个集合5/2）

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<List<Integer>>> futures=new ArrayList<>();
        for (int i = 0; i < taskCount; i++,start=start+taskDataSize) {//for 循环里方法体里面的参数 只有第一次执行完才会执行  第一次start=0
//            start += taskDataSize;   ==  start=start+taskDataSize
            end = start + taskDataSize;
            //最后如果有分配不均的，多余部分交给最后一个任务处理
            if (i == taskCount - 1) { //遍历到最后一层
                if (remainder != 0) {
                    end = listSize;
                }
            }
            int finalStart = start;
            int finalEnd = end;
           Future<List<Integer>> listFuture= executorService.<List<Integer>>submit(() -> {
                       return getList(list, finalStart, finalEnd);
                   }
           );
            futures.add(listFuture);
        }


        for (Future<List<Integer>> future:futures){
           List<Integer> integerList= future.get();
            System.out.println(integerList.size());
        }

    }


    private static<T> List<T> getList(List<T> list, int start, int end) {
        List<T> list1 = new ArrayList<>();
        for (int i = start; i < end; i++) {
            T num = list.get(i);
            list1.add(num);
        }
        return list1;
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
