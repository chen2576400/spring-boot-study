package com.chenning.springbootlearn.transactional.transactionTemplate;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.transactional.FunctionCard;
import com.chenning.springbootlearn.transactional.FunctionUser;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @program: spring-boot-study
 * @description:
 * @author: nchen
 * @create: 2022-01-19 15:16
 **/
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
@Log4j2
public class CardTemplateService {
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private FunctionCard functionCard;

    @Autowired
    private FunctionUser functionUser;


    public String doSomething() {
        User user = functionUser.insertUser();
        System.out.println(1 / 0);
        functionCard.insertCard(user.getUserId());
        return "sucess";
    }


    /**
     * 设置回滚点(回滚方法不同,自动回滚的那种设置回滚点不会生效)
     */
    @Test
    public void test0() {
        String execute = transactionTemplate.execute(transactionStatus -> {
            //只回滚savePoint以下代码异常
            Object savepoint = transactionStatus.createSavepoint();
            User user = functionUser.insertUser();
            try {
                System.out.println(1 / 0);
                functionCard.insertCard(user.getUserId());
            } catch (Exception e) {
                transactionStatus.rollbackToSavepoint(savepoint);
            }
            return "sucess";
        });
        System.out.println(execute);
    }




    @Test
    public void test1() {
        String execute = transactionTemplate.execute(transactionStatus -> {
            User user = functionUser.insertUser();
            System.out.println(1 / 0);
            functionCard.insertCard(user.getUserId());
            return "sucess";
        });
        System.out.println(execute);
    }


    /**
     * 自动回滚（）
     */
    @Test
    public void test2() {
        String execute = transactionTemplate.execute(transactionStatus -> doSomething());
        System.out.println(execute);
    }


    /**
     * 手动回滚  lamada版
     */
    @Test
    public void test3() {
        String execute = transactionTemplate.execute((transactionStatus) -> {
            try {
                return doSomething();
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
                StackTraceElement stackTraceElement = e.getStackTrace()[0];
                log.error(String.format("方法%s的报错信息为%s,错误行数为%s", stackTraceElement.getMethodName(),e, stackTraceElement.getLineNumber()));
            }
            return "error";
        });
        System.out.println(execute);
    }

    /**
     * 手动回滚 实现类版
     */
    @Test
    public void test4() {
        transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                try {
                    return doSomething();
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    StackTraceElement stackTraceElement = e.getStackTrace()[0];
                    log.error("方法{}的报错信息为{},报错行数为{}", stackTraceElement.getMethodName(), e, stackTraceElement.getLineNumber());
//                    log.error(String.format("方法%s,错误行数为%s", e, stackTraceElement.getLineNumber()));
                }
                return "error";
            }
        });
    }
}
