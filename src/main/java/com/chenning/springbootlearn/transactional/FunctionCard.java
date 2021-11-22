package com.chenning.springbootlearn.transactional;

import com.chenning.springbootlearn.crud.model.Card;
import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.crud.service.CardService;
import com.chenning.springbootlearn.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author nchen
 * @Date 2021/11/22 14:23
 * @Version 1.0
 * @Description
 */
@Service
public class FunctionCard {
    @Autowired
    CardService cardService;

    public void insertCard(Integer userId) {
        Card card = new Card();
        card.setUserId(userId);
        card.setCardNumber("7777");
        cardService.insert(card);
    }

}
