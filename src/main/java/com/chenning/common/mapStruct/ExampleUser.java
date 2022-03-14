package com.chenning.common.mapStruct;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author nchen
 * @Date 2021/12/2 10:27
 * @Version 1.0
 * @Description
 */
@AllArgsConstructor
@Data
public class ExampleUser {
    private Long id;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private ExampleRole role;

    private Timestamp userTimestamp;
    private String commonName1;

}
