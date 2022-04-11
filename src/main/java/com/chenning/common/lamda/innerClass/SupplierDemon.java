package com.chenning.common.lamda.innerClass;

import com.chenning.common.crud.model.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-11 15:01
 * <p>
 * <p>
 * Supplier<T>
 * 参数：None
 * 返回类型 T
 * 作用：提供一个对象
 **/
public class SupplierDemon {

        public ILike func1(Supplier<ILike> supplier){
       return supplier.get();
    }


    @Test
    public  void test1(){
        SupplierDemon demon=new SupplierDemon();
        ILike like = demon.func1(() -> {
            return  sum -> String.valueOf(sum);
        });

        System.out.println(like.say(22));
    }
}
