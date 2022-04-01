package com.chenning.common.desgin.proxy.service;

import com.alibaba.fastjson.JSON;
import com.chenning.common.crud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 14:36
 **/
public class Hello03Impl implements IHello{
    private Logger logger = LoggerFactory.getLogger(Hello03Impl.class);

    @Override
    public void say(String parm) {
        logger.info("HI"+this.getClass().getName()+"参数为"+parm);
    }

    @Override
    public void dance(User user) {
        logger.info("HI"+this.getClass().getName()+"参数为"+ JSON.toJSONString(user));
    }
}
