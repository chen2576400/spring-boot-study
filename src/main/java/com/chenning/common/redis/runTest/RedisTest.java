package com.chenning.common.redis.runTest;

import com.alibaba.fastjson.JSON;
import com.chenning.common.SpringBootLearnApplication;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import com.chenning.common.redis.RedisConstant;
import com.chenning.common.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: Mr.Chen
 * @create: 2021-02-02 17:30
 **/
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;


    @Test
    public void findAllUser() {
        List<User> userList=null;
        String key= RedisConstant.REDIS_CACHENAME_USER;
        if (redisService.exists(key)){
            userList=redisService.get(key);
            if (CollectionUtils.isEmpty(userList)){
                redisService.set(key, userList, 24 * 60 * 60);
            }
        }else {
            userList= userService.findAllUser();
            redisService.set(key, userList, 24 * 60 * 60);
        }
        System.out.println(JSON.toJSONString(userList));
    }

}
