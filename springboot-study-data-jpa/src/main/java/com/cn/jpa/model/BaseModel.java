package com.cn.jpa.model;

import lombok.Data;
import javax.persistence.*;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 14:56
 *
 * 只有使用了@EnableJpaAuditing注解，@CreateDate @LastModifiedBy等注解才会生效；
 * 只有实现了AuditorAware接口，才会指定@CreateBy  @LastModifiedBy用户名信息。
 *
 *
 *
 *
 * 标注为@MappedSuperclass的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 * 标注为@MappedSuperclass的类不能再标注@Entity或@Table注解，也无需实现序列化接口。
 * SINGLE_TABLE 是将父类和其所有的子类集合在一块，存在一张表中，并创建一个新的字段来判断对象的类型。
 * TABLE_PER_CLASS 是为每一个类创建一个表，这些表是相互独立的。
 * JOINED 是将父类、子类分别存放在不同的表中，并且建立相应的外键，以确定相互之间的关系
 **/

@Inheritance(strategy= InheritanceType.SINGLE_TABLE)//选择继承策略
@MappedSuperclass //标明是 基类
//@EntityListeners(TestEntityListeners.class)//数据监听类(先去掉，打印日志太烦)
@Data
public class BaseModel {
/*
    @Id 标识此字段是主键
    @GeneratedValue 设置主键的自增策略,默认是AUTO
    TABLE 使用一个特定的数据库表格来保存主键。
    SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列。
    IDENTITY 主键由数据库自动生成（主要是自动增长型）
    AUTO 主键由程序控制。
    */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

//    @CreatedDate  //在记录创建的时候自动插入创建时间
//    @Column(name = "created_date", updatable = false)
//    private Timestamp createdDate;
//
//    @LastModifiedDate  //在记录修改的时候自动修改操作 时间
//    @Column(name = "updated_date", updatable = false)
//    private Timestamp updatedDate;
//
//
//    @Column(name = "created_by", updatable = false, length = 64)
//    private String createdBy;
//
//
//    @Column(name = "updated_by", length = 64)
//    private String updatedBy;
}
