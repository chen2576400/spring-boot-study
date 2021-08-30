package com.chenning.springbootlearn.excel.POI;

import com.chenning.springbootlearn.util.excel.ExcelUtils;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author nchen
 * @Date 2021/8/30 10:33
 * @Version 1.0
 * @Description
 */
@Log4j2
public class AnalysisExcelByPOI {
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


    public void test1() throws InterruptedException {
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        ArrayList<ModelPOI> modelPOIS = Lists.<ModelPOI>newArrayList();
        if (workbook != null) {
            sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            StopWatch stopWatch=new StopWatch();
            stopWatch.start();
            for (int i = 1; i < rownum; i++) {//行
                TimeUnit.MILLISECONDS.sleep(60);
                ModelPOI model = new ModelPOI();
                row = sheet.getRow(i);
                for (int j = 0; j < colnum; j++) {//列

                    cellData = (String) ExcelUtils.getCellFormatValue(row.getCell(j));
                    switch (j) {
                        case 0:   //姓名
                            model.setName(cellData);
                            break;
                        case 1:   //年龄
                            model.setAge(Integer.parseInt(cellData));
                            break;
                        case 2:   //性别
                            model.setSex(cellData);
                            break;
                        case 3:   //身份证号码
                            model.setAddressNumber(Integer.parseInt(cellData));
                            break;
                        default:
                            break;
                    }
                }
                modelPOIS.add(model);
            }
            stopWatch.stop();
            log.info("执行时长：" + stopWatch.getTime(TimeUnit.MILLISECONDS) + " 毫秒.");
            System.out.println("集合长度"+modelPOIS.size());
        }
    }

}
