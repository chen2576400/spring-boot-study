package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.chenning.springbootlearn.util.excel.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author nchen
 * @Date 2021/8/30 14:54
 * @Version 1.0
 * @Description
 */
public class AnalysisExcelByEasyExcel {

    List<Integer> list = new ArrayList<>();
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

    //读取默认第一个sheet
    public void test1() {
        EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook))
                .sheet()// 这里默认读取第一个sheet
                .head(ModelEasyExcelSheetOne.class)
                .registerReadListener(new SheetOneExcelListener())
                .headRowNumber(1)//从第几行开始读取 不写默认是1从第二行
                .doRead();//读取

    }


    //读取多个sheet
    public void test2() {
        ExcelReader excelReader = EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook)).build();

        SheetOneExcelListener sheetOneExcelListener = new SheetOneExcelListener();
        ReadSheet readSheet1 = EasyExcel.readSheet(0)
                .head(ModelEasyExcelSheetOne.class)
                .registerReadListener(sheetOneExcelListener)
                .build();


        ReadSheet readSheet2 = EasyExcel.readSheet(1)
                .head(ModelEasyExcelSheetTwo.class)
                .registerReadListener(new SheetTwoExcelListener())
                .build();
// 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
        excelReader.read(readSheet1, readSheet2);
// 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();


    }
}
