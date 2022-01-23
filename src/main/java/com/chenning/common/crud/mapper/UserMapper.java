package com.chenning.common.crud.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.model.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2021-02-02
 */
//@Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。
//@Mapper不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserVo>  findUserAndCard();
    List<UserVo>  findUserAndCardByID(@Param("id") Integer userID);
    List<UserVo>  findUserAndCardInIDs(@Param("ids") List<Integer> userIDs);

    List<UserVo>  findUserAndCardByModelParm(@Param("map")Map map);


}
