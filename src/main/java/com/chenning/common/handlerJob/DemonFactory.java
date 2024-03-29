package com.chenning.common.handlerJob;

import com.chenning.common.handlerJob.service.CardInfoGetter;
import com.chenning.common.handlerJob.service.UserInfoGetter;
import com.chenning.common.handlerJob.service.impl.CardInfoGetterImpl;
import com.chenning.common.handlerJob.service.impl.UserInfoGetterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 16:45
 **/
public class DemonFactory {
    private DemonModel demonModel;
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    public DemonFactory(DemonModel demonModel) {
        this.demonModel = demonModel;
    }

    public UserInfoGetter getUserInfoGetter() {
        UserInfoGetter userInfoGetter = new UserInfoGetterImpl();
        autowireCapableBeanFactory.autowireBean(userInfoGetter);
        return userInfoGetter;
    }


    public CardInfoGetter getCardInfoGetter() {
        CardInfoGetter cardInfoGetter = new CardInfoGetterImpl();
        autowireCapableBeanFactory.autowireBean(cardInfoGetter);
        return cardInfoGetter;
    }

}
