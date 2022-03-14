package com.chenning.common.design.design02;

/**
 * @Author nchen
 * @Date 2021/11/29 16:43
 * @Version 1.0
 * @Description
 */
public class DependecyInversion02 {
    public static void main(String[] args) {
        //客户端无需改变
        Person02 person = new Person02();
        person.receive(new Email02());

        person.receive(new WeiXin());
    }

}

//定义接口
interface IReceiver {
    public String getInfo();
}

class Email02 implements IReceiver {
    @Override
    public String getInfo() {
        return "电子邮件信息: hello,world";
    }
}

//增加微信
class WeiXin implements IReceiver {
    @Override
    public String getInfo() {
        return "微信信息: hello,ok";
    }
}

//方式2
class Person02 {
    //这里我们是对接口的依赖
    public void receive(IReceiver receiver ) {
        System.out.println(receiver.getInfo());
    }

}
