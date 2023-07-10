package com.liangcha.security.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 管理后台 - OAuth2 客户端 Response VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuth2ClientRespVO extends OAuth2ClientBaseVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
