package com.chenning.common.excel.runTest.poi;

import com.chenning.common.excel.POI.AnalysisExcelByPOI;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @Author nchen
 * @Date 2021/8/30 10:47
 * @Version 1.0
 * @Description
 */
public class ExcelTest {


    @SneakyThrows
    @Test
    public void test1(){
        AnalysisExcelByPOI excel=new AnalysisExcelByPOI();
        excel.test1();
    }
}
