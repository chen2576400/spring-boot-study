package com.chenning.common.quartz.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chenning.common.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface JobMapper extends BaseMapper<ScheduleJob> {

}
