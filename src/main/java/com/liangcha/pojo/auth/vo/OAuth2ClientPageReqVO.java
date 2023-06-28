package com.liangcha.pojo.auth.vo;

import cn.hutool.db.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - OAuth2 客户端分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuth2ClientPageReqVO extends Page {

    /**
     * 应用名
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

}
