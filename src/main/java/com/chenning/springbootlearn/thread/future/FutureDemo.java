package com.chenning.springbootlearn.thread.future;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

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
