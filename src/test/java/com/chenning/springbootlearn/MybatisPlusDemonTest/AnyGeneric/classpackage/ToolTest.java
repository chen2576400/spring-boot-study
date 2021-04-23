package com.chenning.springbootlearn.MybatisPlusDemonTest.AnyGeneric.classpackage;

import com.chenning.springbootlearn.anyGeneric.classpackage.Student;
import com.chenning.springbootlearn.anyGeneric.classpackage.Tool;
import com.chenning.springbootlearn.anyGeneric.classpackage.Worker;
import org.junit.Test;

/**
 * @Author nchen
 * @Date 2021/4/23 15:40
 * @Version 1.0
 * @Description
 */
public class ToolTest {

    @Test
    public void x() {
        Worker t1 = new Worker("张三", "男");
        Student w1 = new Student("李四", 20);

        //现在我们使用Tool类来调用t1和w1
        Tool<Worker> workerTool = new Tool<Worker>(t1);

        Tool<Student> studentTool = new Tool<Student>(w1);

        //打印查看效果
        System.out.println("使用ts调用t1中的数据：" + workerTool.getE().getName() + ":" + workerTool.getE().getSex());
        System.out.println("使用tw调用w1中的数据：" + studentTool.getE().getName() + ":" + workerTool.getE().getSex());

    }
}
