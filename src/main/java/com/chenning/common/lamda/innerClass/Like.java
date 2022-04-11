package com.chenning.common.lamda.innerClass;

import org.junit.Test;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-14 16:09
 **/
public class Like implements ILike {
    @Override
    public String say(Integer sum) {
        String s = String.format("我是接口的默认实现类方法,参数是%s", sum);
        System.out.println(s);
        return s;
    }


    @Test
    public void test1() {
        ILike iLike1 = new ILike() {
            @Override
            public String say(Integer sum) {
                String s = String.format("我是接口的自定义实现方法,参数是%s", sum);
                System.out.println(s);
                return s;
            }
        };
        String say = iLike1.say(5);//执行这里才会走实现类方法
        System.out.println("执行完毕**********" + say);
    }


    /**
     * 这个会将ILike的实现类Like 的方法重写
     */
    @Test
    public void test2() {
        ILike iLike1 = new Like() {
            @Override
            public String say(Integer sum) {
                String s = String.format("我是接口的自定义实现方法,参数是%s", sum);
                System.out.println(s);
                return s;
            }
        };
        String say = iLike1.say(6);
        System.out.println("执行完毕**********" + say);
    }


    @Test
    public void test3() {
        ILike iLike1 = new Like();
        String say = iLike1.say(7);
        System.out.println("执行完毕**********" + say);
    }


    /**
     * lamada写法  满足条件
     * 1：lamada只能有一行代码情况下才能简化为一行，如果有多行，请用代码块包裹
     * 2：前提是接口为函数式接口（有且只有一个抽象方法的接口）
     * 3：多个参数也可以去掉参数类型，要去掉都去掉，必须加上括号
     * <p>
     * 只有在接口只有一个抽象方法时候，箭头函数默认实现匿名内部类
     */
    @Test
    public void test4() {

        //1 lamda表达式简化
        ILike iLike1 = (Integer a) -> String.format("我是接口的自定义实现类方法iLike1,参数是%s", a);
        //2 lamda简化参数类型
        ILike iLike2 = (b) -> String.format("我是接口的自定义实现类方法iLike2,参数是%s", b);
        //3 lamada简化括号
        ILike iLike3 = sum -> String.format("我是接口的自定义实现类方法iLike4,参数是%s", sum);

        String say1 = iLike1.say(7);
        String say2 = iLike2.say(8);
        String say3 = iLike3.say(9);
        System.out.println("执行完毕**********" + say1);
        System.out.println("执行完毕**********" + say2);
        System.out.println("执行完毕**********" + say3);
    }


    @Test
    public void test5() {
        todo1(sum -> String.format("我是接口函数todo1,参数是%s", sum));
    }
    private static void todo1(ILike like) {
        String say = like.say(0716);
        System.out.println(say);
    }







}
