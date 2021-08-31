package com.chenning.springbootlearn.excel.EasyExcel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nchen
 * @Date 2021/8/30 14:46
 * @Version 1.0
 * @Description easyExcel实体类
 * @ExcelProperty 必要的一个注解，注解中有三个参数value,index,converter分别代表列明，列序号，数据转换方式
 * value和index只能二选一，通常不用设置converter
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelEasyExcelSheetOne {
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private Integer age;
    @ExcelProperty(index = 2)
    private String sex;
    @ExcelProperty(index = 3)
    private Integer addressNumber;
    private String memo;

}
