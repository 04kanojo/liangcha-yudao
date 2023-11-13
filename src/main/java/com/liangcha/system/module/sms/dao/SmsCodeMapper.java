package com.liangcha.system.module.sms.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.module.sms.pojo.SmsCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface SmsCodeMapper extends BaseMapper<SmsCode> {
    default SmsCode selectLastByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapper<SmsCode>()
                .eq(SmsCode::getMobile, mobile)
                .orderByDesc(true, SmsCode::getId)
                .last("limit 1"));
    }
}
