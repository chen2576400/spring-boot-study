package com.chenning.springbootlearn.mybatisPlus.excel.poi;

import com.chenning.springbootlearn.excel.POI.AnalysisExcel;
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
        AnalysisExcel excel=new AnalysisExcel();
        excel.test1();
    }
}
