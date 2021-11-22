package com.chenning.springbootlearn.transactional;

import com.chenning.springbootlearn.crud.model.Card;
import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.crud.service.CardService;
import com.chenning.springbootlearn.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author nchen
 * @Date 2021/11/22 16:02
 * @Version 1.0
 * @Description
 */
@Service
public class FunctionOwnClass {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;


    @Autowired
    FunctionTestService testService;


    @Transactional
    public User insertUser() {
        User user = new User();
        user.setPassword("666");
        user.setUserName("666");
        user.setAddress("666");
        userService.insert(user);
        return user;
    }


    public void insertCard() {
        User user = insertUser();
        System.out.println(1 / 0);
        Card card = new Card();
        card.setUserId(user.getUserId());
        card.setCardNumber("7777");
        cardService.insert(card);
    }


    @Transactional
    public void insertCardTransactional() {
        User user = insertUser();
        System.out.println(1 / 0);
        Card card = new Card();
        card.setUserId(user.getUserId());
        card.setCardNumber("7777");
        cardService.insert(card);
    }


    public void testDo1() {
        testService.testFunction1();
    }

}
