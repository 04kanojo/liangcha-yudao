package com.liangcha.framework.security.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.framework.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * OAuth2 刷新令牌
 *
 * @author 凉茶
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_oauth2_refresh_token", autoResultMap = true)
public class OAuth2RefreshTokenDO extends BaseDO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

}
