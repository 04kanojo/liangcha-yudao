package com.liangcha.system.service.sms;

import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import com.liangcha.framework.common.enums.RedisKeyConstants;
import com.liangcha.system.dao.sms.SmsTemplateMapper;
import com.liangcha.system.domain.sms.SmsTemplateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 短信模板 Service 实现类
 *
 * @author zzf
 * @since 2021/1/25 9:25
 */
@Service
@Slf4j
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Override
    public String formatSmsTemplateContent(String content, Map<String, Object> params) {
        return StrUtil.format(content, params);
    }

    @Override
    @Cached(name = RedisKeyConstants.SMS_TEMPLATE, key = "#code")
    public SmsTemplateDO getSmsTemplateByCodeFromCache(String code) {
        return smsTemplateMapper.selectByCode(code);
    }
}
