package com.liangcha.system.sms.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.sms.domain.SmsCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface SmsCodeMapper extends BaseMapper<SmsCodeDO> {
    default SmsCodeDO selectLastByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<SmsCodeDO>().eq(SmsCodeDO::getMobile, mobile).last("limit 1"));
    }
}
