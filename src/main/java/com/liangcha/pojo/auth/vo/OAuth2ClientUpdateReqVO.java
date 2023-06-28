package com.liangcha.pojo.auth.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 管理后台 - OAuth2 客户端更新 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuth2ClientUpdateReqVO extends OAuth2ClientBaseVO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Long id;

}
