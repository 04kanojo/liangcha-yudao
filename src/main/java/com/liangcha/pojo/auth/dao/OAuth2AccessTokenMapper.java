package com.liangcha.pojo.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.pojo.auth.db.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {
}
