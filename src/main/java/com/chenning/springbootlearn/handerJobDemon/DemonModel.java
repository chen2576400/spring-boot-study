package com.chenning.springbootlearn.handerJobDemon;

import com.chenning.springbootlearn.crud.model.User;
import com.chenning.springbootlearn.handerJobDemon.vo.CardVo;
import lombok.Data;

@Data
public class DemonModel {
    private Integer id;
    private User user;
    private CardVo cardVo;


}
