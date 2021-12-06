package com.chenning.springbootlearn.util.mapStruct.mode1;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author nchen
 * @Date 2021/12/2 10:29
 * @Version 1.0
 * @Description
 */
@Data
public class ExampleUserRoleDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 角色名
     */
    private String roleName;


    private String timestamp;

    private String commonName1;


    private String commonName2;
}
