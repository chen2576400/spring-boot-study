package com.chenning.springbootlearn.crud.service;

import com.baomidou.mybatisplus.service.IService;
import com.chenning.springbootlearn.crud.model.Card;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chen
 * @since 2021-02-03
 */
public interface CardService extends IService<Card> {
    List<Card> findAllCard();
}
