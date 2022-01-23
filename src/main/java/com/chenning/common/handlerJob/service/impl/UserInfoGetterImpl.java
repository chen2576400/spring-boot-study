package com.chenning.common.handlerJob.service.impl;

import com.chenning.common.crud.mapper.UserMapper;
import com.chenning.common.crud.model.User;
import com.chenning.common.handlerJob.DemonModel;
import com.chenning.common.handlerJob.service.UserInfoGetter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 16:49
 **/
public class UserInfoGetterImpl implements UserInfoGetter {
    @Autowired
    private UserMapper mapper;
    @Override
    public void initUserInfo(DemonModel demonModel) {
        Integer id=demonModel.getId();
        User user = mapper.selectById(id);
        demonModel.setUser(user);
    }
}
