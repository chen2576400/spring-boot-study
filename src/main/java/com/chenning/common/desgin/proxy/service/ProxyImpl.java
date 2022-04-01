package com.chenning.common.desgin.proxy.service;

import com.chenning.common.crud.model.User;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 17:09
 **/
public class ProxyImpl implements IProxy{
    @Override
    public void say(String parm) {
        System.out.println("默认实现");
    }

    @Override
    public void dance(User user) {
        System.out.println("默认实现");

    }

    @Override
    public void fight(String parm) {
        System.out.println("代理的真实对象不存在的该方法");
    }
}
