package com.chenning.springbootlearn.reflex.demon2.service;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 17:14
 **/
public class Command1002 {
    private String str;


    public Command1002(String str) {
        this.str = str;
    }


    public String say1002() {
        return str;
    }



    public String say1002(String parm) {
        return str + "拼接参数"+parm;
    }
}
