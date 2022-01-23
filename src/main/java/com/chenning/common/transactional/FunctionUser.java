package com.chenning.common.transactional;

import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
