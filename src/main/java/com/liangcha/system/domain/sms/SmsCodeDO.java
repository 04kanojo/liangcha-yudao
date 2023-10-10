package com.liangcha.system.domain.sms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.liangcha.framework.common.pojo.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 手机验证码 DO
 * <p>
 * idx_mobile 索引：基于 {@link #mobile} 字段
 *
 * @author 凉茶
 */
@TableName("system_sms_code")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeDO extends BaseDO {

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
     * 枚举 {@link SmsCodeDO}
     */
    private Integer scene;
    /**
     * 创建 IP
     */
    private String createIp;
    /**
     * 今日发送的第几条
     */
    private Integer todayIndex;
    /**
     * 是否使用
     */
    private Boolean used;
    /**
     * 使用时间
     */
    private LocalDateTime usedTime;
    /**
     * 使用 IP
     */
    private String usedIp;

}
