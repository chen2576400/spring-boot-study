package com.chenning.common.desgin.proxy.service;

import com.chenning.common.crud.model.User;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 14:36
 **/
public interface IHello {

    void  say(String parm);


    void  dance(User user);
}
