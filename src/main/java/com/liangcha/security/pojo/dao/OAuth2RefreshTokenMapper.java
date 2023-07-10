package com.liangcha.security.pojo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.security.pojo.db.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {
}
