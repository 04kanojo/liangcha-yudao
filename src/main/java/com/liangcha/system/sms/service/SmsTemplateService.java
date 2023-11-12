package com.liangcha.system.sms.service;

import com.liangcha.system.sms.pojo.domain.SmsTemplateDO;

import java.util.Map;

/**
 * 短信模板 Service 接口
 *
 * @author 凉茶
 */
public interface SmsTemplateService {
    /**
     * 格式化短信内容
     *
     * @param content 短信模板的内容
     * @param params  内容的参数
     * @return 格式化后的内容
     */
    String formatSmsTemplateContent(String content, Map<String, String> params);

    /**
     * 获得短信模板
     *
     * @param templateId 模板id
     * @return 短信模板
     */
    SmsTemplateDO getByTemplateId(String templateId);

}
