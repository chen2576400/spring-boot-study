package com.chenning.springbootlearn.aop;

import java.lang.annotation.*;

/**
 * @Author nchen
 * @Date 2021/8/7 11:09
 * @Version 1.0
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitTime {
    int time() default 1; // 访问次数，默认为10次

    long timeout() default 1000; // 过期时间，时间戳间隔

}
