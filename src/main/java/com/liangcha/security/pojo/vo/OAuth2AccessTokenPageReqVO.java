package com.liangcha.security.pojo.vo;

import cn.hutool.db.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 访问令牌分页 Request VO
 *
 * @author 凉茶
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2AccessTokenPageReqVO extends Page {

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

}
