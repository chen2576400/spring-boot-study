package com.chenning.springbootlearn.transactional;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.crud.model.Card;
import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.crud.service.CardService;
import com.chenning.springbootlearn.crud.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author nchen
 * @Date 2021/11/22 14:07
 * @Version 1.0
 * @Description
 */
@Service
public class FunctionUser {
    @Autowired
    UserService userService;

    public User insertUser() {
        User user = new User();
        user.setPassword("666");
        user.setUserName("666");
        user.setAddress("666");
        userService.insert(user);
        return user;
    }




}
