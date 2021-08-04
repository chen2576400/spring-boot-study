package com.chenning.springbootlearn.thread.forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @Author nchen
 * @Date 2021/8/4 16:03
 * @Version 1.0
 * @Description
 */
public class ForkJoinDemoTask extends RecursiveTask<List<Integer>> {


    private List<Integer> tList;

    private int start;

    private int end;

    //每个线程处理的任务数不超过的数量(临界值)
    private Integer taskNumber;


    public ForkJoinDemoTask(int start, int end, List<Integer> tList, Integer taskNumber) {
        this.tList = tList;
        this.taskNumber = taskNumber;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Integer> compute() {  //(这里是递归)
        //集合长度小于每个线程任务数 则不切割((0, list.size-1 <  taskNumber))
        if ((end - start) < taskNumber) {
            LOGGER.info("开始计算的部分：startValue = " + start + ";endValue = " + end +"执行的数量为"+(end-start) );
            List<Integer> integers=new ArrayList<>();
            for (int i = start; i <= end; i++) {
                Integer t=tList.get(i);
                integers.add(t);
            }
            return integers;
        }else {   // 将大任务分解成两个小任务

            ForkJoinDemoTask subTask1 = new ForkJoinDemoTask(start, (start + end) / 2,tList,taskNumber);
            ForkJoinDemoTask subTask2 = new ForkJoinDemoTask((start + end) / 2 + 1, end,tList,taskNumber);
            //subTask1.fork();subTask2.fork();
            invokeAll(subTask1, subTask2);
            List<Integer> join1 = subTask1.join();
            List<Integer> join2 = subTask2.join();
            List   array=new ArrayList(){{addAll(join1);addAll(join2);};};
            return array;
        }

    }
}
