package com.chenning.springbootlearn.lamda;

import com.chenning.springbootlearn.crud.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author nchen
 * @Date 2021/9/28 14:59
 * @Version 1.0
 * @Description
 */
public class OptionalDemon {

    {
        /**
         *                  [of]    return -> OPTIONAL<T>
         *  创建一个值为张三的String类型的Optional
         * Optional<String> ofOptional = Optional.of("张三");
         * 如果我们用of方法创建Optional对象时，所传入的值为null，则抛出NullPointerException如下图所示
         * Optional<String> nullOptional = Optional.of(null);
         *
         *                [ofNullable]  return -> OPTIONAL<T>
         * //为指定的值创建Optional对象，不管所传入的值为null不为null，创建的时候都不会报错
         * Optional<String> nullOptional = Optional.ofNullable(null);
         * Optional<String> nullOptional = Optional.ofNullable("lisi");
         *
         *               [empty]     return -> OPTIONAL<T>
         * //创建一个空的String类型的Optional对象
         * Optional<String> emptyOptional = Optional.empty();
         *
         *
         *               [get] return -> T     OPTIONAL<T>
         * 如果我们创建的Optional对象中有值存在则返回此值，如果没有值存在，则会抛出NoSuchElementException异常。
         * Optional<String> stringOptional = Optional.of("张三");
         * System.out.println(stringOptional.get());
         *
         *
         *                [orElse] return -> T     OPTIONAL<T>
         * 如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
         * 值的一提 如果orElse(function) Optional里面不论是否存在值都会执行function(function报错会影响程序)
         * Optional<String> stringOptional = Optional.of("张三");
         * System.out.println(stringOptional.orElse("zhangsan"));  //consol:张三
         * Optional<String> emptyOptional = Optional.empty();
         * System.out.println(emptyOptional.orElse("李四"));    //consol:李四
         *
         *
         *                [orElseGet]   return -> T     OPTIONAL<T>
         *如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
         *值的一提 如果orElseGet(function)  Optional里面只有不存在值的时候才会调用function
         *
         *
         *                 [orElseThrow]  return -> T     OPTIONAL<T>
         *如果创建的Optional中有值存在，则返回此值，否则抛出一个由指定的Supplier接口生成的异常
         * optionalUser.orElseThrow(Exception::new)
         *
         *
         *                       [filter]  return -> OPTIONAL<T>
         * 如果创建的Optional中的值存在，对该值执行提供的Function函数调用
         * Optional<String> stringOptional = Optional.of("zhangsan");
         * System.out.println(stringOptional.filter(e -> e.length() > 5).orElse("王五"));
         * stringOptional = Optional.empty();
         * System.out.println(stringOptional.filter(e -> e.length() > 5).orElse("lisi"));
         *
         *
         *                       [map]  return -> OPTIONAL<T>
         * map方法执行传入的lambda表达式参数对Optional实例的值进行修改,修改后的返回值仍然是一个Optional对象
         * Optional<String> stringOptional = Optional.of("zhangsan");
         * System.out.println(stringOptional.map(e -> e.toUpperCase()).orElse("失败"));
         *
         * stringOptional = Optional.empty();
         * System.out.println(stringOptional.map(e -> e.toUpperCase()).orElse("失败"));
         *
         *
         *                   [isPresent]  return ->boolean
         * 对 Optional 实例进行判断，是否包含值，如果存在值，就返回 true，否则返回 false
         * Optional<String> empty = Optional.<String>empty();
         *  empty.isPresent();  false
         *
         *
         *
         *                  [ifPresent]  return ->   void
         * Optional.ifPresent(function)
         * 如果对象不是空的，就对执行传入的 Lambda 表达式 ,否则不执行
         *
         */

    }


    public User getNullUser() {
        return null;
    }

    public User getBeanUser() {
        return new User();
    }

    public User getUser() {
        User user = new User();
        user.setUserId(1001);
        user.setUserName("张三");
        return user;
    }


    /**
     * Null对象
     */
    @Test
    public void test1() {
        User user = getNullUser();//当前为空Null

        Integer integer = Optional.ofNullable(user)
                .map(user1 -> user1.getUserId())
                .orElse(1024);
        System.out.println("consol_" + integer); //consol_1024


        Integer integer1 = Optional.ofNullable(user)
                .filter(user1 -> user1.getUserId() > 1000)
                .map(user1 -> user1.getUserId())
                .orElse(7777);
        System.out.println("consol_" + integer1);  //consol_7777

    }

    /**
     * 属性列字段都为Null的bean对象
     *
     * .filter(属性)  属性为空时候会报空指针    为空时候Optional.filter(属性为空)   直接报空指针     【尽量把filter放到最后面 先用map】
     * .map(属性)     属性空不会导致空指针     为空时候Optional.map(属性为空)   返回的是empty Optional  [map里面属性是什么返回的类型就是什么]
     */
    @Test
    public void test2() {
        User user = getBeanUser();//当前为空Null

        Integer integer = Optional.ofNullable(user)
                .map(user1 -> user1.getUserId())
                .orElse(1024);
        System.out.println("consol_" + integer); //consol_1024

/*   错误示例
        如果对象属性列为空   .filter(对象属性为空)   会直接报错
        Integer integer1 = Optional.ofNullable(user)
                .filter(user1 -> user1.getUserId() > 1000)
                .map(user1 -> user1.getUserId())
                .orElse(7777);
        System.out.println("consol_" + integer1);  // NullPointerException
        */

        Integer integer1 = Optional.ofNullable(user)
                .map(user1 -> user1.getUserId())
                .filter(integer2 -> integer2 > 1000)
                .orElse(7777);
        System.out.println("consol_" + integer1);  //consol_7777*/



        Optional.ofNullable(user)
                .map(user1 -> user1.getUserId()).ifPresent(integer2 -> System.out.println(integer2));

    }


    /**
     * 有属性的user对象
     */
    @Test
    public void test3() {
        User user = getUser();//有属性的user对象

        Integer integer = Optional.ofNullable(user)
                .map(user1 -> user1.getUserId())
                .orElse(1024);
        System.out.println("consol_" + integer); //consol_1001


        {
            Integer integer1 = Optional.ofNullable(user)
                    .filter(user1 -> user1.getUserId() > 1000)  //因为此对象这个属性不为空 所以 filter不会出错 但是推荐先map 最后 filter
                    .map(user1 -> user1.getUserId())
                    .orElse(7777);
            System.out.println("consol_" + integer1); //consol_1001



            Integer integer2 = Optional.ofNullable(user)
                    .map(user1 -> user1.getUserId())
                    .filter(integer0 -> integer0 > 1000)
                    .orElse(7777);
            System.out.println("consol_" + integer2);  //consol_1001
        }

    }




    @Test
    public void test4() {



        User user1=new User();
        user1.setUserName("111");



        User user2=new User();

        User user3=new User();
        user3.setUserName("333");
        List<User> userList=new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        List<String> collect = userList.stream().map(user -> {
            return Optional.ofNullable(user).map(user4 -> user4.getUserName()).orElse("");
        }).collect(Collectors.toList());
        //System.out.println(collect.contains(333));
        //System.out.println(collect.contains("333"));
        //System.out.println(collect.contains(" "));
        //System.out.println(collect.contains("111"));
        boolean b = userList.stream().allMatch(user -> "111".equals(user.getUserName()));
        System.out.println(b);
    }


}
