package com.liangcha.pojo.auth.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.liangcha.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * OAuth2 客户端 DO
 *
 * @author 凉茶
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_oauth2_client", autoResultMap = true)
public class OAuth2ClientDO extends BaseDO {

    /**
     * 编号，数据库自增
     */
    @TableId
    private Long id;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String secret;

    /**
     * 应用名
     */
    private String name;

    /**
     * 应用图标
     */
    private String logo;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 访问令牌的有效期
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌的有效期
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 可重定向的 URI 地址
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> redirectUris;

    /**
     * 授权类型（模式）
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorizedGrantTypes;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 自动授权的 Scope
     * <p>
     * code 授权时，如果 scope 在这个范围内，则自动通过
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> autoApproveScopes;

    /**
     * 权限
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorities;

    /**
     * 资源
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> resourceIds;

    /**
     * 附加信息，JSON 格式
     */
    private String additionalInformation;

}
