package com.liangcha.system.module.sms.dao;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.sms.pojo.domain.SmsLogDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsLogMapper extends BaseMapper<SmsLogDO> {

    default SmsLogDO selectLastByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<SmsLogDO>()
                .eq(SmsLogDO::getMobile, mobile)
                .orderByDesc(SmsLogDO::getCreateTime)
                .last("limit 1"));
    }
}
