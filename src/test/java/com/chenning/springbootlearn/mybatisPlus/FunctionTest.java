package com.chenning.springbootlearn.mybatisPlus;

import com.alibaba.fastjson.JSON;
import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.crud.model.Card;
import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.crud.service.CardService;
import com.chenning.springbootlearn.crud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-02 15:43
 **/
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class FunctionTest {
    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;
    @Test
    public void findAllUser() {
        List<User> allUser = userService.findAllUser();
        System.out.println(JSON.toJSONString(allUser));
    }

    @Test
    public void findAllCard() {
        List<Card> allCard = cardService.findAllCard();
        System.out.println(JSON.toJSONString(allCard));
    }
}
