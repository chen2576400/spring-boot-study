package com.chenning.common.design.design01;

/**
 * @Author nchen
 * @Date 2021/11/29 15:16
 * @Version 1.0
 * @Description 类A通过接口Interface1依赖类B，类C通过接口Interface1依赖类D，请编写代码完成此应用实例。
 * 没有使用接口隔离代码原则代码
 */
public class SingleResponsibility1 {



    public static void main(String[] args) {


    }

    //接口
    interface Interface1 {
        void operation1();
        void operation2();
        void operation3();
        void operation4();
        void operation5();
    }

    class B implements Interface1 {
        @Override
        public void operation1() {
            System.out.println("B 实现了 operation1");
        }
        @Override
        public void operation2() {
            System.out.println("B 实现了 operation2");
        }
        @Override
        public void operation3() {
            System.out.println("B 实现了 operation3");
        }
        @Override
        public void operation4() {
            System.out.println("B 实现了 operation4");
        }
        @Override
        public void operation5() {
            System.out.println("B 实现了 operation5");
        }
    }

    class D implements Interface1 {
        @Override
        public void operation1() {
            System.out.println("D 实现了 operation1");
        }
        @Override
        public void operation2() {
            System.out.println("D 实现了 operation2");
        }
        @Override
        public void operation3() {
            System.out.println("D 实现了 operation3");
        }
        @Override
        public void operation4() {
            System.out.println("D 实现了 operation4");
        }
        @Override
        public void operation5() {
            System.out.println("D 实现了 operation5");
        }
    }

    class A { //A 类通过接口Interface1 依赖(使用) B类，但是只会用到1,2,3方法
        public void depend1(Interface1 i) {
            i.operation1();
        }
        public void depend2(Interface1 i) {
            i.operation2();
        }
        public void depend3(Interface1 i) {
            i.operation3();
        }
    }

    class C { //C 类通过接口Interface1 依赖(使用) D类，但是只会用到1,4,5方法
        public void depend1(Interface1 i) {
            i.operation1();
        }
        public void depend4(Interface1 i) {
            i.operation4();
        }
        public void depend5(Interface1 i) {
            i.operation5();
        }
    }


}
