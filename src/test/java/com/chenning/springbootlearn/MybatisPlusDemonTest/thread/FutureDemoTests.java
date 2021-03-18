package com.chenning.springbootlearn.MybatisPlusDemonTest.thread;

import com.chenning.springbootlearn.thread.future.FutureDemo;
import org.junit.Test;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 16:36
 */
public class FutureDemoTests {

    /**
     * future测试 task睡眠10s
     *由于循环里面阻塞获取 所以和同步执行耗时相同
     * @throws Exception e
     */
    @Test
    public void testFuture() throws Exception {
        FutureDemo.testFuture();
    }





    /**
     * future测试 task睡眠10s
     *多线程执行 将异步执行结果收集  再遍历获取
     * @throws Exception e
     */
    @Test
    public void testFutureList() throws Exception {
        FutureDemo.testFutureList();
    }
}
