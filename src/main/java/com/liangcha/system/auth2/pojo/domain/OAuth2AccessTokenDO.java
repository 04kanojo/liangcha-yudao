package com.liangcha.system.auth2.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OAuth2 访问令牌 DO
 * <p>
 * 如下字段，暂时未使用，暂时不支持：
 * user_name、authentication（用户信息）
 *
 * @author 芋道源码
 */
@TableName(value = "system_oauth2_access_token", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2AccessTokenDO extends BaseDO {

    /**
     * 编号，数据库递增
     */
    @TableId
    private Long id;

    /**
     * 访问令牌
     */
    private String accessToken;

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

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}