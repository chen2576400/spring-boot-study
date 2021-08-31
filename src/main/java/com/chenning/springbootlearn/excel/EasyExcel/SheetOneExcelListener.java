package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author nchen
 * @Date 2021/8/30 14:48
 * @Version 1.0
 * @Description
 */
@Log4j2
//有个很重要的点   不能被spring管理,要每次读取excel都要new。
//这边就会有一个问题：如果OwnerExcelListener中需要用到Spring中的组键怎么办？
/**
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 */
public class SheetOneExcelListener extends AnalysisEventListener<ModelEasyExcelSheetOne> {
    //由于每次读都是新new OwnerExcelListener的，所以这个list不会存在线程安全问题
    List<ModelEasyExcelSheetOne> list = new ArrayList<>();
    private static final int BATCH_COUNT = 100;

    @Override
    public void invoke(ModelEasyExcelSheetOne o, AnalysisContext analysisContext) {
        //一行数据读取后会调用
        System.out.println("当前sheet1对象" + o);
        list.add(o);
        if (list.size() >= BATCH_COUNT) {
            // TODO: 2021/8/30  
            // 100条处理完完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //通知文件读取完毕
        System.out.println("读取完毕");
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }

    }
}
