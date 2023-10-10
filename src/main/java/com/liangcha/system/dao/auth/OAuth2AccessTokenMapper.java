package com.liangcha.system.dao.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.framework.security.pojo.domain.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 凉茶
 */
@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {
    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(new LambdaQueryWrapper<OAuth2AccessTokenDO>()
                .eq(OAuth2AccessTokenDO::getAccessToken, accessToken));
    }

    default List<OAuth2AccessTokenDO> selectByUserId(Long id) {
        return selectList(new LambdaQueryWrapper<OAuth2AccessTokenDO>().eq(OAuth2AccessTokenDO::getUserId, id));
    }
}
