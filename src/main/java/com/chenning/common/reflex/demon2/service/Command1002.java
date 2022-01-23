package com.chenning.common.reflex.demon2.service;

import com.chenning.common.crud.model.User;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 17:14
 **/
public class Command1002 extends Command1000 {
    private String str;
    private User user;

    public Command1002() {

    }

    public Command1002(String str) {
        this.str = str;
    }


    public String say1002() {
        return str;
    }

    public Command1002(User user, String str) {
        this.str = str;
        this.user = user;
    }

    public String say1002(String parm) {
        return str + "拼接参数" + parm;
    }
}
