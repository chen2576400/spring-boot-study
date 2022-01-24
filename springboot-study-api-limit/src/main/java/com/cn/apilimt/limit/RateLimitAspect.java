package com.cn.apilimt.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Dominick Li
 * @CreateTime 2020/5/2 11:57
 * @description  基于guava包下的RateLimimiter限流  实现每秒令牌数量控制
 *
 *
 * RateLimiter.create(3.0);创建一个限流器，参数代表每秒生成3个令牌，通过limiter.acquire()来获取令牌，
 * 当然也可以通过tryAcquire(int permits, long timeout, TimeUnit unit)来设置等待超时时间的方式获取令牌，
 * 如果超timeout为0或则调用无参的tryAcquire()，则代表非阻塞，获取不到立即返回，支持阻塞或可超时的令牌消费。

 **/
@Component
@Scope
@Aspect
public class RateLimitAspect {
    //每秒只发出5个令牌,内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 业务层切点
     */
    @Pointcut("@annotation(com.cn.apilimt.limit.RateLimit)")
    public void ServiceAspect() { }

    @Around("ServiceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            //tryAcquire()是非阻塞, rateLimiter.acquire()是阻塞的
            if (rateLimiter.tryAcquire()) { //获取一个令牌
                obj = joinPoint.proceed();
            } else {
                //拒绝了请求（服务降级）
                obj = "The system is busy, please visit after a while";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
