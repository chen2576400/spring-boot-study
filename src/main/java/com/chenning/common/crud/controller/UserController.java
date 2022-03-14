package com.chenning.common.crud.controller;


import com.chenning.common.crud.model.ParmVo;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.model.UserVo;
import com.chenning.common.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    public List<UserVo> findUserAndCardByID(Integer id,String s) {
        return userService.findUserAndCardByID(id);
    }

    /**
     * postMan 调用是  传的是json  这种不属于对象  不能id:1传参  直接写  1
     * @param id
     * @return
     */
    @RequestMapping("/findUserAndCardByID1")
    public List<UserVo> findUserAndCardByID1(@RequestBody Integer id) {
        return userService.findUserAndCardByID(id);
    }



    /**
     * postMan
     * @param
     * @return
     */
    @RequestMapping("/findUserAndCardByID2")
    public List<UserVo> findUserAndCardByID2(@RequestBody ParmVo parmVo) {
        return userService.findUserAndCardByID(parmVo.getId());
    }


}
