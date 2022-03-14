package com.cn.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 16:29
 **/
@Entity
@Table
@Data
public class User extends  BaseModel  implements Serializable {


    private String password;

    private String userName;

    private String address;
}
