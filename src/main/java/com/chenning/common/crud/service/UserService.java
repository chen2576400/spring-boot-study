package com.chenning.common.crud.service;

import com.baomidou.mybatisplus.service.IService;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.model.UserVo;

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

    User  findUserByNameAndPassword(String userName,String password);
}
