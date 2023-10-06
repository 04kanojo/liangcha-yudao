package com.liangcha.framework.common.enums;


/**
 * System Redis Key 枚举类
 * 由于注解上不能调用方法,所以使用接口
 *
 * @author 凉茶
 */
public interface RedisKeyConstants {

    /**
     * 指定部门的所有子部门编号数组的缓存
     */
    String DEPT_CHILDREN_ID_LIST = "dept_children_ids_";

    /**
     * 角色的缓存
     */
    String ROLE = "role_";

    /**
     * 用户拥有的角色编号的缓存
     */
    String USER_ROLE_ID_LIST = "user_role_ids_";

    /**
     * 拥有指定菜单的角色编号的缓存
     */
    String MENU_ROLE_ID_LIST = "menu_role_ids_";

    /**
     * 拥有权限对应的菜单编号数组的缓存
     */
    String PERMISSION_MENU_ID_LIST = "permission_menu_ids_";

    /**
     * OAuth2 客户端的缓存
     */
    String OAUTH_CLIENT = "oauth_client_";

    /**
     * 站内信模版的缓存
     */
    String NOTIFY_TEMPLATE = "notify_template_";

    /**
     * 邮件账号的缓存
     */
    String MAIL_ACCOUNT = "mail_account_";

    /**
     * 邮件模版的缓存
     */
    String MAIL_TEMPLATE = "mail_template_";

    /**
     * 短信模版的缓存
     */
    String SMS_TEMPLATE = "sms_template_";

    /**
     * token的缓存
     */
    String OAUTH2_ACCESS_TOKEN = "oauth2_access_token_";
}
