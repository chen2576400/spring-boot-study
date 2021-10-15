package com.chengning.controller;

import com.chengning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    OrderService orderService;

    /**
     *
     * 如果报一下错误
     * com.mysql.cj.jdbc.MysqlXAException: XAER_RMERR: Fatal error occurred in the transaction branch - check your data for consistency
     * at com.mysql.cj.jdbc.MysqlXAConnection.mapXAExceptionFromSQLException(MysqlXAConnection.java:333) ~[mysql-connector-java-8.0.11.jar:8.0.11]
     * 当前访问mysql的账号root缺少系统权限，执行以下sql语句即可
     *
     * GRANT XA_RECOVER_ADMIN ON *.* TO root@'%' ;
     *
     */
    @GetMapping("/user")
    public String add(){
         return orderService.insertDbaAndDbB(true);
    }

}
