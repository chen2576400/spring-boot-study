package com.chenning.springbootlearn.mybatisPlus.guava;

import com.google.common.collect.*;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author nchen
 * @Date 2021/8/7 16:31
 * @Version 1.0
 * @Description
 */
public class GauavaDemon {

    /**
     * Gauava 创建集合
     */
    public void newCollect() {
        // 创建一个 ArrayList 集合
        List<String> list1 = Lists.newArrayList();
        // 创建一个 ArrayList 集合，同时塞入3个数据(并可以操作集合)
        List<String> list2 = Lists.newArrayList("a", "b", "c");
        // 创建一个 ArrayList 集合，容量初始化为10
        List<String> list3 = Lists.newArrayListWithCapacity(10);
        LinkedList<String> linkedList1 = Lists.newLinkedList();
        CopyOnWriteArrayList<String> cowArrayList = Lists.newCopyOnWriteArrayList();
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        ConcurrentMap<Object, Object> concurrentMap = Maps.newConcurrentMap();
        TreeMap<Comparable, Object> treeMap = Maps.newTreeMap();
        HashSet<Object> hashSet = Sets.newHashSet();
        HashSet<String> newHashSet = Sets.newHashSet("a", "a", "b", "c");
    }

    /**类似List，虽然叫set，但是他存放的元素是可以重复的，但是不保证顺序，
     * 而且还可以获取和设置某一个元素的出现次数
     * HashMultiset集合(可以存放重复的 当hashMultiset.elementSet()变为set时会去重)
     */
    public void hashMultiset() {
        ArrayList<String> arrayList = Lists.newArrayList("a", "b", "c", "d", "a", "c");
        HashMultiset<String> multiset = HashMultiset.create(arrayList);
    /*    for (String  key:multiset.elementSet()){
            System.out.println(key + ":" + multiset.count(key))   ;
        }*/
        //Set<String> strings = multiset.elementSet();
        multiset.elementSet().forEach(s -> System.out.println(s + ":" + multiset.count(s)));
        /**
         * result:
         * a:2
         * b:1
         * c:2
         * d:1
         */
///////////////////////////////////////////////////////////////////////////////
        HashMultiset<Integer> hashMultiset = HashMultiset.<Integer>create();
        hashMultiset.add(2);
        hashMultiset.add(2);
        hashMultiset.add(3);
        hashMultiset.add(4,5);//添加5个 值为4的元素
        hashMultiset.remove(4); //向集合中移出一个元素
        hashMultiset.remove(4,20);//向集合中移出若干个元素,如果元素的个数小于要移除的个数，则会把该元素移除光
        Set<Integer> set = hashMultiset.elementSet();//这里会去重
        set.forEach(integer -> {
            System.out.println(integer+"出现了"+hashMultiset.count(integer)+"次");
        });
///////////////////////////////////////////////////////////////////////////////

    }




    public void hashMultiMap(){
        //==============================[ListMultimap]===========================================
        ListMultimap<String, Integer> listMultimap = ArrayListMultimap.<String, Integer>create();
        listMultimap.put("1",1001);
        listMultimap.put("1",1002);
        listMultimap.put("1",1003); listMultimap.put("1",1003);
        listMultimap.put("2",1003);
        listMultimap.put("2",1004);
        //{1=[1001, 1002, 1003, 1003], 2=[1003, 1004]}
        List<Integer> integerList = listMultimap.get("key");
        //==============================[ListMultimap]===========================================




        //==============================[HashMultimap]===========================================
        //和SetMultimap 貌似一样
        HashMultimap<String, Integer> hashMultimap = HashMultimap.create();
        Set<Integer> integers = hashMultimap.get("key");
        //==============================[HashMultimap]===========================================



        //==============================[SetMultimap]===========================================
        //直接声明为SetMultimap(一个key可以多个value,但是一个相同key value如果也相同就会去重)
        SetMultimap<String, Integer> setMultimap=HashMultimap.create();
        setMultimap.put("1",1001);
        setMultimap.put("1",1002);
        setMultimap.put("1",1003); setMultimap.put("1",1003);
        setMultimap.put("2",1003);
        setMultimap.put("2",1004);
        //  {1=[1001, 1002, 1003], 2=[1004, 1003]}
        Set<Integer> set = setMultimap.get("1");
        //==============================[SetMultimap]===========================================



        //==============================[Multimap转map]===========================================
        Map<String, Collection<Integer>> collectionMap = HashMultimap.<String, Integer>create().asMap();
        //==============================[Multimap转map]===========================================



        //==============================[Multimap遍历]===========================================
        HashMultimap<String, Integer> multimap = HashMultimap.<String, Integer>create();
        multimap.put("1",1001);multimap.put("1",1002);multimap.put("1",1003);  multimap.put("2",1004);
        for (Map.Entry<String, Integer> entry : multimap.entries()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + value);
            /**
             * 1 1001
             * 1 1002
             * 1 1003
             * 2 1004
             */
        }
        //==============================[Multimap遍历]===========================================

    }




    public static void main(String[] args) {
        List<String> list2 = Lists.newArrayList("a", "b", "c");
        list2.remove(0);
        System.out.println(list2);
    }

}