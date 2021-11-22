package com.chenning.springbootlearn.transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author nchen
 * @Date 2021/11/22 14:26
 * @Version 1.0
 * @Description  事务回滚调用说明
 * 1：数据库引擎不支持事务： MyISAM 引擎是不支持事务操作的，InnoDB 才是支持事务的引擎，一般要支持事务都会使用 InnoDB。
 * 2：如果这个类就不会被 Spring 管理了，事务也不会生效
 * 3：@Transactional 只能用于 public 的方法上，否则事务不会失效，如果要用在非 public 方法上，可以开启 AspectJ 代理模式。
 * 4：自调用问题：一个class里面包含A和B两个方法   A（@Transactional）  B不加@Transactional    B方法调用A    最终事务都不会生效
 * 因为它们发生了自身调用，就调该类自己的方法，而没有经过 Spring 的代理类，默认只有在外部调用事务才会生效
 * 解决办法（1：把这两个方法分开到不同的类中； 2：把@Transactional 加到调用方法上面）
 *
 */
@RestController
@RequestMapping("transactional")
public class FunctionTestController {
    @Autowired
    FunctionTestService testService;


    @Autowired
    FunctionOwnClass  ownClass;


    /**
     *（回滚成功）
     * FunctionCard 没有加回滚事务注解
     * FunctionUser 没有加回滚事务注解
     * 调用俩方法testFunction1 增加事务回滚注解，数据库成功回滚
     * 默认Transactional 只包含RuntimeException(运行异常),想要囊括所有请指定 rollbackFor.class
     */
    @RequestMapping("testFunction1")
    @Transactional
    public void testFunction1() {
        testService.testFunction1();
    }



    /**
     * （回滚失败）
     * try catch捕捉异常不处理
     * 调用俩方法testFunction2 增加事务回滚注解，并且捕捉异常    数据库回滚失败User表插入成功
     */
    @RequestMapping("testFunction2")
    public void testFunction2() {
        testService.testFunction2();
    }



    /**
     * （回滚成功）
     * try catch捕捉异常并且在catch 抛出异常
     * 调用俩方法testFunction3 增加事务回滚注解，数据库成功回滚
     */
    @RequestMapping("testFunction3")
    public void testFunction3() {
        testService.testFunction3();
    }



    /**
     * （回滚成功）
     * try catch捕捉异常并且在catch 里手动回滚事务
     */
    @RequestMapping("testFunction4")
    public void testFunction4() {
        testService.testFunction4();
    }



    /**
     * (只回滚指定部分)
     *指定事务点  事务点之后的才会回滚，之前的正常插入
     *
     */
    @RequestMapping("testFunction5")
    public void testFunction5() {
        testService.testFunction5();
    }




    /**
     * (回滚失败)
     * 一个类里面多个方法A和B自调用 A（@Transactional）  B不加@Transactional  B调用A
     * B不加@Transactional 事务失败    加上@Transactional事务成功（或者写到不同class调用）
     */
    @RequestMapping("testFunction6")
    public void testFunction6() {
        ownClass.insertCard();
    }






    /**
     * (回滚成功)
     * 一个类里面多个方法A和B自调用 A（@Transactional）  B不加@Transactional  B调用A
     *
     */
    @RequestMapping("testFunction7")
    public void testFunction7() {
        ownClass.insertCardTransactional();
    }





    /**
     *  (回滚成功)
     * 某个方法不加@Transactional 调用其他service事务管理的方法  事务会生效
     *
     */
    @RequestMapping("testFunction8")
    public void testFunction8() {
        ownClass.testDo1();
    }

}
