package com.chenning.common.lamda.innerClass;

import com.chenning.common.crud.model.User;
import org.junit.Test;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-11 14:20
 **/



/* Consumer<T>	T	void	用于接收一个对象进行处理但没有返回*/
public class ConsumerDemon {

    public void func1(Consumer<User> function,User user){
        function.accept(user);
    }
    public void func2(Consumer<User> function1,Consumer<User> function2,User user){
        function1.andThen(function2).accept(user);
    }
    //配合接口使用
    public void func3(Consumer<ILike> function1,ILike like){
        function1.accept(like);
    }

    @Test
    public  void  test1(){
        User user=new User();
        user.setUserName("张三");
        ConsumerDemon demon=new ConsumerDemon();
        demon.func1(user1 -> System.out.println(user1.getUserName()),user);
    }



    @Test
    public  void  test2(){
        User user=new User();
        user.setUserName("ABC");
        ConsumerDemon demon=new ConsumerDemon();
        demon.func2(user1 -> System.out.println(user1.getUserName().toUpperCase())
        ,user1 -> System.out.println(user1.getUserName().toLowerCase()),user);


    }


    @Test
    public  void  test3(){
        ConsumerDemon demon=new ConsumerDemon();
        demon.func3(like -> System.out.println(like.say(2)),sum -> (sum+1+""));
    }
}
