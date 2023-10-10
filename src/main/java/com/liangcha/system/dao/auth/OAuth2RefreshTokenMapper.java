package com.liangcha.system.dao.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.framework.security.pojo.domain.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {
    default void deleteByRefreshToken(String refreshToken) {
        delete(new LambdaQueryWrapper<OAuth2RefreshTokenDO>().eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }
}
