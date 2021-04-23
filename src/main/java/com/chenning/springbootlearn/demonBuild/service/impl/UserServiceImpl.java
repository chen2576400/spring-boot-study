package com.chenning.springbootlearn.demonBuild.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chenning.springbootlearn.demonBuild.mapper.UserMapper;
import com.chenning.springbootlearn.demonBuild.model.User;
import com.chenning.springbootlearn.demonBuild.model.UserVo;
import com.chenning.springbootlearn.demonBuild.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public List<UserVo> findUserAndCardByID(Integer userID) {
        return userMapper.findUserAndCardByID(userID);
    }
}
