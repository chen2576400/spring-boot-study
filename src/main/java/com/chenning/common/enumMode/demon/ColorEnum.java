//package com.chenning.common.enumMode.demon;
//
///**
// * @description:
// * @author: Mr.Nchen
// * @create: 2022-04-01 15:38
// *
// * 枚举类Color 反编译后的
// **/
//public final class ColorEnum extends Enum
//{
//
//    //返回存储枚举实例的数组的副本。values()方法通常用于foreach循环遍历枚举常量。
//    public static ColorEnum[] values()
//    {
//        return (ColorEnum[])$VALUES.clone();
//    }
//    //根据实例名获取实例
//    public static ColorEnum valueOf(String s)
//    {
//        return (ColorEnum)Enum.valueOf(ColorEnum, s);
//    }
//
//    //私有构造方法，这里调用了父类的构造方法，其中参数s对应了常量名，参数i代表枚举的一个顺序(这个顺序与枚举的声明顺序对应，用于oridinal()方法返回顺序值)
//    private ColorEnum(String s, int i)
//    {
//        super(s, i);
//    }
//
//    //我们定义的枚举在这里声明了三个 ColorEnum的常量对象引用,对象的实例化在static静态块中
//    public static final ColorEnum RED;
//    public static final ColorEnum BLUE;
//    public static final ColorEnum GREEN;
//    //将所有枚举的实例存放在数组中
//    private static final ColorEnum $VALUES[];
//
//    static
//    {
//        RED = new ColorEnum("RED", 0);
//        BLUE = new ColorEnum("BLUE", 1);
//        GREEN = new ColorEnum("GREEN", 2);
//        //将所有枚举的实例存放在数组中
//        $VALUES = (new ColorEnum[] {
//                RED, BLUE, GREEN
//        });
//    }
//}
