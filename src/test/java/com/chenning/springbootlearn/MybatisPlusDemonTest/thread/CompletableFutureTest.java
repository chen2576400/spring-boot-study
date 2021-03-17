package com.chenning.springbootlearn.MybatisPlusDemonTest.thread;

import com.alibaba.fastjson.JSON;
import com.chenning.springbootlearn.thread.completableFuture.CompletableFutureDemo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 17:19
 */

public class CompletableFutureTest {
    /**相关文档
     * https://blog.csdn.net/finalheart/article/details/87615546
     *
     * 每调用一次Future的get或者join方法 都相当于阻塞取值。 所以要先把futureList先组装成集合再取   lamada不能直接在结果集上遍历取值 否则循环一直阻塞一次
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);

    @Test
    public void test1() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }


    /**多线程处理
     * 搭配线程池处理时，记得保持守护线程开启  否则任务执行完 守护线程直接关闭 相关逻辑代码不执行了
     */
    @Test
    public void test2() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo1();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }


    /**多线程处理
     * 带回调的传统写法拆分(不带线程池)
     */
    @Test
    public void test3() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo2();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }


    /**多线程处理
     * lamada表达式写法(配线程池)
     */
    @Test
    public void test4() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo3();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }


    /**
     * 执行某个方法  for
     */
    @Test
    public void test5() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo4();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }


    /**
     * 执行某个方法  lamada
     */
    @Test
    public void test6() {
        LOGGER.info("================================>>>>>>>>>>>test start");
        List<Integer> list = CompletableFutureDemo.completableFutureDemo5();
        LOGGER.info(JSON.toJSONString(list));
        LOGGER.info("================================>>>>>>>>>>>test end");
    }
}
