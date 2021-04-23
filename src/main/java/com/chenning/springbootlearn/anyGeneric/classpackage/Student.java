package com.chenning.springbootlearn.anyGeneric.classpackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nchen
 * @Date 2021/4/23 15:37
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
}
