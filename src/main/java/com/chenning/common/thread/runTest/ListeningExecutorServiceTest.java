package com.chenning.common.thread.runTest;

import com.chenning.common.thread.ListeningExecutorService.ListeningExecutorServiceDemon;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author nchen
 * @Date 2021/8/7 16:15
 * @Version 1.0
 * @Description
 */
public class ListeningExecutorServiceTest {

    @Test
    public void testListeningExecutor() {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        ListeningExecutorServiceDemon.test();
        stopWatch.stop();
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒.");
    }
}
