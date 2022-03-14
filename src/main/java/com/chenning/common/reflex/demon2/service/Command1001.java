package com.chenning.common.reflex.demon2.service;

import com.chenning.common.crud.model.User;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 17:14
 **/
public class Command1001 {
    private String str;
    private User user;

    public Command1001(){

    }

    public Command1001(String str) {
        this.str = str;
    }


    public Command1001(User user,String str) {
        this.str = str;
        this.user=user;
    }

    public String say1001() {
        return str;
    }


    public String say1001(String parm) {
        return str + "拼接参数"+parm;
    }


}
