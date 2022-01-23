package com.cn.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 16:28
 **/
@Entity
@Table
@Data
public class Card extends  BaseModel  implements Serializable {

    private Integer userId;

    private String cardNumber;
}
