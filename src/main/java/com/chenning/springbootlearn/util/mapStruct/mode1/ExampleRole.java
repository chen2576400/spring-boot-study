package com.chenning.springbootlearn.util.mapStruct.mode1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author nchen
 * @Date 2021/12/2 10:28
 * @Version 1.0
 * @Description
 */
@AllArgsConstructor
@Data
public class ExampleRole {
    private Long id;
    private String roleName;
    private String description;

    private Timestamp roleTimestamp;
    private String commonName2;

}
