package com.chenning.springbootlearn.MybatisPlusDemonTest.mail;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.mail.SendMailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/2/22 17:23
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class JavaMailSenderTest {
    @Autowired
    SendMailUtils mailUtils;

    @Test
    public void sendSimpleMail() throws Exception {
        mailUtils.simpleMailSend("2576400553@qq.com", "测试", "重汽精细化项目！！！");
    }

    @Test
    public void sendEnclosureMail() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("计划模板测试.docx", "D:\\计划模板测试.docx");
        map.put("新建文本文档.txt", "D:\\新建文本文档.txt");
        map.put("风险措施一览表.xlsx", "D:\\风险问题.xlsx");
        mailUtils.attachedSend("nchen@pisx.com", "Hello Attachment", "重汽测试邮件", map);
    }


    @Test
    public void sendMultiplayerMail() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("计划模板测试.docx", "D:\\计划模板测试.docx");
        map.put("新建文本文档.txt", "D:\\新建文本文档.txt");
        map.put("风险措施一览表.xlsx", "D:\\风险问题.xlsx");
        String[] filePath = map.values().toArray(new String[0]);
        String[] address = {"nchen@pisx.com", "2576400553@qq.com"};
        mailUtils.sendBatchMailWithFile(address, "Hello Attachment", "重汽测试邮件", filePath);
    }

}
