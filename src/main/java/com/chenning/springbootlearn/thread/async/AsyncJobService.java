package com.chenning.springbootlearn.thread.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 16:34
 */
@Service
public class AsyncJobService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncJobService.class);

    @Async("taskExecutor")
    public Future<Integer> task(Integer i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("================================>>>>>>>>>>>" + i);
        return new AsyncResult<>(i);
    }
}
