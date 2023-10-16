package com.liangcha.system.social.enums;

import com.liangcha.framework.validation.interfaces.IntArr;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 社交平台的类型枚举
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum SocialTypeEnum implements IntArr {

    /**
     * Gitee
     */
    GITEE(10, "GITEE"),
    /**
     * 钉钉
     */
    DINGTALK(20, "DINGTALK"),

    /**
     * 企业微信
     */
    WECHAT_ENTERPRISE(30, "WECHAT_ENTERPRISE"),
    /**
     * 微信公众平台 - 移动端 H5
     */
    WECHAT_MP(31, "WECHAT_MP"),
    /**
     * 微信开放平台 - 网站应用 PC 端扫码授权登录
     */
    WECHAT_OPEN(32, "WECHAT_OPEN"),
    /**
     * 微信小程序
     */
    WECHAT_MINI_APP(34, "WECHAT_MINI_APP"),
    ;

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 类型的标识
     */
    private final String source;

    @Override
    public int[] getIntArr() {
        return Arrays.stream(values()).mapToInt(SocialTypeEnum::getType).toArray();
    }
}
