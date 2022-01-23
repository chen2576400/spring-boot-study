package com.cn.jpa.config;

import com.cn.jpa.model.BaseModel;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 15:16
 *
 **/
public class TestEntityListeners {
//
//    @PrePersist
//    public void PrePersist(BaseModel entity){
//        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
////        System.out.println("开始保存--"+entity.toString());
//    }
//    @PreUpdate
//    public void PreUpdate(BaseModel entity){
////        System.out.println("开始更新--"+entity.toString());
//    }
//
//    @PostPersist
//    public void PostPersist(BaseModel entity){
//        entity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
////        System.out.println("结束保存--"+entity.toString());
//    }
//
//    @PostUpdate
//    public void PostUpdate(BaseModel entity){
////        System.out.println("结束更新--"+entity.toString());
//    }
}
