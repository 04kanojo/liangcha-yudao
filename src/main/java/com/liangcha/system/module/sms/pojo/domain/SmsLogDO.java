package com.liangcha.system.module.sms.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.common.pojo.BaseDO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 短信日志 DO
 *
 * @author 凉茶
 */
@TableName(value = "system_sms_log", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SmsLogDO extends BaseDO {

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 短信类型
     */
    private Integer templateType;

    /**
     * 格式化后的内容
     */
    private String templateContent;

    /**
     * 输入后的参数
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, String> templateParams;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 创建ip
     */
    private String createIp;

    /**
     * 使用ip
     */
    private String useIp;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errMessage;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 回执id
     */
    private String bizId;

}
