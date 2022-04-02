package com.chenning.common.enumMode.demon;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-01 15:43
 **/
public enum Weather {

    Sunny(1, "晴天"), Rainy(2, "雨天"), Cloudy(3, "多云");
    private int value;
    private String label;

    private Weather(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static Weather parse(int value) {
        Weather result = Weather.Sunny;
        switch (value) {
            case 1:
                result = Weather.Sunny;
                break;
            case 2:
                result = Weather.Rainy;
                break;
            case 3:
                result = Weather.Cloudy;
                break;
        }
        return result;
    }

    public static List<Weather> getEnumValues() {
        return Arrays.asList(values());
    }

    public static void main(String[] args) {
        System.out.println(Weather.Sunny.getValue() + ":" + Weather.Sunny.getLabel());
        Weather weather = Weather.Cloudy;
        System.out.println(weather.getValue() + ":" + weather.getLabel());
        List<Weather> list = getEnumValues();
        for (Weather sw : list) {
            System.out.println(sw.value + "--" + sw.label);
        }
    }
}
