package com.liangcha.server.controller.auth2.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 管理后台 - OAuth2 客户端 Response VO
 *
 * @author 凉茶
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
