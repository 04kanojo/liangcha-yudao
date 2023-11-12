package com.liangcha.system.sms.service;

import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import com.liangcha.framework.redis.RedisKeyConstants;
import com.liangcha.system.sms.dao.SmsTemplateMapper;
import com.liangcha.system.sms.pojo.domain.SmsTemplateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信模板 Service 实现类
 *
 * @author 凉茶
 */
@Service
@Slf4j
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Override
    public String formatSmsTemplateContent(String content, Map<String, String> params) {
        return StrUtil.format(content, params);
    }

    @Override
    @Cached(name = RedisKeyConstants.SMS_TEMPLATE, key = "#templateId", expire = 1, timeUnit = TimeUnit.HOURS)
    public SmsTemplateDO getByTemplateId(String templateId) {
        return smsTemplateMapper.selectByTemplateId(templateId);
    }
}
