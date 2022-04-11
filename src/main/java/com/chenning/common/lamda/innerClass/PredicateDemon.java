package com.chenning.common.lamda.innerClass;

import com.chenning.common.crud.model.User;
import org.junit.Test;

import java.util.function.Predicate;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-11 13:55
 *
 *  作用：对某种数据类型的数据进行判断，结果返回一个boolean
 **/


/*Predicate<T>	T	boolean	用于判别一个对象*/
public class PredicateDemon {

    //    test方法主要用于参数符不符合规则。返回值 boolean
     public  Boolean  func1(Predicate<User> predicate,User user){
         return predicate.test(user);
     }


    //    and 方法等同于我们的逻辑与&&,存在短路特性,需要所有条件都满足
    public  Boolean  func2(Predicate<User> predicate1,Predicate<User> predicate2,User user){
        return predicate1.and(predicate2).test(user);
    }



    //    or 等同于我们的逻辑或 || ，多个条件只要一个满足即可
    public  Boolean  func3(Predicate<User> predicate1,Predicate<User> predicate2,User user){
        return predicate1.or(predicate2).test(user);
    }

    //配合接口使用
    public  Boolean  func4(Predicate<ILike> predicate,ILike like){
        return predicate.test(like);
    }


     @Test
    public void  test1(){
         User user=new User();
         user.setUserName("张三");
         PredicateDemon demon = new PredicateDemon();
         Boolean func1 = demon.func1(user1 -> {
             return "张三".equals(user1.getUserName());
         }, user);
         System.out.println(func1);
     }

    @Test
    public void  test2(){
        User user=new User();
        user.setUserName("张三");
        user.setUserId(11);
        PredicateDemon demon = new PredicateDemon();
        Boolean func2 = demon.func2(user1 -> user1.getUserId() == null, user1 -> user1.getUserName() != null, user);
        System.out.println(func2);
    }

    @Test
    public void  test3(){
        User user=new User();
        user.setUserName("张三");
        user.setUserId(11);
        PredicateDemon demon = new PredicateDemon();
        Boolean func3 = demon.func3(user1 -> user1.getUserId() == null, user1 -> user1.getUserName() != null, user);
        System.out.println(func3);
    }


    @Test
    public void  test4(){
        PredicateDemon demon = new PredicateDemon();
        Boolean aBoolean = demon.func4(like -> like.say(78910).length() > 4, sum -> sum.toString());
        System.out.println(aBoolean);

    }
}
