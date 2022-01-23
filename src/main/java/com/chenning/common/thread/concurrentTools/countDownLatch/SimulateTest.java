package com.chenning.common.thread.concurrentTools.countDownLatch;

import com.chenning.common.crud.model.UserVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author nchen
 * @Date 2021/12/1 11:00
 * @Version 1.0
 * @Description
 */
public class SimulateTest {


    //java原子包,利用了CAS算法保证了数据的原子性,
    static AtomicInteger successCount = new AtomicInteger(0);//线程安全的计数器
    static AtomicInteger sum = new AtomicInteger(0);//线程安全的计数器(调用总次数)

    static int crudSum = 0;//非线程安全(调用总次数)

    public static void main(String[] args) throws Exception {
        //总共的线程数
        int threadSize = 100;
        //每秒并发数
        final int count = 20;
        //同步执行器,必须等所有线程都完成任务,才能执行后面的代码
        CountDownLatch downLatch = new CountDownLatch(threadSize);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < threadSize; i++) {
            int finalI = i;
            fixedThreadPool.submit(() -> {
                //没经过20的整数倍时候休眠1s
                if (finalI % count == 0) {  //可看成绝对值的取余数运算
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                doGet();

                {//方法总调用次数
                    sum.incrementAndGet(); //线程安全
                    crudSum++;  //线程不安全
                }
                successCount.incrementAndGet();//以原子方式将当前值递增1并在递增后返回新值。它相当于i ++操作。
                downLatch.countDown();
            });
        }
        downLatch.await();//await() 能够阻塞线程 直到调用N次end.countDown() 方法才释放线程  不然会直接异步处理直接输出后面逻辑
        System.out.println("购买商品成功的次数:" + successCount.get());
        System.out.println("调用的总数:" + sum.get() + "==" + crudSum);
        fixedThreadPool.shutdown();
    }


    public static void doGet() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:1025/user/findAllUserByID")
                .queryParam("id", 1)
                .queryParam("s", 4);

        String url = builder.toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        RestTemplate restTemplate = new RestTemplate();
        //直接返回list对象
        ParameterizedTypeReference<List<UserVo>> responseType = new ParameterizedTypeReference<List<UserVo>>() {
        };
        ResponseEntity<List<UserVo>> exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), responseType);
        List<UserVo> body = exchange.getBody();

    }

}
