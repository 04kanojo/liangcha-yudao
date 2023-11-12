package com.liangcha.system.sms.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangcha.system.sms.pojo.domain.SmsTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 凉茶
 */
@Mapper
public interface SmsTemplateMapper extends BaseMapper<SmsTemplateDO> {

    default SmsTemplateDO selectByTemplateId(String templateId) {
        return selectOne(new LambdaQueryWrapper<SmsTemplateDO>().eq(SmsTemplateDO::getTemplateId, templateId));
    }

}
