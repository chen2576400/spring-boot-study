package com.chenning.common.transactional;

import com.chenning.common.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Author nchen
 * @Date 2021/11/22 15:04
 * @Version 1.0
 * @Description
 */
@Service
public class FunctionTestService {
    @Autowired
    FunctionCard functionCard;

    @Autowired
    FunctionUser functionUser;


    /**
     * FunctionCard 没有加回滚事务注解
     * FunctionUser 没有加回滚事务注解
     * 调用俩方法testFunction1 增加事务回滚注解，数据库成功回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void testFunction1() {
        User user = functionUser.insertUser();
        System.out.println(1 / 0);
        functionCard.insertCard(user.getUserId());
        System.out.println("=========================>成功");
    }


    @Transactional(rollbackFor = Exception.class)
    public void testFunction2() {
        try {
            User user = functionUser.insertUser();
            System.out.println(1 / 0);
            functionCard.insertCard(user.getUserId());
            System.out.println("=========================>成功");
        } catch (Exception e) {
            System.out.println("执行错误");
        }
    }



    @Transactional(rollbackFor = Exception.class)
    public void testFunction3() {
        try {
            User user = functionUser.insertUser();
            System.out.println(1 / 0);
            functionCard.insertCard(user.getUserId());
            System.out.println("=========================>成功");
        } catch (Exception e) {
            System.out.println("执行错误");
            throw new RuntimeException();
        }
    }



    @Transactional(rollbackFor = Exception.class)
    public void testFunction4() {
        try {
            User user = functionUser.insertUser();
            System.out.println(1 / 0);
            functionCard.insertCard(user.getUserId());
            System.out.println("=========================>成功");
        } catch (Exception e) {
            System.out.println("执行错误");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }



    @Transactional(rollbackFor = Exception.class)
    public void testFunction5() {
        User user = functionUser.insertUser();


        //只回滚savePoint以下代码异常
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            System.out.println(1 / 0);
            functionCard.insertCard(user.getUserId());
            System.out.println("=========================>成功");
        } catch (Exception e) {
            System.out.println("执行错误");
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
        }
    }






}
