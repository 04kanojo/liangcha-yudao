package com.liangcha.system.sms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 手机验证码 DO
 *
 * @author 凉茶
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCode implements Serializable {

    /**
     * 编号
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 发送场景
     * <p>
     * 枚举 {@link SmsCode}
     */
    private Integer scene;

    /**
     * 创建 IP
     */
    private String createIp;

}
