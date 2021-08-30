package com.chenning.springbootlearn.crud.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chenning.springbootlearn.crud.model.Card;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2021-02-03
 */
@Repository
@Mapper
public interface CardMapper extends BaseMapper<Card> {

}
