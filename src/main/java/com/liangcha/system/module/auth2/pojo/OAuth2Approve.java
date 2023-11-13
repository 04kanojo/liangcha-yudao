package com.liangcha.system.module.auth2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * OAuth2 批准类
 * 记录接受的 scope 列表
 *
 * @author 凉茶
 */
@Data
public class OAuth2Approve implements Serializable {

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
    private String scope;

    /**
     * 是否接受
     * <p>
     * true - 接受
     * false - 拒绝
     */
    private Boolean approved;

}
