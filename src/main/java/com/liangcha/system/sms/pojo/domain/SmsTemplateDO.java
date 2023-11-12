package com.liangcha.system.sms.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.common.enums.CommonStatusEnum;
import com.liangcha.common.pojo.BaseDO;
import com.liangcha.system.sms.enums.SmsTemplateTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 短信模板 DO
 *
 * @author 凉茶
 */
@TableName(value = "system_sms_template", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SmsTemplateDO extends BaseDO implements Serializable {

    /**
     * 编号
     */
    private Long id;

    // ========= 模板相关字段 =========

    /**
     * 短信类型
     * <p>
     * 枚举 {@link SmsTemplateTypeEnum}
     */
    private Integer type;

    /**
     * 启用状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 模板编码，保证唯一
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板内容
     * <p>
     * 内容的参数，使用 {} 包括，例如说 {name}
     */
    private String content;

    /**
     * 参数数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> params;

    /**
     * 备注
     */
    private String remark;

    /**
     * 短信 API 的模板编号
     */
    private String templateId;

}
