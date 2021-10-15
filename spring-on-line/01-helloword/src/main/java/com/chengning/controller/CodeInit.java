package com.chengning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author nchen
 * @Date 2021/10/15 10:25
 * @Version 1.0
 * @Description  没交给容器只有在new 对象时候才会调用
 */
public class CodeInit {
    @Autowired
    private BeanDao beanDao;

////静态代码块>构造代码块>构造函数>普通代码块　
    static {
        //静态代码块在类被加载的时候就运行了，而且只运行一次，并且优先于各种代码块以及构造函数。如果一个类中有多个静态代码块，会按照书写顺序依次执行
        System.out.println("=======================================CodeInit静态代码块儿");
    }


    {   //构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行
        System.out.println("=======================================CodeInit构造代码块");
    }


    public CodeInit(){
        System.out.println("=======================================CodeInit构造函数");
    }


    public void todo(){
        beanDao.todo();
    }



/*   反编译后的代码
public class CodeInit {
        @Autowired
        private BeanDao beanDao;

        public CodeInit() {
            System.out.println("=======================================CodeInit构造代码块");
            System.out.println("=======================================CodeInit构造函数");
        }

        public void todo() {
            this.beanDao.todo();
        }

        static {
            System.out.println("=======================================CodeInit静态代码块儿");
        }
    }*/

}
