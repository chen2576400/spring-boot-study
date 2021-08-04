package com.chenning.springbootlearn.MybatisPlusDemonTest.thread;

import com.chenning.springbootlearn.thread.forkJoin.ForkJoinDemoTask;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @Author nchen
 * @Date 2021/8/4 17:15
 * @Version 1.0
 * @Description
 */
public class ForkJoinDemonTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        List<Integer> integers=new ArrayList<>();
        for (int i=1;i<=500;i++){
            integers.add(i);
        }
        // Fork/Join框架的线程池
        ForkJoinPool pool = new ForkJoinPool(15);//给的线程数量越多越快
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ForkJoinDemoTask joinDemoTask= new ForkJoinDemoTask(0, integers.size()-1,integers,40);
        ForkJoinTask<List<Integer>> submit = pool.submit(joinDemoTask);
        List<Integer> list = submit.get();
        stopWatch.stop();
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 毫秒.");
        //for (Integer i:list){
        //    System.out.println(i);
        //}
        System.out.println(list.size());
    }
}
