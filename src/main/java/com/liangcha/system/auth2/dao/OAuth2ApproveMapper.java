package com.liangcha.system.auth2.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.auth2.pojo.OAuth2Approve;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OAuth2ApproveMapper extends BaseMapper<OAuth2Approve> {

    default List<OAuth2Approve> selectListByUserIdAndUserTypeAndClientId(Long userId, Integer userType, String clientId) {
        return selectList(new LambdaQueryWrapper<OAuth2Approve>()
                .eq(OAuth2Approve::getUserId, userId)
                .eq(OAuth2Approve::getUserType, userType)
                .eq(OAuth2Approve::getClientId, clientId));
    }

    default int update(OAuth2Approve updateObj) {
        return update(updateObj, new LambdaQueryWrapper<OAuth2Approve>()
                .eq(OAuth2Approve::getUserId, updateObj.getUserId())
                .eq(OAuth2Approve::getUserType, updateObj.getUserType())
                .eq(OAuth2Approve::getClientId, updateObj.getClientId())
                .eq(OAuth2Approve::getScope, updateObj.getScope()));
    }

}
