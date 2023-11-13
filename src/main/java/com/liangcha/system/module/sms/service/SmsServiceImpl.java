package com.liangcha.system.module.sms.service;

import cn.hutool.core.collection.CollUtil;
import com.alicp.jetcache.Cache;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.framework.convert.sms.SmsConvert;
import com.liangcha.framework.rabbitMq.message.SmsSendMessage;
import com.liangcha.framework.rabbitMq.producer.SmsProducer;
import com.liangcha.system.module.sms.properties.SmsCodeProperties;
import com.liangcha.system.module.sms.dto.SmsCodeSendReqDTO;
import com.liangcha.system.module.sms.enums.SmsSceneEnum;
import com.liangcha.system.module.sms.pojo.SmsCode;
import com.liangcha.system.module.sms.pojo.domain.SmsLogDO;
import com.liangcha.system.module.sms.pojo.domain.SmsTemplateDO;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomInt;
import static com.liangcha.common.enums.ErrorCodeEnum.*;
import static com.liangcha.common.utils.ServiceExceptionUtil.exception;

/**
 * 短信验证码 Service 实现类
 *
 * @author 凉茶
 */
@Service
@Validated
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsCodeProperties smsCodeProperties;

    @Resource
    private SmsTemplateService smsTemplateService;
    @Resource
    private SmsProducer smsProducer;

    @Resource
    private Cache<String, SmsCode> smsCodeCache;

    @Resource
    private SmsLogService smsLogService;

    @Override
    public void sendSmsCode(SmsCodeSendReqDTO reqDTO) {
        //根据场景获取枚举类
        SmsSceneEnum sceneEnum = SmsSceneEnum.getByScene(reqDTO.getScene());
        if (sceneEnum == null) {
            throw exception(SMS_SEND_SCENE_NOT_EXISTS);
        }

        // 创建验证码
        String code = createSmsCode(reqDTO.getMobile(), reqDTO.getScene(), reqDTO.getCreateIp());
        // 发送验证码
        sendSingleSms(reqDTO.getMobile(),
                sceneEnum,
                CollUtil.list(false, code, String.valueOf(smsCodeProperties.getExpireTimes().toMinutes())),
                null,
                null);
    }

    @Override
    public void doSendSms(SmsSendMessage message) {
        // 发送验证码
        SmsResponse smsResponse = SmsFactory
                .createSmsBlend(SupplierType.TENCENT)
                .sendMessage(message.getMobile(),
                        message.getApiTemplateId(),
                        message.getParamMap());

        // 更新数据库
        SmsLogDO smsLog = SmsConvert.INSTANCE.convert(smsResponse).setId(message.getLogId());
        smsLogService.updateById(smsLog);
    }

    /**
     * 可以通过平台的回调地址进行解析，然后更新日志信息
     * 此项目我并未部署到服务器上，不好测试，所以选择这个方式（借口）
     *
     * @param code 验证码
     */
    @Override
    public void useSmsCode(String code) {
        SmsCode smsCode = smsCodeCache.get(code);
        if (!smsCodeCache.remove(code)) {
            throw exception(SMS_CODE_NOT_CORRECT, SMS_CODE_EXPIRED);
        }

        smsLogService.updateLastByMobile(smsCode.getMobile());

    }

    /**
     * 发送单条短信
     *
     * @param mobile    手机号
     * @param sceneEnum 场景枚举
     * @param params    参数集合
     * @param userId    用户id
     * @param userType  用户类型
     */
    public void sendSingleSms(String mobile, SmsSceneEnum sceneEnum, List<String> params, Long userId, Integer userType) {
        // 获得短信模板
        SmsTemplateDO template = smsTemplateService.getByTemplateId(sceneEnum.getTemplateId());

        // 获取参数map集合
        LinkedHashMap<String, String> paramMap = getParamMap(template, params);

        // 创建发送日志。如果模板被禁用，则不发送短信，只记录日志
        String content = smsTemplateService.formatSmsTemplateContent(template.getContent(), paramMap);
        boolean isSend = CommonStatusEnum.ENABLE.getStatus().equals(template.getStatus());
        Long logId = smsLogService.createSmsLog(mobile, userId, userType, template, content, paramMap);

        // 发送 MQ 消息，异步执行发送短信
        if (isSend) {
            smsProducer.sendSmsSendMessage(mobile, template.getTemplateId(), paramMap, logId);
        }
    }

    /**
     * 创建验证码
     *
     * @param mobile 手机号
     * @param scene  发送场景
     * @param ip     ip地址
     * @return 验证码
     */
    private String createSmsCode(String mobile, Integer scene, String ip) {
        // 创建验证码记录
        String code = String.valueOf(randomInt(smsCodeProperties.getBeginCode(), smsCodeProperties.getEndCode() + 1));
        // 创建验证码记录
        SmsCode smsCode = new SmsCode()
                .setMobile(mobile)
                .setCode(code)
                .setScene(scene)
                .setCreateIp(ip);
        smsCodeCache.put(code, smsCode);
        return code;
    }

    /**
     * 获取参数map集合
     *
     * @param template 模板类
     * @param params   参数集合
     * @return 处理后的Map集合
     */
    private LinkedHashMap<String, String> getParamMap(SmsTemplateDO template, List<String> params) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<String> names = template.getParams();
        if (names.size() != params.size()) {
            throw exception(SMS_TEMPLATE_PARAM_SIZE_ERR);
        }
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), params.get(i));
        }
        return map;
    }

}
