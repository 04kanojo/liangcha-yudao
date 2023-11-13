package com.liangcha.framework.rabbitMq.message;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 短信发送消息
 *
 * @author 凉茶
 */
@Data
@Accessors(chain = true)
public class SmsSendMessage implements Serializable {

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;

    /**
     * 短信 API 的模板编号
     */
    @NotNull(message = "短信 API 的模板编号不能为空")
    private String apiTemplateId;

    /**
     * 参数集合
     */
    private LinkedHashMap<String, String> paramMap;

    /**
     * 日志id
     */
    private Long logId;
}
