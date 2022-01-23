package com.chenning.common.crud.model;

import lombok.Data;

import java.util.List;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 10:36
 */
@Data
public class UserVo {

    private String userName;

    private String address;

    private List<Card> cards;

    private  User user;

}
