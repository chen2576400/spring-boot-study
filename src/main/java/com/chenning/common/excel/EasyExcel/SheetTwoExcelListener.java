package com.chenning.common.excel.EasyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.log4j.Log4j2;

/**
 * @Author nchen
 * @Date 2021/8/31 11:50
 * @Version 1.0
 * @Description
 */
@Log4j2
public class SheetTwoExcelListener  extends AnalysisEventListener<ModelEasyExcelSheetTwo> {
    @Override
    public void invoke(ModelEasyExcelSheetTwo modelEasyExcelSheetTwo, AnalysisContext analysisContext) {
        System.out.println("当前sheet2对象" + modelEasyExcelSheetTwo);
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
