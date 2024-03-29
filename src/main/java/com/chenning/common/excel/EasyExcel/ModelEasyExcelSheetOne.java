package com.chenning.common.excel.EasyExcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
 *
 * 默认不加ExcelProperty 的注解的都会参与读写
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
    @ExcelIgnore //忽略解析字段
    private String memo;

}
