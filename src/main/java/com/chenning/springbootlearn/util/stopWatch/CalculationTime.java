package com.chenning.springbootlearn.util.stopWatch;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @Author nchen
 * @Date 2021/7/27 9:32
 * @Version 1.0
 * @Description
 */
@Log4j2
public class CalculationTime {

    public void sumTime1() {
        Instant start = Instant.now();
// 业务逻辑代码...
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis(); // 单位为毫秒
        log.info("---->电话线程执行完结，耗费{}秒！",
                String.format("%.2f", timeElapsed / 1000.0));
    }


    public void sumTime2() {
        long start = System.currentTimeMillis();
// 业务逻辑代码...
        long end = System.currentTimeMillis();
        long timeElapsed = end - end; // 单位为毫秒
        log.info("---->电话线程执行完结，耗费{}秒！",
                String.format("%.2f", timeElapsed / 1000.0));
    }


    public void sumTime3() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
// 业务逻辑代码...
        stopWatch.stop();
// 统计执行时间（秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒.");
// 统计执行时间（毫秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.MILLISECONDS) + " 毫秒.");
// 统计执行时间（纳秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.NANOSECONDS) + " 纳秒.");

        //System.out.println("Time Elapsed: " + stopWatch.getTime() + "ms"); // 单位为毫秒

    }
}
