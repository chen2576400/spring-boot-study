package com.chenning.common.handlerJob;

import com.chenning.common.crud.model.User;
import com.chenning.common.handlerJob.vo.CardVo;
import lombok.Data;

@Data
public class DemonModel {
    private Integer id;
    private User user;
    private CardVo cardVo;


}
