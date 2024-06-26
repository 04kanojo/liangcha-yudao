package com.liangcha.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局错误码
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    // ========== 基本枚举 ==========
    SUCCESS(200, "成功"),

    SERVICE_ERROR(400, "业务异常"),

    SYSTEM_ERROR(500, "系统异常"),

    UNKNOWN(666, "未知错误"),


    // ========== 权限校验 ==========

    NO_LOGIN(401, "账号未登录"),

    SMALL_AUTHORITY(402, "没有该操作权限"),

    TOKEN_ERR(403, "token解析失败"),

    ACCESS_TOKEN_NOT_EXIST(404, "访问令牌不存在"),

    ACCESS_TOKEN_EXPIRED(405, "访问令牌已过期"),

    FLUSH_TOKEN_NOT_EXIST(406, "刷新令牌不存在"),

    FLUSH_TOKEN_CLIENT_ERR(407, "刷新令牌的客户端编号不正确"),

    FLUSH_TOKEN_EXPIRED(408, "刷新令牌已过期"),

    // ========== 验证码校验 ==========

    CAPTCHA_EXPIRED(409, "验证码失效，请重新获取"),

    CAPTCHA_ERR(410, "验证码错误"),

    // ========== 请求校验 ==========
    ERR_REQUEST(411, "请求参数不正确"),

    NOT_FOUND(412, "请求未找到"),

    METHOD_NOT_ALLOWED(413, "请求方法错误"),

    IO_ERR(414, "io流异常"),

    // ========== 客户端错误段 ==========

    BAD_REQUEST(400, "请求参数不正确"),

    UNAUTHORIZED(401, "账号未登录"),

    FORBIDDEN(403, "没有该操作权限"),

    LOCKED(423, "请求失败，请稍后重试"),

    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

    // ========== 数据权限 ==========
    DATA_SCOPE_NOT_EXISTS(416, "数据范围不存在"),

    DATA_SCOPE_KEY_VALUE_ERR(417, "数据范围key与value长度不匹配"),

    DATA_SCOPE_PARSE_ERR(418, "数据权限解析异常"),

    //  AUTH 模块 1002000000
    AUTH_LOGIN_BAD_CREDENTIALS(1002000000, "登录失败，账号密码不正确"),
    AUTH_LOGIN_USER_DISABLED(1002000001, "登录失败，账号被禁用"),
    AUTH_LOGIN_CAPTCHA_CODE_ERROR(1002000004, "验证码不正确，原因：{}"),
    AUTH_THIRD_LOGIN_NOT_BIND(1002000005, "未绑定账号，需要进行绑定"),
    AUTH_TOKEN_EXPIRED(1002000006, "Token 已经过期"),
    AUTH_MOBILE_NOT_EXISTS(1002000007, "手机号不存在"),

    //  菜单模块 1002001000
    MENU_NAME_DUPLICATE(1002001000, "已经存在该名字的菜单"),
    MENU_PARENT_NOT_EXISTS(1002001001, "父菜单不存在"),
    MENU_PARENT_ERROR(1002001002, "不能设置自己为父菜单"),
    MENU_NOT_EXISTS(1002001003, "菜单不存在"),
    MENU_EXISTS_CHILDREN(1002001004, "存在子菜单，无法删除"),
    MENU_PARENT_NOT_DIR_OR_MENU(1002001005, "父菜单的类型必须是目录或者菜单"),

    //  角色模块 1002002000
    ROLE_NOT_EXISTS(1002002000, "角色不存在"),
    ROLE_NAME_DUPLICATE(1002002001, "已经存在名为【{}】的角色"),
    ROLE_CODE_DUPLICATE(1002002002, "已经存在编码为【{}】的角色"),
    ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE(1002002003, "不能操作类型为系统内置的角色"),
    ROLE_IS_DISABLE(1002002004, "名字为【{}】的角色已被禁用"),
    ROLE_ADMIN_CODE_ERROR(1002002005, "编码【{}】不能使用"),

    //  用户模块 1002003000
    USER_USERNAME_EXISTS(1002003000, "用户账号已经存在"),
    USER_MOBILE_EXISTS(1002003001, "手机号已经存在"),
    USER_EMAIL_EXISTS(1002003002, "邮箱已经存在"),
    USER_NOT_EXISTS(1002003003, "用户不存在"),
    USER_IMPORT_LIST_IS_EMPTY(1002003004, "导入用户数据不能为空！"),
    USER_PASSWORD_FAILED(1002003005, "用户密码校验失败"),
    USER_IS_DISABLE(1002003006, "名字为【{}】的用户已被禁用"),
    USER_COUNT_MAX(1002003008, "创建用户失败，原因：超过租户最大租户配额({})！"),

    //  部门模块 1002004000
    DEPT_NAME_DUPLICATE(1002004000, "已经存在该名字的部门"),
    DEPT_PARENT_NOT_EXITS(1002004001, "父级部门不存在"),
    DEPT_NOT_FOUND(1002004002, "当前部门不存在"),
    DEPT_EXITS_CHILDREN(1002004003, "存在子部门，无法删除"),
    DEPT_PARENT_ERROR(1002004004, "不能设置自己为父部门"),
    DEPT_EXISTS_USER(1002004005, "部门中存在员工，无法删除"),
    DEPT_NOT_ENABLE(1002004006, "部门({})不处于开启状态，不允许选择"),
    DEPT_PARENT_IS_CHILD(1002004007, "不能设置自己的子部门为父部门"),

    //  岗位模块 1002005000
    POST_NOT_FOUND(1002005000, "当前岗位不存在"),
    POST_NOT_ENABLE(1002005001, "岗位({}) 不处于开启状态，不允许选择"),
    POST_NAME_DUPLICATE(1002005002, "已经存在该名字的岗位"),
    POST_CODE_DUPLICATE(1002005003, "已经存在该标识的岗位"),

    //  字典类型 1002006000
    DICT_TYPE_NOT_EXISTS(1002006001, "当前字典类型不存在"),
    DICT_TYPE_NOT_ENABLE(1002006002, "字典类型不处于开启状态，不允许选择"),
    DICT_TYPE_NAME_DUPLICATE(1002006003, "已经存在该名字的字典类型"),
    DICT_TYPE_TYPE_DUPLICATE(1002006004, "已经存在该类型的字典类型"),
    DICT_TYPE_HAS_CHILDREN(1002006005, "无法删除，该字典类型还有字典数据"),

    //  字典数据 1002007000
    DICT_DATA_NOT_EXISTS(1002007001, "当前字典数据不存在"),
    DICT_DATA_NOT_ENABLE(1002007002, "字典数据({})不处于开启状态，不允许选择"),
    DICT_DATA_VALUE_DUPLICATE(1002007003, "已经存在该值的字典数据"),

    //  通知公告 1002008000
    NOTICE_NOT_FOUND(1002008001, "当前通知公告不存在"),

    //  短信模板 1002012000
    SMS_TEMPLATE_NOT_EXISTS(1002012000, "短信模板不存在"),
    SMS_TEMPLATE_CODE_DUPLICATE(1002012001, "已经存在编码为【{}】的短信模板"),

    SMS_TEMPLATE_PARAM_SIZE_ERR(1002012002, "短信模板参数长度不对应"),


    //  短信发送 1002013000
    SMS_SEND_MOBILE_NOT_EXISTS(1002013000, "手机号不存在"),
    SMS_SEND_MOBILE_TEMPLATE_PARAM_MISS(1002013001, "模板参数({})缺失"),
    SMS_SEND_TEMPLATE_NOT_EXISTS(1002013002, "短信模板不存在"),

    SMS_SEND_SCENE_NOT_EXISTS(1002013003, "发送短信场景不存在"),

    //  短信验证码 1002014000
    SMS_CODE_NOT_FOUND(1002014000, "验证码不存在"),
    SMS_CODE_EXPIRED(1002014001, "验证码已过期"),
    SMS_CODE_USED(1002014002, "验证码已使用"),
    SMS_CODE_NOT_CORRECT(1002014003, "验证码不正确"),
    SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY(1002014004, "超过每日短信发送数量"),
    SMS_CODE_SEND_TOO_FAST(1002014005, "短信发送过于频率"),
    SMS_CODE_IS_EXISTS(1002014006, "手机号已被使用"),
    SMS_CODE_IS_UNUSED(1002014007, "验证码未被使用"),

    //  错误码模块 1002017000
    ERROR_CODE_NOT_EXISTS(1002017000, "错误码不存在"),
    ERROR_CODE_DUPLICATE(1002017001, "已经存在编码为【{}】的错误码"),

    //  社交用户 1002018000
    SOCIAL_USER_AUTH_FAILURE(1002018000, "社交授权失败，原因是：{}"),
    SOCIAL_USER_UNBIND_NOT_SELF(1002018001, "社交解绑失败，非当前用户绑定"),
    SOCIAL_USER_NOT_FOUND(1002018002, "社交授权失败，找不到对应的用户"),

    SOCIAL_TYPE_ERR(1002018003, "社交类型错误"),

    //  系统敏感词 1002019000
    SENSITIVE_WORD_NOT_EXISTS(1002019000, "系统敏感词在所有标签中都不存在"),
    SENSITIVE_WORD_EXISTS(1002019001, "系统敏感词已在标签中存在"),

    //  OAuth2 客户端 1002020000
    OAUTH2_CLIENT_NOT_EXISTS(1002020000, "OAuth2 客户端不存在"),
    OAUTH2_CLIENT_EXISTS(1002020001, "OAuth2 客户端编号已存在"),
    OAUTH2_CLIENT_DISABLE(1002020002, "OAuth2 客户端已禁用"),
    OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS(1002020003, "不支持该授权类型"),
    OAUTH2_CLIENT_SCOPE_OVER(1002020004, "授权范围过大"),
    OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH(1002020005, "无效 redirect_uri: {}"),
    OAUTH2_CLIENT_CLIENT_SECRET_ERROR(1002020006, "无效 client_secret: {}"),

    OAUTH2_CLIENT_AUTO_GRANT_ERR(1002020007, "自动授权失败"),

    //  OAuth2 授权 1002021000
    OAUTH2_GRANT_CLIENT_ID_MISMATCH(1002021000, "client_id 不匹配"),
    OAUTH2_GRANT_REDIRECT_URI_MISMATCH(1002021001, "redirect_uri 不匹配"),
    OAUTH2_GRANT_STATE_MISMATCH(1002021002, "state 不匹配"),
    OAUTH2_GRANT_CODE_NOT_EXISTS(1002021003, "code 不存在"),

    //  OAuth2 授权 1002022000
    OAUTH2_CODE_NOT_EXISTS_OR_EXPIRE(1002022000, "code 不存在或者已过期"),

    //  邮箱账号 1002023000
    MAIL_ACCOUNT_NOT_EXISTS(1002023000, "邮箱账号不存在"),
    MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS(1002023001, "无法删除，该邮箱账号还有邮件模板"),

    //  邮件模版 1002024000
    MAIL_TEMPLATE_NOT_EXISTS(1002024000, "邮件模版不存在"),
    MAIL_TEMPLATE_CODE_EXISTS(1002024001, "邮件模版 code({}) 已存在"),

    //  邮件发送 1002025000
    MAIL_SEND_TEMPLATE_PARAM_MISS(1002025000, "模板参数({})缺失"),
    MAIL_SEND_MAIL_NOT_EXISTS(1002025000, "邮箱不存在"),

    //  站内信模版 1002026000
    NOTIFY_TEMPLATE_NOT_EXISTS(1002026000, "站内信模版不存在"),
    NOTIFY_TEMPLATE_CODE_DUPLICATE(1002026001, "已经存在编码为【{}】的站内信模板"),

    //  站内信发送 1002028000
    NOTIFY_SEND_TEMPLATE_PARAM_MISS(1002025000, "模板参数({})缺失"),

    // ========= 文件相关 1-001-003-000 =================
    FILE_ALREADY_EXISTS(1_001_003_000, "文件已存在，请勿重复上传"),
    FILE_NOT_EXISTS(1_001_003_001, "文件不存在"),
    FILE_IS_EMPTY(1_001_003_002, "文件为空"),
    FILE_TYPE_ERR(1_001_003_004, "文件类型错误"),

    FILE_BUCKET_LENGTH_ERR(1_001_003_005, "桶长度最短3个字符最长63个字符"),

    // ========== 商品品牌相关编号 1-008-002-000 ==========
    BRAND_NOT_EXISTS(1_008_002_000, "品牌不存在"),
    BRAND_DISABLED(1_008_002_001, "品牌已禁用"),
    BRAND_NAME_EXIST(1_008_002_002, "品牌名称已存在");


    private final Integer code;
    private final String msg;


}
