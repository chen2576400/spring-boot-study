package com.chenning.springbootlearn.thread.scheduledThreadPoolExecutor;

import com.chenning.springbootlearn.thread.future.FutureDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/18 10:56
 * 定时任务线程
 */
@Component
public class ScheduledThreadPoolExecutorDemon {
    /**
     * shutdown(); //切记定时任务不能shutdow否则任务直接退出  定时任务不执行 不生效
     * 如果shutDown了 可以再启动  那么定时任务就按照参数接着上次运行时间执行
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledThreadPoolExecutorDemon.class);
    ScheduledThreadPoolExecutor executor1 = null;
    ScheduledThreadPoolExecutor executor2 = null;

    //    @PostConstruct
    public void demom1(Integer taskId) {
        if (taskId==1) {
            executor1 = new ScheduledThreadPoolExecutor(5);//不放循环执行  这里长度不管多少都是单线程
            /**
             * command：执行线程
             * initialDelay：初始化延时
             * period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
             * unit：计时单位  当前秒
             */
            LOGGER.info("================================>>>>>>>>>>>test start");
            executor1.scheduleAtFixedRate(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(new Timestamp(System.currentTimeMillis()) + "我是单线程定时任务啊啊啊啊啊啊啊=================>");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 10, 5, TimeUnit.SECONDS);
            LOGGER.info("================================>>>>>>>>>>>test end");
        }
    }


    /**
     * 定时执行  多线程
     */
    public void demom2(Integer taskId) {
        if (taskId == 2) {
            executor2 = new ScheduledThreadPoolExecutor(5);
            /**
             * command：执行线程
             * initialDelay：初始化延时
             * period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
             * unit：计时单位  当前秒
             */
            LOGGER.info("================================>>>>>>>>>>>test start");
            for (int k = 0; k < 5; k++) {
                executor2.scheduleAtFixedRate(() -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            TimeUnit.SECONDS.sleep(10);
                            System.out.println(new Timestamp(System.currentTimeMillis()) + "我是多线程定时任务啊啊啊啊啊啊啊=================>");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 10, 5, TimeUnit.SECONDS);
            }
//        executor2.shutdown();
            LOGGER.info("================================>>>>>>>>>>>test end");
        }
    }
    //=================================================================================================================================//

    /**
     * 启动线程
     *
     * @param taskId 任务id
     */
    public void startThread(Integer taskId) {
        switch (taskId) {
            case 1:
                if (null == executor1) {
                    demom1(taskId);
                }
                break;
            case 2:
                if (null == executor2) {
                    demom2(taskId);
                }
                break;
        }
    }


    /**
     * 停止线程
     *
     * @param taskId 任务id
     */
    public void stopThread(Integer taskId) {
        try {
            switch (taskId) {
                case 1:
                    if (null != executor1 && !executor1.isShutdown()) {
                        executor1.shutdown();
                        executor1 = null;
                    }
                    break;
                case 2:
                    if (null != executor2 && !executor2.isShutdown()) {
                        executor2.shutdown();
                        executor2 = null;
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时任务线程控制面板
     * @return
     */
    public List<Map<String, String>> taskList() {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("taskId", "1");
        map1.put("taskName", "创建物流初次查询任务");
        map1.put("state", executor1 == null ? "未启动" : "已启动");

        Map<String, String> map2 = new HashMap<>();
        map2.put("taskId", "2");
        map2.put("taskName", "查询物流并初次解析");
        map2.put("state", executor2 == null ? "未启动" : "已启动");

        list.add(map1);
        list.add(map2);
        return list;
    }
}
