package com.chenning.common.desgin.proxy.service;

import com.chenning.common.crud.model.User;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-31 17:09
 **/
public interface IProxy {

    void  say(String parm);


    void  dance(User user);


    void  fight(String parm);
}
