package com.chenning.common.reflex.demon1.service;

import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import com.chenning.common.reflex.demon1.AgentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 16:37
 **/
@Service
public class BaseService {
    private LinkedBlockingQueue<AgentEntity> queue = new LinkedBlockingQueue<AgentEntity>(4096);

    @Autowired
    UserService userService;

    public void x(User user) {
        List<User> allUser = userService.findAllUser();
        System.out.println(("用户ID" + user.getUserId() + "用户名字" + user.getUserName() + "所有用户数量") + allUser == null ? 0 : allUser.size());
    }

    public void doSomeThingByDance(User user) {
        System.out.println("DanceService===============>" + "参数用户" + user.getUserName());
    }


    public void doSomeThingBySing(String test, User user) {
        System.out.println("SingService==========>" + test + "参数用户" + user.getUserName());
    }



    public synchronized void add(AgentEntity entity) {
        try {
            queue.put(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void start() {
        new Thread(() -> {

            while (true) {
                try {
                    AgentEntity entity = queue.take();
                    entity.getMethod().invoke(entity.getObject(), entity.getArgs());
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
