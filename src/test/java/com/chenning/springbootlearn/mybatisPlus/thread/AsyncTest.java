package com.chenning.springbootlearn.mybatisPlus.thread;

import com.alibaba.fastjson.JSON;
import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.thread.async.AsyncJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 16:36
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class AsyncTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTest.class);

    @Autowired
    private AsyncJobService jobService;


    /**
     * 线程池最小线程配置多少 就会并行执行多少数据  如果配成1 那就只有一条线程执行  依次处理
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        LOGGER.info("================================>>>>>>>>>>>test start");
        for (int i = 0; i < 5; i++) {
            Future<Integer> task = jobService.task(i);
            futures.add(task);
        }
        LOGGER.info("================================>>>>>>>>>>>test end");
        /**
         * 将操作逻辑交给多线程处理 然后将结果集收集遍历  遍历时线程是阻塞的 有一个future没获取结果就不会结束
         */
        System.out.println("futures===============>长度"+futures.size());

            List<Integer> collect = futures.stream().map(integerFuture -> {
                try {
                    return integerFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            System.out.println(JSON.toJSONString(collect));



    }


}
