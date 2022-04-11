package com.chenning.common.lamda.innerClass;

import lombok.Data;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-11 16:38
 **/
@Data
public class Demon<T, U> {
    T t;
    U u;

    public Boolean predicate(Predicate<T> predicate1, Predicate<T> predicate2, Predicate<T> predicate3) {
        return predicate1.and(predicate2).and(predicate3).test(t);
    }


    public U function(Function<T, U> function) {
        return function.apply(t);
    }

    public String function(Function<T, U> function1, Function<U, String> function2) {
        return function1.andThen(function2).apply(t);
    }


    @Test
    public void test1() {
        Predicate<Integer> integerPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer o) {
                return o == 2;
            }
        };
        Predicate<Integer> integerPredicate1 = integer -> {
            return integer == 2;
        };

        boolean boolean1 = integerPredicate.test(3);
        boolean boolean2 = integerPredicate1.test(2);

        Demon<String, Integer> demon = new Demon<>();
        demon.setT("105");
        Boolean boolean3 = demon.predicate(s -> s.length() > 2, s -> s.endsWith("5"), s -> s.startsWith("1"));
        System.out.println(boolean1 + "#" + boolean2 + "#" + boolean3);
    }


    @Test
    public void test2() {

        Demon<ILike, String> demon = new Demon<>();


        Function<ILike, String> function = like -> {
            return like.say(1001);
        };
        String function1 = function.apply(sum -> sum.toString());


        demon.setT(sum -> {
            System.out.println("参数为sum");
            return sum.toString();
        });
        String function2 = demon.function(like -> like.say(199));


        String function3 = demon.function(like -> like.say(200), s -> {
            return s + "hellow";
        });


        System.out.println(function1 + "#" + function2+"#"+function3);


    }


}
