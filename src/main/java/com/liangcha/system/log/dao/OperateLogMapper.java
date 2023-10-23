package com.liangcha.system.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.log.domain.OperateLogDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLogDO> {

}
