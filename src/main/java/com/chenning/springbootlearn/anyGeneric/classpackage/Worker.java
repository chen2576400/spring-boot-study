package com.chenning.springbootlearn.anyGeneric.classpackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author nchen
 * @Date 2021/4/23 14:34
 * @Version 1.0
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private String name;
    private String sex;
}
