package com.liangcha.system.service.sms;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.liangcha.framework.common.enums.SmsSceneEnum;
import com.liangcha.framework.common.properties.SmsCodeProperties;
import com.liangcha.framework.convert.auth.AuthConvert;
import com.liangcha.framework.mq.message.SmsSendMessage;
import com.liangcha.framework.mq.producer.SmsProducer;
import com.liangcha.system.controller.auth.vo.AuthSmsSendReqVO;
import com.liangcha.system.controller.sms.dto.SmsCodeSendReqDTO;
import com.liangcha.system.dao.sms.SmsCodeMapper;
import com.liangcha.system.domain.sms.SmsCodeDO;
import com.liangcha.system.domain.sms.SmsTemplateDO;
import com.liangcha.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.randomInt;
import static com.liangcha.framework.common.enums.ErrorCodeEnum.*;
import static com.liangcha.framework.common.utils.ServiceExceptionUtil.exception;

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
    private AdminUserService userService;

    @Resource
    private SmsTemplateService smsTemplateService;
    @Resource
    private SmsProducer smsProducer;

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO, HttpServletRequest request) {
        // 登录场景，验证是否存在
        if (userService.getUserByMobile(reqVO.getMobile()) == null) {
            throw exception(AUTH_MOBILE_NOT_EXISTS);
        }

        //解释vo为dto
        SmsCodeSendReqDTO reqDTO = AuthConvert.INSTANCE
                .convert(reqVO)
                .setCreateIp(ServletUtil.getClientIP(request));

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