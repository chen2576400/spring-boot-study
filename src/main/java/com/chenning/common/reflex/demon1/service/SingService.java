package com.chenning.common.reflex.demon1.service;

import com.alibaba.fastjson.JSON;
import com.chenning.common.crud.model.User;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 17:33
 **/

public class SingService extends  BaseService{

    public void listUser(){
        List<User> allUser = userService.findAllUser();
        System.out.println(JSON.toJSONString(allUser));
    }

}
