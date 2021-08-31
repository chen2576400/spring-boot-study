package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author nchen
 * @Date 2021/8/31 14:19
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelWriteExcel {
    @ExcelProperty(value = "字符串标题")
    private String title;
    @ExcelProperty(value = "日期标题")
    private Date date;
    @ExcelProperty(value = "数字标题")
    private Double doubleData;

}
