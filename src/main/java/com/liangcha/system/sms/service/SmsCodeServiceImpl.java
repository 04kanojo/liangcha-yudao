package com.liangcha.system.sms.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.convert.auth.AuthConvert;
import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import com.liangcha.framework.rabbitMq.producer.SmsProducer;
import com.liangcha.server.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.sms.dao.SmsCodeMapper;
import com.liangcha.system.sms.domain.SmsCodeDO;
import com.liangcha.system.sms.domain.SmsTemplateDO;
import com.liangcha.system.sms.dto.SmsCodeSendReqDTO;
import com.liangcha.system.sms.enums.SmsSceneEnum;
import com.liangcha.system.sms.properties.SmsCodeProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.randomInt;
import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;
import static com.liangcha.common.utils.ServletUtils.getClientIP;
import static com.liangcha.common.utils.ServletUtils.getRequest;

/**
 * 短信验证码 Service 实现类
 *
 * @author 凉茶
 */
@Service
@Validated
public class SmsCodeServiceImpl implements SmsCodeService {

    @Resource
    private SmsCodeProperties smsCodeProperties;

    @Resource
    private SmsCodeMapper smsCodeMapper;

    @Resource
    private SmsTemplateService smsTemplateService;
    @Resource
    private SmsProducer smsProducer;

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        //解释vo为dto
        SmsCodeSendReqDTO reqDTO = AuthConvert.INSTANCE
                .convert(reqVO)
                .setCreateIp(getClientIP(getRequest()));

        //根据场景获取枚举类
        SmsSceneEnum sceneEnum = SmsSceneEnum.getByScene(reqDTO.getScene());
        if (sceneEnum == null) {
            throw exception(SMS_SEND_SCENE_NOT_EXISTS);
        }

        // 创建验证码
        String code = createSmsCode(reqDTO.getMobile(), reqDTO.getScene(), reqDTO.getCreateIp());

        // 发送验证码
        sendSingleSms(reqDTO.getMobile(), sceneEnum.getTemplateCode(), code);
    }

    @Override
    public void doSendSms(SmsSendMessage message) {
        //TODO 申请完短信继续编码
    }

    public void sendSingleSms(String mobile, String templateCode, String code) {
        // 获得短信模板
        SmsTemplateDO template = smsTemplateService.getSmsTemplateByCodeFromCache(templateCode);

        // 短信模板不存在
        if (template == null) {
            throw exception(SMS_SEND_TEMPLATE_NOT_EXISTS);
        }

        // 校验手机号码是否存在
        if (StrUtil.isEmpty(mobile)) {
            throw exception(SMS_SEND_MOBILE_NOT_EXISTS);
        }

        // 发送 MQ 消息，异步执行发送短信
        smsProducer.sendSmsSendMessage(mobile, template.getApiTemplateId(), code);
    }

    private String createSmsCode(String mobile, Integer scene, String ip) {
        // 校验是否可以发送验证码，不用筛选场景
        SmsCodeDO lastSmsCode = smsCodeMapper.selectLastByMobile(mobile);
        if (lastSmsCode != null) {
            if (LocalDateTimeUtil.between(lastSmsCode.getCreateTime(), LocalDateTime.now()).toMillis()
                    < smsCodeProperties.getSendFrequency().toMillis()) { // 发送过于频繁
                throw exception(SMS_CODE_SEND_TOO_FAST);
            }
            if (isSameDay(lastSmsCode.getCreateTime()) && // 必须是今天，才能计算超过当天的上限
                    lastSmsCode.getTodayIndex() >= smsCodeProperties.getSendMaximumQuantityPerDay()) { // 超过当天发送的上限。
                throw exception(SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY);
            }
        }

        // 创建验证码记录
        String code = String.valueOf(randomInt(smsCodeProperties.getBeginCode(), smsCodeProperties.getEndCode() + 1));
        SmsCodeDO newSmsCode = SmsCodeDO.builder()
                .mobile(mobile)
                .code(code)
                .scene(scene)
                .todayIndex(lastSmsCode != null ? lastSmsCode.getTodayIndex() + 1 : 1)
                .createIp(ip)
                .used(false)
                .build();
        smsCodeMapper.insert(newSmsCode);
        return code;
    }

    public boolean isSameDay(LocalDateTime time) {
        return LocalDateTimeUtil.isSameDay(time, LocalDateTime.now());
    }
}
