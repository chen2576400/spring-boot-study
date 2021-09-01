package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.chenning.springbootlearn.util.excel.ExcelUtils;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.*;

/**
 * @Author nchen
 * @Date 2021/8/30 14:54
 * @Version 1.0
 * @Description
 */
public class AnalysisExcelByEasyExcel {
    final String PATH = "D:\\";

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

    /**
     * 读取默认第一个sheet
     */
    public void test1() {
        EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook))
                .sheet()// 这里默认读取第一个sheet
                .head(ModelEasyExcelSheetOne.class)
                .registerReadListener(new SheetOneExcelListener())
                .headRowNumber(1)//从第几行开始读取 不写默认是1从第二行
                .doRead();//读取

    }


    /**
     * 读取多个sheet
     */
    public void test2() {
        ExcelReader excelReader = EasyExcel.read(ExcelUtils.WorkbookToInputStream(workbook)).build();

        ReadSheet readSheet1 = EasyExcel.readSheet(0)
                .head(ModelEasyExcelSheetOne.class)
                .registerReadListener(new SheetOneExcelListener())
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


    /**
     * 生成excel文件到指定文件夹(单个sheet)
     */
    public void test3() {
        List<ModelWriteExcel> models = getData();
        String fileName = PATH + "EasyExcel导出.xlsx";

        // 忽略 title列 不导出
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("title");

        // 只导出 title列
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("title");

        // 当前表示 第coulmnIndex+1列 每隔eachRow行 合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 1);

        EasyExcel.write()
                .file(fileName)
                .head(ModelWriteExcel.class)
                .sheet("sheet页1")
                //.excludeColumnFiledNames(excludeColumnFiledNames)
                //.includeColumnFiledNames(includeColumnFiledNames)
                .registerWriteHandler(loopMergeStrategy)
                .doWrite(models); //已经包含了finish


/*        // 写法2，方法二需要手动关闭流
        fileName = System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(data(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();*/

    }


    /**
     * 生成excel文件到指定文件夹(单个sheet)
     */
    public void test4() {
        List<ModelWriteExcel> models = getData();
        String fileName = PATH + "EasyExcel导出.xlsx";

        // 忽略 title列 不导出
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("title");
        // 只导出 title列
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("title");
        // 当前表示 第coulmnIndex+1列 每隔eachRow行 合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 1);



        ExcelWriter excelWriter = EasyExcel.write().file(fileName).build();

        //第一个sheet
        WriteSheet writeSheet1 = EasyExcel.writerSheet("sheet1").head(ModelWriteExcel.class).build();
        //第二个sheet
        WriteSheet writeSheet2 = EasyExcel.writerSheet("sheet2").head(ModelWriteExcel.class)
                .registerWriteHandler(loopMergeStrategy).build();


        //写入第一个
        excelWriter.write(models, writeSheet1);
        //写入第二个
        excelWriter.write(models, writeSheet2);
        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

    }


    /**
     * 浏览器 响应下载单个sheet的excel
     * @param httpServletResponse
     * @param request
     */
    public void test5(HttpServletResponse httpServletResponse, HttpServletRequest request) {
        try {
            List<ModelWriteExcel> models = getData();

            httpServletResponse.setHeader("content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("最新指标数据模板.xlsx", "utf-8"));
            EasyExcel.write()
                    .file(httpServletResponse.getOutputStream())
                    .head(ModelWriteExcel.class)
                    .sheet("导出模板sheet")
                    .doWrite(models); //已经包含了finish
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 浏览器 响应下载多个sheet的excel
     * @param httpServletResponse
     * @param request
     */
    public void test6(HttpServletResponse httpServletResponse, HttpServletRequest request) {
        try {
            List<ModelWriteExcel> models = getData();

            httpServletResponse.setHeader("content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("最新指标数据模板.xlsx", "utf-8"));
            ExcelWriter excelWriter = EasyExcel.write()
                    .file(httpServletResponse.getOutputStream()).build();


            //第一个sheet
            WriteSheet writeSheet1 = EasyExcel.writerSheet("导出模板sheet1").head(ModelWriteExcel.class).build();
            //第二个sheet
            WriteSheet writeSheet2 = EasyExcel.writerSheet("导出模板sheet2").head(ModelWriteExcel.class).build();
            //写入第一个
            excelWriter.write(models, writeSheet1);
            //写入第二个
            excelWriter.write(models, writeSheet2);
            //千万别忘记finish 会帮忙关闭流
            excelWriter.finish();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private List<ModelWriteExcel> getData() {
        ArrayList<ModelWriteExcel> modelWriteExcels = Lists.<ModelWriteExcel>newArrayList();
        for (int i = 0; i < 10; i++) {
            ModelWriteExcel modelWriteExcel = ModelWriteExcel.builder().date(new Date(System.currentTimeMillis()))
                    .doubleData(0.56)
                    .title("字符串" + i)
                    .build();
            modelWriteExcels.add(modelWriteExcel);
        }
        return modelWriteExcels;
    }
}
