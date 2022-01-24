package com.cn.apilimt.test;


import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于guava包下的RateLimimiter限流  实现每秒令牌数量控制
 *
 *
 *
 *
 * 每秒钟只能获得一个令牌情况下   测试结果如下
 * 当i等于1的时候,直接获取到了令牌,当i大于1的时候会随着i的增长,获取令牌的等待时间也在增长
 */
public class RateLimitTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        long begin = System.currentTimeMillis();
        // 每秒允许发放1个令牌
        double permits=1.0;
        RateLimiter limiter = RateLimiter.create(permits);
        for (int i = 1; i <= 5; i++) {
            // 获取i个令牌, 当i超过permits会被阻塞
            double waitTime = limiter.acquire(i);
            System.out.println("curTime=" + sdf.format(new Date()) + " call index:" + i + " waitTime:" + waitTime);
        }
        long end =  System.currentTimeMillis();
        System.out.println("begin time:" + sdf.format(new Date(begin))+",end time:"+sdf.format(new Date(end))+",Total task time："+(end-begin));
    }
}
