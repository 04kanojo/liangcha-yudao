package com.liangcha.framework.security.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 登录用户信息
 *
 * @author 凉茶
 */
@Data
@Builder
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 授权范围
     */
    private List<String> scopes;

}
