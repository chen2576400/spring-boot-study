package com.chenning.springbootlearn.reflex.model.service;

import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.demonBuild.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 16:37
 **/
@Service
public class BaseService {
    @Autowired
    UserService userService;

    public void x(User user) {
        List<User> allUser = userService.findAllUser();
        System.out.println(("用户ID"+user.getUserId() +"用户名字"+user.getUserName()+"所有用户数量")+allUser==null?0:allUser.size());
    }

    public void doSomeThingByDance(User user){
        System.out.println("DanceService===============>"+"参数用户"+user.getUserName());
    }


    public void doSomeThingBySing(String test,User user){
        System.out.println("SingService==========>"+test+"参数用户"+user.getUserName());
    }
}
