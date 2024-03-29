package com.chenning.common.crud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chenning.common.crud.mapper.CardMapper;
import com.chenning.common.crud.model.Card;
import com.chenning.common.crud.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-02-03
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {
    @Autowired
    CardMapper mapper;

    @Override
    public List<Card> findAllCard() {
        return mapper.selectList(null);
    }
}
