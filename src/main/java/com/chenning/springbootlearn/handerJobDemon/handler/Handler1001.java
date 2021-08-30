package com.chenning.springbootlearn.handerJobDemon.handler;

import com.chenning.springbootlearn.crud.service.UserService;
import com.chenning.springbootlearn.handerJobDemon.DemonModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 16:19
 **/
public class Handler1001 extends BaseHandler {
    @Autowired
    private UserService service;

    @Override
    public void handler(Map<String, Object> params) {
        DemonModel model = getDemonModel();
        String parm = params.get("parm").toString();

        System.out.println("我是Handler1001参数parm值为============>" + parm);
        System.out.println("我是Handler  Mode============>" + model);
    }
}
