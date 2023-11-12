package com.liangcha.system.sms.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.common.pojo.BaseDO;
import com.liangcha.system.user.enums.UserTypeEnum;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsLogDO extends BaseDO {

    /**
     * 自增编号
     */
    private Long id;

    // ========= 模板相关字段 =========

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

    // ========= 手机相关字段 =========

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
     * <p>
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;

    private String createIp;

    private String useIp;


    /**
     * 发送状态
     */
    private Integer sendStatus;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

}
