package com.chenning.common.excel.pdf;

import com.chenning.common.crud.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 生成pdf文件
 * @author: Mr.Nchen
 * @create: 2022-04-02 10:04
 **/
public class ExportPdf {
    static {
        List<User> users = new ArrayList();
        for (int i = 1001; i < 1006; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("用户" + i);
            user.setAddress("住址号" + i);
            user.setPassword("用户密码" + i);
            users.add(user);
        }
    }

}
