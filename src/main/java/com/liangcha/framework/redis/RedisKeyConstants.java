package com.liangcha.framework.redis;


/**
 * Redis Key 枚举类
 * 问：为什么使用接口，不适用枚举
 * 答：因为jetCaChe缓存注解key值需要的是字符串，用接口方便
 *
 * @author 凉茶
 */
public interface RedisKeyConstants {

    /**
     * 指定部门的所有子部门编号数组的缓存
     */
    String DEPT_CHILDREN_ID_LIST = "dept_children_ids:";

    /**
     * 角色的缓存
     */
    String ROLE = "role:";

    /**
     * 用户拥有的角色编号的缓存
     */
    String USER_ROLE_ID_LIST = "user_role_ids:";

    /**
     * 拥有指定菜单的角色编号的缓存
     */
    String MENU_ROLE_ID_LIST = "menu_role_ids:";

    /**
     * 拥有权限对应的菜单编号数组的缓存
     */
    String PERMISSION_MENU_ID_LIST = "permission_menu_ids:";

    /**
     * OAuth2 客户端的缓存
     */
    String OAUTH_CLIENT = "oauth_client:";

    /**
     * OAuth2 批准信息的缓存
     */
    String OAUTH_APPROVE = "oauth_approve:";

    /**
     * OAuth2 code的缓存
     */
    String OAUTH_CODE = "oauth_code:";

    /**
     * 站内信模版的缓存
     */
    String NOTIFY_TEMPLATE = "notify_template:";

    /**
     * 邮件账号的缓存
     */
    String MAIL_ACCOUNT = "mail_account:";

    /**
     * 邮件模版的缓存
     */
    String MAIL_TEMPLATE = "mail_template:";

    /**
     * 短信模版的缓存
     */
    String SMS_TEMPLATE = "sms_template:";

    /**
     * 短信code缓存
     */
    String SMS_CODE = "sms_code:";

    /**
     * 指定客户端token的缓存
     */
    String OAUTH2_ACCESS_TOKEN = "oauth2_access_token:";

    /**
     * 全部客户端token的缓存
     */
    String OAUTH2_ALL_ACCESS_TOKEN = "oauth2_all_access_token:";

    /**
     * refresh_token的缓存
     */
    String OAUTH2_REFRESH_ACCESS_TOKEN = "oauth2_refresh_access_token:";
}
