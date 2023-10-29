package com.liangcha.system.auth2;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.framework.security.pojo.domain.OAuth2ApproveDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OAuth2ApproveMapper extends BaseMapper<OAuth2ApproveDO> {

    default List<OAuth2ApproveDO> selectListByUserIdAndUserTypeAndClientId(Long userId, Integer userType, String clientId) {
        return selectList(new LambdaQueryWrapper<OAuth2ApproveDO>()
                .eq(OAuth2ApproveDO::getUserId, userId)
                .eq(OAuth2ApproveDO::getUserType, userType)
                .eq(OAuth2ApproveDO::getClientId, clientId));
    }

}
