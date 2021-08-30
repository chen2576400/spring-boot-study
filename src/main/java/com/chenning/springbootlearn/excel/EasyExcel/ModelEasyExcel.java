package com.chenning.springbootlearn.excel.EasyExcel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nchen
 * @Date 2021/8/30 14:46
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelEasyExcel {
    private String name;
    private Integer age;
    private String sex;
    private Integer addressNumber;
}
