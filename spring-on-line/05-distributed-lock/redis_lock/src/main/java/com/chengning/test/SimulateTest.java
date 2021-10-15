package com.chengning.test;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulateTest {

    public static void main(String[] args) {
        //并发的线程数
        int threadSize=100;
        ExecutorService fixedThreadPool=Executors.newFixedThreadPool(threadSize);
        for(int i=0;i<threadSize;i++) {
            fixedThreadPool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RestTemplate restTemplate = new RestTemplate(); //这里使用nginx配置
                String result = restTemplate.getForObject("http://localhost/deduct_stock/1", String.class);
                System.out.println(result);
            });
        }
    }
}
