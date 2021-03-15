package com.chenning.springbootlearn.demonBuild.service;

import com.baomidou.mybatisplus.service.IService;
import com.chenning.springbootlearn.demonBuild.model.User;

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
}
