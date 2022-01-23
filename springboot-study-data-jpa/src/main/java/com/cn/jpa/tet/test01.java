package com.cn.jpa.tet;

import com.alibaba.fastjson.JSON;
import com.cn.jpa.SpringBootDataJpaApplication;
import com.cn.jpa.dao.UserDao;
import com.cn.jpa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-23 15:28
 **/
@SpringBootTest(classes = SpringBootDataJpaApplication.class)
@RunWith(SpringRunner.class)
public class test01 {
    @Autowired
    private UserDao dao;

    @Test
    public void dosomthing01() {
        List<User> all = dao.findAll();
        System.out.println(JSON.toJSONString(all));
    }
}
