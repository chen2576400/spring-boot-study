package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @Author nchen
 * @Date 2021/8/30 14:48
 * @Version 1.0
 * @Description
 */
public class ExcelListener extends AnalysisEventListener {

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        //一行数据读取后会调用
        System.out.println("当前对象"+o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //通知文件读取完毕
        System.out.println("读取完毕");
    }
}
