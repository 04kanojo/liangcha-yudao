package com.liangcha.system.module.auth2.pojo;

import com.liangcha.system.module.permission.domain.RoleDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author 凉茶
 */
@Data
@Accessors(chain = true)
public class LoginUser implements Serializable {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 登录用户的角色集合
     */
    private List<RoleDO> roles;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 授权范围
     */
    private List<String> scopes;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
