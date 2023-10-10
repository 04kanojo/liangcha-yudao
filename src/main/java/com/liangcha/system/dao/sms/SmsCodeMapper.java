package com.liangcha.system.dao.sms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.domain.sms.SmsCodeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsCodeMapper extends BaseMapper<SmsCodeDO> {
    default SmsCodeDO selectLastByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<SmsCodeDO>().eq(SmsCodeDO::getMobile, mobile).last("limit 1"));
    }
}
