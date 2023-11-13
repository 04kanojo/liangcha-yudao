package com.liangcha.system.sms.service;

import com.liangcha.system.sms.dao.SmsLogMapper;
import com.liangcha.system.sms.pojo.domain.SmsLogDO;
import com.liangcha.system.sms.pojo.domain.SmsTemplateDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static com.liangcha.common.utils.ServletUtils.getClientIP;

/**
 * 短信日志 Service 实现类
 *
 * @author zzf
 */
@Slf4j
@Service
public class SmsLogServiceImpl implements SmsLogService {

    @Resource
    private SmsLogMapper smsLogMapper;

    @Override
    public Long createSmsLog(String mobile, Long userId, Integer userType,
                             SmsTemplateDO template, String templateContent, LinkedHashMap<String, String> templateParams) {
        SmsLogDO smsLog = new SmsLogDO()
                .setMobile(mobile)
                .setUserId(userId)
                .setUserType(userType)
                .setSendTime(LocalDateTime.now())
                .setTemplateId(template.getTemplateId())
                .setTemplateCode(template.getCode())
                .setTemplateType(template.getType())
                .setTemplateContent(templateContent)
                .setTemplateParams(templateParams)
                .setTemplateType(template.getType())
                .setCreateIp(getClientIP());

        // 插入数据库
        smsLogMapper.insert(smsLog);
        return smsLog.getId();
    }

    @Override
    public void updateLastByMobile(String mobile) {
        SmsLogDO smsLog = smsLogMapper.selectLastByMobile(mobile);
        smsLog.setUseIp(getClientIP());
        smsLogMapper.updateById(smsLog);
    }

    @Override
    public SmsLogDO getById(Long id) {
        return smsLogMapper.selectById(id);
    }

    @Override
    public void updateById(SmsLogDO smmLog) {
        smsLogMapper.updateById(smmLog);
    }

}
