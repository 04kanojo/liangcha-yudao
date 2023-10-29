package com.liangcha.server.controller.auth2.vo;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * OAuth2 客户端 Base VO，提供给添加、修改、详细的子 VO 使用
 *
 * @author 凉茶
 */
@Data
public class OAuth2ClientBaseVO {

    /**
     * 客户端编号
     */
    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    /**
     * 客户端密钥
     */
    @NotNull(message = "客户端密钥不能为空")
    private String secret;

    @NotNull(message = "应用名不能为空")
    private String name;

    @NotNull(message = "应用图标不能为空")
    @URL(message = "应用图标的地址不正确")
    private String logo;

    /**
     * 应用描述
     */
    private String description;

    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 访问令牌的有效期
     */
    @NotNull(message = "访问令牌的有效期不能为空")
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌的有效期
     */
    @NotNull(message = "刷新令牌的有效期不能为空")
    private Integer refreshTokenValiditySeconds;

    /**
     * 可重定向的 URI 地址
     */
    @NotNull(message = "可重定向的 URI 地址不能为空")
    private List<@NotEmpty(message = "重定向的 URI 不能为空") @URL(message = "重定向的 URI 格式不正确") String> redirectUris;

    /**
     * 授权类型
     */
    @NotNull(message = "授权类型不能为空")
    private List<String> authorizedGrantTypes;

    /**
     * 授权范围
     */
    private List<String> scopes;

    /**
     * 自动通过的授权范围
     */
    private List<String> autoApproveScopes;

    /**
     * 权限
     */
    private List<String> authorities;

    /**
     * 资源
     */
    private List<String> resourceIds;

    /**
     * 附加信息
     */
    private String additionalInformation;

}
