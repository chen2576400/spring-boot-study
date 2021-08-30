package com.chenning.springbootlearn.excel.POI;

import com.chenning.springbootlearn.util.excel.ExcelUtils;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @Author nchen
 * @Date 2021/8/30 10:33
 * @Version 1.0
 * @Description
 */
public class AnalysisExcel {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void test1() {
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
            for (int i = 1; i < rownum; i++) {//行
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
            System.out.println(modelPOIS.size());
        }
    }

}
