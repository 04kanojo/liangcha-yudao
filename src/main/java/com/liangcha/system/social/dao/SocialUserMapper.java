package com.liangcha.system.social.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.social.domain.SocialUserDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 凉茶
 */
@Mapper
public interface SocialUserMapper extends BaseMapper<SocialUserDO> {
    default SocialUserDO selectByTypeAndCodeAndState(Integer type, String code, String state) {
        return selectOne(new LambdaQueryWrapper<SocialUserDO>()
                .eq(SocialUserDO::getType, type)
                .eq(SocialUserDO::getCode, code)
                .eq(SocialUserDO::getState, state));
    }
}
