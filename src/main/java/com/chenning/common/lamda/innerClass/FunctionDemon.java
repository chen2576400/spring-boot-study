package com.chenning.common.lamda.innerClass;

import com.chenning.common.crud.model.User;
import org.junit.Test;

import java.util.function.Function;

/**
 * @description: lamada函数编程
 * @author: Mr.Nchen
 * @create: 2022-04-11 09:45
 * <p>
 * <p>
 * 接口	       参数	返回类型	描述
 * Predicate<T>	T	boolean	用于判别一个对象
 * Consumer<T>	T	void	用于接收一个对象进行处理但没有返回
 * Function<T,R>	T	R	转换一个对象为不同类型的对象
 * Supplier<T>	None	T	提供一个对象
 **/



/*Function<T,R>	T	R	转换一个对象为不同类型的对象*/
public class FunctionDemon {
    // 将给定的参数应用到这个函数上,传入的参数类型为T返回类型为R
    public String func1(Function<User, String> function, User user) {
        return function.apply(user);
    }


    // 返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
    public String func2(Function<User, String> function1, Function<String, User> function2, String s) {
        //首先将 字符串参数s   应用到function2中，再将function2的函数结果应用到function1中
        return function1.compose(function2).apply(s);
    }


    // 返回一个组合函数，该函数结果应用到after函数中
    public String func3(Function<User, Integer> function1, Function<Integer, String> function2, User user) {
        //首先 将user参数应用到  function1中, 再将function1的函数结果应用到 function2中
        return function1.andThen(function2).apply(user);
    }


    //配合接口使用
    public String func4(Function<ILike, String> function, ILike like) {
        return function.apply(like);
    }


    @Test
    public void test1() {
        FunctionDemon integerStringFunctionDemon = new FunctionDemon();
        User user = new User();
        user.setUserName("张三");
        String func = integerStringFunctionDemon.func1(User::getUserName, user);
        System.out.println(func);
    }


    @Test
    public void test2() {
        FunctionDemon integerStringFunctionDemon = new FunctionDemon();
        String func = integerStringFunctionDemon.func2(user1 -> user1.getUserName(), s -> {
            User user = new User();
            user.setUserName(s);
            return user;
        }, "李四");
        System.out.println(func);
    }


    @Test
    public void test3() {
        User user = new User();
        user.setUserId(1234);
        FunctionDemon integerStringFunctionDemon = new FunctionDemon();
        String func3 = integerStringFunctionDemon.func3(User::getUserId, integer -> integer.toString(), user);
        System.out.println(func3);
    }



    @Test
    public void test4() {
        FunctionDemon integerStringFunctionDemon = new FunctionDemon();
        String s = integerStringFunctionDemon.func4(like -> like.say(7), sum -> String.format("我是接口的自定义实现类方法iLike4,参数是%s", sum));
        System.out.println(s);
    }
}
