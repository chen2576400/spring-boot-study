package com.chenning.springbootlearn.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/8/11 16:39
 * @Version 1.0
 * @Description
 */
public class GauavaStringUntil {



    public void joiner() {
//==================================================================================                //1.用指定的字符连接  //输出 a#b#c
        String join  = Joiner.on("#")
                .join(Lists.newArrayList("a", "b", "c"));
        //String str1 = Joiner.on("#").join(new String[]{"a","b","c"});
        System.out.println("用 # 号连接的字符串是："+join); //a,b,c

//==================================================================================
       //2.跳过连接中的null值  //输出 a#b#c
        String joinNull = Joiner.on("#").skipNulls()
                .join(Lists.newArrayList("a", "b", "c", null));
        System.out.println("用 # 号连接的字符串,跳过中间的null值："+joinNull);

//==================================================================================
        //3.有null值存在时用指定值代替   //输出 a#b#c#DEFAULT
        String joinUseForNull = Joiner.on("#").useForNull("DEFAULT")
                .join("a", "b", "c", null);
        System.out.println("用指定值代替集合中的null值："+joinUseForNull);

//==================================================================================
        //4.把集合变成一个stringBuilder 或者stringBuffer //输出 a#b#c#DEFAULT
        StringBuilder builder = Joiner.on("#").useForNull("DEFAULT").appendTo(new StringBuilder(), Lists.newArrayList("a", "b", "c", null));
        //StringBuffer buffer = Joiner.on("#").useForNull("DEFAULT").appendTo(new StringBuffer(), Lists.newArrayList("a", "b", "c", null));
        System.out.println("拼接成的 builder是："+builder);

//==================================================================================
        //5.对于key value的分隔   //输出 study=1003#scala=1002#hello=1001
        Map<String, Integer> map = Maps.newHashMap();
        map.put("hello", 1001);
        map.put("scala", 1002);
        map.put("study", 1003);
        String joinMap = Joiner.on("#").withKeyValueSeparator("=").join(map);
        System.out.println("key value 的分隔符："+joinMap);
//==================================================================================
    }



    public void splitter() {
//==================================================================================
// 1.用指定字符切分字符串,并转换成list
        List<String> list = Splitter.on(",").splitToList("a,b,c,d");
        System.out.println("list:" + list); //输出 [a, b, c, d]
//==================================================================================
// 去掉字符串中的空格，再进行过滤空元素
        List<String> list1 = Splitter.on(",")
                .omitEmptyStrings()//去空字符
                .trimResults()//其空格
                .splitToList("a,b,, ,c,d");
        System.out.println("list1:" + list1); //输出 [a, b, c, d]
//==================================================================================
        //limit表示最多把字符串分隔成多少份   //输出 [a, b, c#d]
        List<String> list2 = Splitter.on("#").omitEmptyStrings().trimResults().limit(3).splitToList("a#b#c#d");
        System.out.println("list2:" + list2);
//==================================================================================
        //将字串还原成map，是Joiner的逆向操作，注意：字符串的格式必须满足“a:1#b:2”这种格式，格式不对会导致还原map失败
        // 输出 {study=1003, scala=1002, hello=1001}
        Map<String,String> map = Splitter.on("#").omitEmptyStrings().trimResults()
                .withKeyValueSeparator("=").split("study=1003#scala=1002#hello=1001");
        System.out.println("map:" + map);
//==================================================================================

    }
}
