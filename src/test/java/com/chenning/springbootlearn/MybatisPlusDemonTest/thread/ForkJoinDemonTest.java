package com.chenning.springbootlearn.MybatisPlusDemonTest.thread;

import com.chenning.springbootlearn.thread.forkJoin.ForkJoinDemoTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

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
        for (int i=1;i<=5000;i++){
            integers.add(i);
        }
        // Fork/Join框架的线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemoTask joinDemoTask= new ForkJoinDemoTask(0, integers.size()-1,integers,40);
        ForkJoinTask<List<Integer>> submit = pool.submit(joinDemoTask);
        List<Integer> list = submit.get();
        for (Integer i:list){
            System.out.println(i);
        }
        System.out.println(list);
    }
}
