package com.chenning.springbootlearn.demonBuild.service;

import com.baomidou.mybatisplus.service.IService;
import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.demonBuild.model.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2021-02-02
 */

public interface UserService extends IService<User> {
    List<User> findAllUser();

    List<UserVo>  findUserAndCardByID(Integer userID);
}
