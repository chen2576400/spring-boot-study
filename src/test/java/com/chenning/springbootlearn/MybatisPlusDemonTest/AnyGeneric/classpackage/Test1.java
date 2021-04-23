//package com.chenning.springbootlearn.MybatisPlusDemonTest.AnyGeneric.classpackage;
//
//import com.chenning.springbootlearn.anyGeneric.classpackage.Worker;
//import org.junit.jupiter.api.Test;
//
///**
// * @Author nchen
// * @Date 2021/4/23 14:41
// * @Version 1.0
// * @Description
// */
//public class Test1 {
//
//    /**
//     * <T> T test1(Class<T> clazz)
//     */
//    @Test
//    public void test1() {
//        Demon demon = new Demon();
//        Worker a = demon.test1(B.class);//A是B的父类 所以也能接受
//        B b = demon.test1(B.class);
//        C c = demon.test1(C.class);
//        System.out.println(b);
//    }
//
//
//    /**
//     * <T extends  A> T test2(Class<T> clazz)
//     */
//    @Test
//    public void test2() {
//        Demon demon = new Demon();
//        Worker a = demon.test2(B.class);//A是B的父类 所以也能接受
//        B b = demon.test2(B.class);
////        C c = demon.test2(C.class);//由于这里限定了 必须是 <T extends  A> 所以编译提示错误
//        System.out.println(b);
//    }
//
//}
