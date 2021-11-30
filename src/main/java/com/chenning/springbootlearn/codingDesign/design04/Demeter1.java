package com.chenning.springbootlearn.codingDesign.design04;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author nchen
 * @Date 2021/11/30 10:18
 * @Version 1.0
 * @Description
 * 1) 有一个学校，下属有各个学院和总部，现要求打印出学
 * 校总部员工ID和学院员工的id
 */
public class Demeter1 {

    public static void main(String[] args) {
        //创建了一个 SchoolManager 对象
        SchoolManager schoolManager = new SchoolManager();
        //输出学院的员工id 和  学校总部的员工信息
        schoolManager.printAllEmployee(new CollegeManager1());

    }

}


//学校总部员工类
class Employee1 {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}


//学院的员工类
class CollegeEmployee1 {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}


//管理学院员工的管理类
class CollegeManager1 {
    //返回学院的所有员工
    public List<CollegeEmployee1> getAllEmployee() {
        List<CollegeEmployee1> list = new ArrayList<CollegeEmployee1>();
        for (int i = 0; i < 10; i++) { //这里我们增加了10个员工到 list
            CollegeEmployee1 emp = new CollegeEmployee1();
            emp.setId("学院员工id= " + i);
            list.add(emp);
        }
        return list;
    }
}

//学校管理类

//分析 SchoolManager 类的直接朋友类有哪些 Employee、CollegeManager
//CollegeEmployee 不是 直接朋友 而是一个陌生类，这样违背了 迪米特法则
class SchoolManager {
    //返回学校总部的员工
    public List<Employee1> getAllEmployee() {
        List<Employee1> list = new ArrayList<Employee1>();

        for (int i = 0; i < 5; i++) { //这里我们增加了5个员工到 list
            Employee1 emp = new Employee1();
            emp.setId("学校总部员工id= " + i);
            list.add(emp);
        }
        return list;
    }

    //该方法完成输出学校总部和学院员工信息(id)
    void printAllEmployee(CollegeManager1 sub) {

        //分析问题
        //1. 这里的 CollegeEmployee 不是  SchoolManager的直接朋友
        //2. CollegeEmployee 是以局部变量方式出现在 SchoolManager
        //3. 违反了 迪米特法则

        //获取到学院员工
        List<CollegeEmployee1> list1 = sub.getAllEmployee();
        System.out.println("------------学院员工------------");
        for (CollegeEmployee1 e : list1) {
            System.out.println(e.getId());
        }
        //获取到学校总部员工
        List<Employee1> list2 = this.getAllEmployee();
        System.out.println("------------学校总部员工------------");
        for (Employee1 e : list2) {
            System.out.println(e.getId());
        }
    }

}
