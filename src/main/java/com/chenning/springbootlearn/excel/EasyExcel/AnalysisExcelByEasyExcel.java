package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.chenning.springbootlearn.util.excel.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author nchen
 * @Date 2021/8/30 14:54
 * @Version 1.0
 * @Description
 */
public class AnalysisExcelByEasyExcel {
    private static Workbook workbook = null;

    static {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources("classpath*:static/uploadFile/解析模板.xlsx");
            if (resources.length > 0) {
                Resource resource = resources[0];
                String absolutePath = resource.getFile().getAbsolutePath();//文件路径
                String suffixFilename = ExcelUtils.getSuffixFilename(resource.getFile().getAbsolutePath());//文件后缀
                InputStream inputStream = resource.getInputStream();
                workbook = ExcelUtils.readExcel(inputStream, suffixFilename);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void  test1(){
        ExcelReaderBuilder reader = EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook), ModelEasyExcel.class, new OwnerExcelListener());
        EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook))
                .sheet()
                .registerReadListener(new OwnerExcelListener())
                .head(ModelEasyExcel.class)
                .doRead();
    }


    public  void  test2(){
        ExcelReaderBuilder reader = EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook), ModelEasyExcel.class, new OwnerExcelListener());
        List<Object> objects = EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook))
                .sheet()
                .registerReadListener(new OwnerExcelListener())
                .head(ModelEasyExcel.class)
                .doReadSync();
    }
}
