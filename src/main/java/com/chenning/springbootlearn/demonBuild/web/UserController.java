package com.chenning.springbootlearn.demonBuild.web;


import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.demonBuild.model.UserVo;
import com.chenning.springbootlearn.demonBuild.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chen
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher applicationListener;

    @RequestMapping("/findAllUser")
    public List<User> userList() {
        return userService.findAllUser();
    }

    @RequestMapping("/findAllUserByID")
    public List<UserVo> findUserAndCardByID(Integer id) {
        return userService.findUserAndCardByID(id);
    }

    @RequestMapping("/findUserAndCardByID1")
    public List<UserVo> findUserAndCardByID1(@RequestBody Integer id) {
        return userService.findUserAndCardByID(id);
    }
}
