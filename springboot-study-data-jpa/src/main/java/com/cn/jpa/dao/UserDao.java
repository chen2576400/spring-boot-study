package com.cn.jpa.dao;

import com.cn.jpa.model.Card;
import com.cn.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 16:33
 **/
public interface UserDao extends JpaRepository<User,Integer> {
}
