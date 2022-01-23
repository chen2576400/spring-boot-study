package com.cn.jpa.dao;

import com.cn.jpa.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 16:32
 **/
public interface CardDao extends JpaRepository<Card,Integer> {

}
