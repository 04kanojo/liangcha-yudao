package com.liangcha.system.sms.service;


import com.liangcha.system.sms.pojo.domain.SmsTemplateDO;

import java.util.LinkedHashMap;

/**
 * 短信日志 Service 接口
 *
 * @author 凉茶
 */
public interface SmsLogService {

    /**
     * 创建短信日志
     *
     * @param mobile          手机号
     * @param userId          用户编号
     * @param userType        用户类型
     * @param isSend          是否发送
     * @param template        短信模板
     * @param templateContent 短信内容
     * @param templateParams  短信参数
     */
    void createSmsLog(String mobile, Long userId, Integer userType, Boolean isSend,
                      SmsTemplateDO template, String templateContent, LinkedHashMap<String, String> templateParams);

    /**
     * 根据手机号更新最后一个日志
     *
     * @param mobile 手机号
     */
    void updateLastByMobile(String mobile);
}
