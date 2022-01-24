package com.cn.apilimt.controller;

import com.cn.apilimt.limit.RateLimit;
import com.cn.apilimt.limit.SemaphoreLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Dominick Li
 * @CreateTime 2020/5/2 10:41
 * @description
 **/
@RestController
public class LimitTestController {

    @RequestMapping("/ratelimit")
    @RateLimit
    public String ratelimit() throws Exception{
        //假设业务处理了1秒
        TimeUnit.SECONDS.sleep(2);
        return "success";
    }

    /**
     * 设置limitKey=SemaphoreKey,并且许可证只有3个
     */
    @RequestMapping("/SemaphoreLimit")
    @SemaphoreLimit(limitKey ="SemaphoreKey", value =3)
    public String SemaphoreLimit() throws Exception{
        //假设业务处理了1秒
        TimeUnit.SECONDS.sleep(1);
        return "success";
    }
}
