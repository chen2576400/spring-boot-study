//package com.chenning.common.enumMode.demon;
//
//
//import java.util.Arrays;
//import java.util.List;
///**
// * @description:
// * @author: Mr.Nchen
// * @create: 2022-04-01 15:38
// *
// * 枚举类Weather 反编译后的
// **/
//public final class WeatherEnum extends Enum{
//
//    private WeatherEnum(String s, int i, int value, String label){
//        super(s, i);
//        this.value = value;
//        this.label = label;
//    }
//
//    public int getValue(){
//        return value;
//    }
//
//    public String getLabel(){
//        return label;
//    }
//
//    public static Weather parse(int value){
//        Weather result = Sunny;
//        switch(value){
//            case 1: // '\001'
//                result = Sunny;
//                break;
//            case 2: // '\002'
//                result = Rainy;
//                break;
//            case 3: // '\003'
//                result = Cloudy;
//                break;
//        }
//        return result;
//    }
//
//    public static List getEnumValues(){
//        return Arrays.asList(values());
//    }
//
//    public static void main(String args[]){
//        System.out.println((new StringBuilder(String.valueOf(Sunny.getValue()))).append(":").append(Sunny.getLabel()).toString());
//        Weather weather = Cloudy;
//        System.out.println((new StringBuilder(String.valueOf(weather.getValue()))).append(":").append(weather.getLabel()).toString());
//        List list = getEnumValues();
//        Weather sw;
//        for(Iterator iterator = list.iterator(); iterator.hasNext(); System.out.println((new StringBuilder(String.valueOf(sw.value))).append("--").append(sw.label).toString()))
//            sw = (Weather)iterator.next();
//    }
//
//	public static Weather[] values(){
//		Weather aweather[];
//		int i;
//		Weather aweather1[];
//		System.arraycopy(aweather = ENUM$VALUES, 0, aweather1 = new Weather[i = aweather.length], 0, i);
//		return aweather1;
//	}
//
//	public static Weather valueOf(String s){
//		return (Weather)Enum.valueOf(Weather, s);
//	}
//
//    public static final Weather Sunny;
//    public static final Weather Rainy;
//    public static final Weather Cloudy;
//    private int value;
//    private String label;
//    private static final Weather ENUM$VALUES[];
//    static {
//        Sunny = new Weather("Sunny", 0, 1, "\u6674\u5929");
//        Rainy = new Weather("Rainy", 1, 2, "\u96E8\u5929");
//        Cloudy = new Weather("Cloudy", 2, 3, "\u591A\u4E91");
//		ENUM$VALUES = (new Weather[] {
//			Sunny, Rainy, Cloudy
//		});
//    }
//
//}
