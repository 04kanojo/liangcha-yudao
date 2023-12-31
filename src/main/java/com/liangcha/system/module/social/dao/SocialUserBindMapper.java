package com.liangcha.system.module.social.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.social.domain.SocialUserBindDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 凉茶
 */
@Mapper
public interface SocialUserBindMapper extends BaseMapper<SocialUserBindDO> {

//    default void deleteByUserTypeAndUserIdAndSocialType(Integer userType, Long userId, Integer socialType) {
//        delete(new LambdaQueryWrapperX<SocialUserBindDO>()
//                .eq(SocialUserBindDO::getUserType, userType)
//                .eq(SocialUserBindDO::getUserId, userId)
//                .eq(SocialUserBindDO::getSocialType, socialType));
//    }
//
//    default void deleteByUserTypeAndSocialUserId(Integer userType, Long socialUserId) {
//        delete(new LambdaQueryWrapperX<SocialUserBindDO>()
//                .eq(SocialUserBindDO::getUserType, userType)
//                .eq(SocialUserBindDO::getSocialUserId, socialUserId));
//    }
//
//    default SocialUserBindDO selectByUserTypeAndSocialUserId(Integer userType, Long socialUserId) {
//        return selectOne(SocialUserBindDO::getUserType, userType,
//                SocialUserBindDO::getSocialUserId, socialUserId);
//    }
//
//    default List<SocialUserBindDO> selectListByUserIdAndUserType(Long userId, Integer userType) {
//        return selectList(new LambdaQueryWrapperX<SocialUserBindDO>()
//                .eq(SocialUserBindDO::getUserId, userId)
//                .eq(SocialUserBindDO::getUserType, userType));
//    }

}
