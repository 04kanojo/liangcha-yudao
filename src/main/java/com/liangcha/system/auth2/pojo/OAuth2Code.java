package com.liangcha.system.auth2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * OAuth2 授权码类
 *
 * @author 凉茶
 */
@Data
public class OAuth2Code implements Serializable {

    /**
     * 编号，数据库递增
     */
    private Long id;

    /**
     * 授权码
     */
    private String code;

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
     * 重定向地址
     */
    private String redirectUri;

    /**
     * 状态
     */
    private String state;

}
