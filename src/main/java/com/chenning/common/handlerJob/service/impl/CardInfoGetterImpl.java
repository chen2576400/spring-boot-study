package com.chenning.common.handlerJob.service.impl;

import com.chenning.common.crud.mapper.CardMapper;
import com.chenning.common.crud.model.Card;
import com.chenning.common.crud.model.User;
import com.chenning.common.handlerJob.DemonModel;
import com.chenning.common.handlerJob.service.CardInfoGetter;
import com.chenning.common.handlerJob.vo.CardVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 17:01
 **/
public class CardInfoGetterImpl implements CardInfoGetter {
    @Autowired
    private CardMapper mapper;
    @Override
    public void initCardInfo(DemonModel demonModel) {
        User user=demonModel.getUser();
        Card card = mapper.selectById(user.getUserId());
        CardVo vo=new CardVo();
        vo.setCard(card);
        vo.setCode("添加属性");
        demonModel.setCardVo(vo);
    }
}
