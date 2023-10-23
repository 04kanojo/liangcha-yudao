package com.liangcha.system.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.log.domain.LoginLogDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogDO> {

}
