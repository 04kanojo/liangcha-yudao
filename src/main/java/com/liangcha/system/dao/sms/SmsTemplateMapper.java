package com.liangcha.system.dao.sms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.domain.sms.SmsTemplateDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsTemplateMapper extends BaseMapper<SmsTemplateDO> {

    default SmsTemplateDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<SmsTemplateDO>().eq(SmsTemplateDO::getCode, code));
    }

}
