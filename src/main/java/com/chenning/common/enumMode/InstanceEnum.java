package com.chenning.common.enumMode;

import com.chenning.common.crud.service.CardService;
import com.chenning.common.quartz.config.SpringContextUtils;

/**
 * @description: 单例模式
 * @author: Mr.Nchen
 * @create: 2022-04-01 16:15
 **/
public enum InstanceEnum {

    INSTANCE;

    final CardService cardService;

    private InstanceEnum() {
        cardService = SpringContextUtils.getBean(CardService.class);
    }


    public CardService getInstance() {
        return cardService;
    }

}
