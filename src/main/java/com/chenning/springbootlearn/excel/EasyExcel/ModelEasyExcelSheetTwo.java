package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nchen
 * @Date 2021/8/31 11:51
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelEasyExcelSheetTwo {
    @ExcelProperty(index = 0)
    private String classes;
    @ExcelProperty(index = 1)
    private Integer studentNumber;


}
