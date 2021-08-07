package com.chenning.springbootlearn.thread.ListeningExecutorService;

import com.google.common.util.concurrent.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author nchen
 * @Date 2021/8/7 15:46
 * @Version 1.0
 * @Description
 */
public class ListeningExecutorServiceDemon {

    static ExecutorService executorService = new ThreadPoolExecutor(50, 50,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder()
            .setNameFormat("Excel-OT-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());


    public static List<String> test() {
        ExecutorService executorService = ListeningExecutorServiceDemon.executorService;
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5);


        List<ListenableFuture<String>> futureList = asList.stream().map(integer -> {
            ListenableFuture<String> submit = listeningExecutorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String st = "hello" + integer;
                    if (integer==2){
                        TimeUnit.SECONDS.sleep(4);
                    }else {
                        TimeUnit.SECONDS.sleep(2);
                    }

                    return st;
                }
            });
            Futures.addCallback(submit, new FutureCallback<String>() {
                @SneakyThrows
                @Override
                public void onSuccess(String s) {//这个已经是执行成功后的结果了
                    //String s1 = submit.get();
                    System.out.println("成功了===" + s + "[][][][]");
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("失败了===" + throwable.getMessage());
                }
            }, listeningExecutorService);


            return submit; //这里决定了返回类型
        }).collect(Collectors.toList());


        List<String> list = futureList.stream().map(stringListenableFuture -> {
            try {
                return stringListenableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        //也可以直接onSuccess里面的结果  里面貌似已经是get阻塞后的

        return list;

    }
}
