package com.chenning.common.enumMode;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-01 15:25
 *
 * 详情去看demon包下面的源码反编译   是如何将自定义参数与枚举实例值绑定的
 **/
public class EnumTest {
    public static void main(String[] args) {
        test2();

    }


    /**
     *   enum.values 自带方法 返回所有枚举实例
     *   enum.name()  返回枚举常量名称
     */
    public static void test1() {
        HandWorkEnum[] values = HandWorkEnum.values();
        for (HandWorkEnum value : values) {
            System.out.println("枚举常量的名称:" + value.name() + "枚举常量对应的值" + value.description);
        }
    }

    public static void test2() {
        String s = HandWorkEnum.getdescription001(HandWorkEnum.TREATMENTSTATUSE4);
        System.out.println(s);
        s = HandWorkEnum.getdescription(91);
        System.out.println(s);
    }


}
