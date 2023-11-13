/*
Navicat MySQL Data Transfer

Source Server         : 腾讯服务器容器mysql1老密码
Source Server Version : 50730
Source Host           : 124.223.63.101:3306
Source Database       : liangcha-yudao

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2023-11-13 20:18:13
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_dept
-- ----------------------------
DROP TABLE IF EXISTS `system_dept`;
CREATE TABLE `system_dept`
(
    `id`             bigint(20)                             NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `name`           varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
    `parent_id`      bigint(20)                             NOT NULL DEFAULT '0' COMMENT '父部门id',
    `sort`           int(11)                                NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `leader_user_id` bigint(20)                                      DEFAULT NULL COMMENT '负责人',
    `phone`          varchar(11) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '联系电话',
    `email`          varchar(50) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '邮箱',
    `status`         tinyint(4)                             NOT NULL COMMENT '部门状态（0正常 1停用）',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint(20)                             NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 112
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='部门表';

-- ----------------------------
-- Records of system_dept
-- ----------------------------
INSERT INTO `system_dept`
VALUES ('100', '凉茶', '0', '0', '1', '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '1',
        '2023-11-11 15:26:09', '\0', '1');
INSERT INTO `system_dept`
VALUES ('101', '深圳总公司', '100', '1', '104', '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '1',
        '2022-05-16 20:25:23', '\0', '1');
INSERT INTO `system_dept`
VALUES ('102', '长沙分公司', '100', '2', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '',
        '2021-12-15 05:01:40', '\0', '1');
INSERT INTO `system_dept`
VALUES ('103', '研发部门', '101', '1', '104', '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '103',
        '2022-01-14 01:04:14', '\0', '1');
INSERT INTO `system_dept`
VALUES ('104', '市场部门', '101', '2', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '',
        '2021-12-15 05:01:38', '\0', '1');
INSERT INTO `system_dept`
VALUES ('105', '测试部门', '101', '3', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '1',
        '2022-05-16 20:25:15', '\0', '1');
INSERT INTO `system_dept`
VALUES ('106', '财务部门', '101', '4', '103', '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '103',
        '2022-01-15 21:32:22', '\0', '1');
INSERT INTO `system_dept`
VALUES ('107', '运维部门', '101', '5', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '',
        '2021-12-15 05:01:33', '\0', '1');
INSERT INTO `system_dept`
VALUES ('108', '市场部门', '102', '1', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '1',
        '2022-02-16 08:35:45', '\0', '1');
INSERT INTO `system_dept`
VALUES ('109', '财务部门', '102', '2', null, '15888888888', 'ry@qq.com', '0', 'admin', '2021-01-05 17:03:47', '',
        '2021-12-15 05:01:29', '\0', '1');
INSERT INTO `system_dept`
VALUES ('110', '新部门', '0', '1', null, null, null, '0', '110', '2022-02-23 20:46:30', '110', '2022-02-23 20:46:30',
        '\0', '121');
INSERT INTO `system_dept`
VALUES ('111', '顶级部门', '0', '1', null, null, null, '0', '113', '2022-03-07 21:44:50', '113', '2022-03-07 21:44:50',
        '\0', '122');

-- ----------------------------
-- Table structure for system_file
-- ----------------------------
DROP TABLE IF EXISTS `system_file`;
CREATE TABLE `system_file`
(
    `id`          bigint(20)                               NOT NULL AUTO_INCREMENT COMMENT '文件编号',
    `name`        varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '文件名',
    `path`        varchar(512) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '文件路径',
    `url`         varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件 URL',
    `type`        varchar(128) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '文件类型',
    `size`        int(11)                                  NOT NULL COMMENT '文件大小',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1108
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文件表';

-- ----------------------------
-- Records of system_file
-- ----------------------------

-- ----------------------------
-- Table structure for system_login_log
-- ----------------------------
DROP TABLE IF EXISTS `system_login_log`;
CREATE TABLE `system_login_log`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `log_type`    bigint(20)                              NOT NULL COMMENT '日志类型',
    `trace_id`    varchar(64) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `user_id`     bigint(20)                              NOT NULL DEFAULT '0' COMMENT '用户编号',
    `user_type`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '用户类型',
    `username`    varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '用户账号',
    `result`      tinyint(4)                              NOT NULL COMMENT '登陆结果',
    `user_ip`     varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户 IP',
    `user_agent`  varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '浏览器 UA',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1723736763667226627
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统访问记录';

-- ----------------------------
-- Records of system_login_log
-- ----------------------------
INSERT INTO `system_login_log`
VALUES ('1723705971620667393', '100', '', '1', '2', 'admin', '0', '0:0:0:0:0:0:0:1',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36',
        null, '2023-11-12 22:15:03', null, '2023-11-12 22:15:03', '\0', '0');
INSERT INTO `system_login_log`
VALUES ('1723706855532486657', '100', '', '1', '2', 'admin', '0', '0:0:0:0:0:0:0:1',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36',
        null, '2023-11-12 22:18:34', null, '2023-11-12 22:18:34', '\0', '0');
INSERT INTO `system_login_log`
VALUES ('1723736763667226626', '103', '', '1', '2', '19923209856', '0', '0:0:0:0:0:0:0:1',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36',
        null, '2023-11-13 00:17:25', null, '2023-11-13 00:17:25', '\0', '0');

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`
(
    `id`             bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`           varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '菜单名称',
    `permission`     varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `type`           tinyint(4)                              NOT NULL COMMENT '菜单类型',
    `sort`           int(11)                                 NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `parent_id`      bigint(20)                              NOT NULL DEFAULT '0' COMMENT '父菜单ID',
    `path`           varchar(200) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '路由地址',
    `icon`           varchar(100) COLLATE utf8mb4_unicode_ci          DEFAULT '#' COMMENT '菜单图标',
    `component`      varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '组件路径',
    `component_name` varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '组件名',
    `status`         tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '菜单状态',
    `visible`        bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `keep_alive`     bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否缓存',
    `always_show`    bit(1)                                  NOT NULL DEFAULT b'1' COMMENT '是否总是显示',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2391
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='菜单权限表';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu`
VALUES ('1', '系统管理', '', '1', '10', '0', '/system', 'system', null, null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('2', '基础设施', '', '1', '20', '0', '/infra', 'monitor', null, null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('5', 'OA 示例', '', '1', '40', '1185', 'oa', 'people', null, null, '0', '', '', '', 'admin',
        '2021-09-20 16:26:19', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('100', '用户管理', 'system:user:list', '2', '1', '1', 'user', 'user', 'system/user/index', 'SystemUser', '0',
        '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:31:59', '\0');
INSERT INTO `system_menu`
VALUES ('101', '角色管理', '', '2', '2', '1', 'role', 'peoples', 'system/role/index', 'SystemRole', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:33:59', '\0');
INSERT INTO `system_menu`
VALUES ('102', '菜单管理', '', '2', '3', '1', 'menu', 'tree-table', 'system/menu/index', 'SystemMenu', '0', '', '',
        '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:34:32', '\0');
INSERT INTO `system_menu`
VALUES ('103', '部门管理', '', '2', '4', '1', 'dept', 'tree', 'system/dept/index', 'SystemDept', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:35:32', '\0');
INSERT INTO `system_menu`
VALUES ('104', '岗位管理', '', '2', '5', '1', 'post', 'post', 'system/post/index', 'SystemPost', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:36:21', '\0');
INSERT INTO `system_menu`
VALUES ('105', '字典管理', '', '2', '6', '1', 'dict', 'dict', 'system/dict/index', 'SystemDictType', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:36:45', '\0');
INSERT INTO `system_menu`
VALUES ('106', '配置管理', '', '2', '6', '2', 'config', 'edit', 'infra/config/index', 'InfraConfig', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 10:31:17', '\0');
INSERT INTO `system_menu`
VALUES ('107', '通知公告', '', '2', '8', '1', 'notice', 'message', 'system/notice/index', 'SystemNotice', '0', '', '',
        '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:45:06', '\0');
INSERT INTO `system_menu`
VALUES ('108', '审计日志', '', '1', '9', '1', 'log', 'log', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('109', '令牌管理', '', '2', '2', '1261', 'token', 'online', 'system/oauth2/token/index', 'SystemTokenClient',
        '0', '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:47:41', '\0');
INSERT INTO `system_menu`
VALUES ('110', '定时任务', '', '2', '12', '2', 'job', 'job', 'infra/job/index', 'InfraJob', '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2023-04-08 10:36:49', '\0');
INSERT INTO `system_menu`
VALUES ('111', 'MySQL 监控', '', '2', '9', '2', 'druid', 'druid', 'infra/druid/index', 'InfraDruid', '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2023-04-08 09:09:30', '\0');
INSERT INTO `system_menu`
VALUES ('112', 'Java 监控', '', '2', '11', '2', 'admin-server', 'server', 'infra/server/index', 'InfraAdminServer', '0',
        '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 10:34:08', '\0');
INSERT INTO `system_menu`
VALUES ('113', 'Redis 监控', '', '2', '10', '2', 'redis', 'redis', 'infra/redis/index', 'InfraRedis', '0', '', '',
        '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 10:33:30', '\0');
INSERT INTO `system_menu`
VALUES ('114', '表单构建', 'infra:build:list', '2', '2', '2', 'build', 'build', 'infra/build/index', 'InfraBuild', '0',
        '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 09:06:12', '\0');
INSERT INTO `system_menu`
VALUES ('115', '代码生成', 'infra:codegen:query', '2', '1', '2', 'codegen', 'code', 'infra/codegen/index',
        'InfraCodegen', '0', '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 09:02:24', '\0');
INSERT INTO `system_menu`
VALUES ('116', '系统接口', 'infra:swagger:list', '2', '3', '2', 'swagger', 'swagger', 'infra/swagger/index',
        'InfraSwagger', '0', '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 09:11:28', '\0');
INSERT INTO `system_menu`
VALUES ('500', '操作日志', '', '2', '1', '108', 'operate-log', 'form', 'system/operatelog/index', 'SystemOperateLog',
        '0', '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:47:00', '\0');
INSERT INTO `system_menu`
VALUES ('501', '登录日志', '', '2', '2', '108', 'login-log', 'logininfor', 'system/loginlog/index', 'SystemLoginLog',
        '0', '', '', '', 'admin', '2021-01-05 17:03:48', '1', '2023-04-08 08:46:18', '\0');
INSERT INTO `system_menu`
VALUES ('1001', '用户查询', 'system:user:query', '3', '1', '100', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1002', '用户新增', 'system:user:create', '3', '2', '100', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1003', '用户修改', 'system:user:update', '3', '3', '100', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1004', '用户删除', 'system:user:delete', '3', '4', '100', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1005', '用户导出', 'system:user:export', '3', '5', '100', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1006', '用户导入', 'system:user:import', '3', '6', '100', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1007', '重置密码', 'system:user:update-password', '3', '7', '100', '', '', '', null, '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1008', '角色查询', 'system:role:query', '3', '1', '101', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1009', '角色新增', 'system:role:create', '3', '2', '101', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1010', '角色修改', 'system:role:update', '3', '3', '101', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1011', '角色删除', 'system:role:delete', '3', '4', '101', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1012', '角色导出', 'system:role:export', '3', '5', '101', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1013', '菜单查询', 'system:menu:query', '3', '1', '102', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1014', '菜单新增', 'system:menu:create', '3', '2', '102', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1015', '菜单修改', 'system:menu:update', '3', '3', '102', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1016', '菜单删除', 'system:menu:delete', '3', '4', '102', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1017', '部门查询', 'system:dept:query', '3', '1', '103', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1018', '部门新增', 'system:dept:create', '3', '2', '103', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1019', '部门修改', 'system:dept:update', '3', '3', '103', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1020', '部门删除', 'system:dept:delete', '3', '4', '103', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1021', '岗位查询', 'system:post:query', '3', '1', '104', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1022', '岗位新增', 'system:post:create', '3', '2', '104', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1023', '岗位修改', 'system:post:update', '3', '3', '104', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1024', '岗位删除', 'system:post:delete', '3', '4', '104', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1025', '岗位导出', 'system:post:export', '3', '5', '104', '', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1026', '字典查询', 'system:dict:query', '3', '1', '105', '#', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1027', '字典新增', 'system:dict:create', '3', '2', '105', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1028', '字典修改', 'system:dict:update', '3', '3', '105', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1029', '字典删除', 'system:dict:delete', '3', '4', '105', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1030', '字典导出', 'system:dict:export', '3', '5', '105', '#', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1031', '配置查询', 'infra:config:query', '3', '1', '106', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1032', '配置新增', 'infra:config:create', '3', '2', '106', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1033', '配置修改', 'infra:config:update', '3', '3', '106', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1034', '配置删除', 'infra:config:delete', '3', '4', '106', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1035', '配置导出', 'infra:config:export', '3', '5', '106', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1036', '公告查询', 'system:notice:query', '3', '1', '107', '#', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1037', '公告新增', 'system:notice:create', '3', '2', '107', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1038', '公告修改', 'system:notice:update', '3', '3', '107', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1039', '公告删除', 'system:notice:delete', '3', '4', '107', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1040', '操作查询', 'system:operate-log:query', '3', '1', '500', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1042', '日志导出', 'system:operate-log:export', '3', '2', '500', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1043', '登录查询', 'system:login-log:query', '3', '1', '501', '#', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1045', '日志导出', 'system:login-log:export', '3', '3', '501', '#', '#', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1046', '令牌列表', 'system:oauth2-token:page', '3', '1', '109', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-05-09 23:54:42', '\0');
INSERT INTO `system_menu`
VALUES ('1048', '令牌删除', 'system:oauth2-token:delete', '3', '2', '109', '', '', '', null, '0', '', '', '',
        'admin', '2021-01-05 17:03:48', '1', '2022-05-09 23:54:53', '\0');
INSERT INTO `system_menu`
VALUES ('1050', '任务新增', 'infra:job:create', '3', '2', '110', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1051', '任务修改', 'infra:job:update', '3', '3', '110', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1052', '任务删除', 'infra:job:delete', '3', '4', '110', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1053', '状态修改', 'infra:job:update', '3', '5', '110', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1054', '任务导出', 'infra:job:export', '3', '7', '110', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1056', '生成修改', 'infra:codegen:update', '3', '2', '115', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1057', '生成删除', 'infra:codegen:delete', '3', '3', '115', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1058', '导入代码', 'infra:codegen:create', '3', '2', '115', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1059', '预览代码', 'infra:codegen:preview', '3', '4', '115', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1060', '生成代码', 'infra:codegen:download', '3', '5', '115', '', '', '', null, '0', '', '', '', 'admin',
        '2021-01-05 17:03:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1063', '设置角色菜单权限', 'system:permission:assign-role-menu', '3', '6', '101', '', '', '', null, '0', '',
        '', '', '', '2021-01-06 17:53:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1064', '设置角色数据权限', 'system:permission:assign-role-data-scope', '3', '7', '101', '', '', '', null, '0',
        '', '', '', '', '2021-01-06 17:56:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1065', '设置用户角色', 'system:permission:assign-user-role', '3', '8', '101', '', '', '', null, '0', '', '',
        '', '', '2021-01-07 10:23:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1066', '获得 Redis 监控信息', 'infra:redis:get-monitor-info', '3', '1', '113', '', '', '', null, '0', '', '',
        '', '', '2021-01-26 01:02:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1067', '获得 Redis Key 列表', 'infra:redis:get-key-list', '3', '2', '113', '', '', '', null, '0', '', '',
        '', '', '2021-01-26 01:02:52', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1070', '代码生成示例', 'infra:test-demo:query', '2', '1', '2', 'test-demo', 'validCode',
        'infra/testDemo/index', null, '0', '', '', '', '', '2021-02-06 12:42:49', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1071', '测试示例表创建', 'infra:test-demo:create', '3', '1', '1070', '', '', '', null, '0', '', '', '', '',
        '2021-02-06 12:42:49', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1072', '测试示例表更新', 'infra:test-demo:update', '3', '2', '1070', '', '', '', null, '0', '', '', '', '',
        '2021-02-06 12:42:49', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1073', '测试示例表删除', 'infra:test-demo:delete', '3', '3', '1070', '', '', '', null, '0', '', '', '', '',
        '2021-02-06 12:42:49', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1074', '测试示例表导出', 'infra:test-demo:export', '3', '4', '1070', '', '', '', null, '0', '', '', '', '',
        '2021-02-06 12:42:49', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1075', '任务触发', 'infra:job:trigger', '3', '8', '110', '', '', '', null, '0', '', '', '', '',
        '2021-02-07 13:03:10', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1076', '数据库文档', '', '2', '4', '2', 'db-doc', 'table', 'infra/dbDoc/index', 'InfraDBDoc', '0', '', '',
        '', '', '2021-02-08 01:41:47', '1', '2023-04-08 09:13:38', '\0');
INSERT INTO `system_menu`
VALUES ('1077', '监控平台', '', '2', '13', '2', 'skywalking', 'eye-open', 'infra/skywalking/index', 'InfraSkyWalking',
        '0', '', '', '', '', '2021-02-08 20:41:31', '1', '2023-04-08 10:39:06', '\0');
INSERT INTO `system_menu`
VALUES ('1078', '访问日志', '', '2', '1', '1083', 'api-access-log', 'log', 'infra/apiAccessLog/index',
        'InfraApiAccessLog', '0', '', '', '', '', '2021-02-26 01:32:59', '1', '2023-04-08 10:31:34', '\0');
INSERT INTO `system_menu`
VALUES ('1082', '日志导出', 'infra:api-access-log:export', '3', '2', '1078', '', '', '', null, '0', '', '', '', '',
        '2021-02-26 01:32:59', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1083', 'API 日志', '', '2', '8', '2', 'log', 'log', null, null, '0', '', '', '', '', '2021-02-26 02:18:24',
        '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1084', '错误日志', 'infra:api-error-log:query', '2', '2', '1083', 'api-error-log', 'log',
        'infra/apiErrorLog/index', 'InfraApiErrorLog', '0', '', '', '', '', '2021-02-26 07:53:20', '1',
        '2023-04-08 10:32:25', '\0');
INSERT INTO `system_menu`
VALUES ('1085', '日志处理', 'infra:api-error-log:update-status', '3', '2', '1084', '', '', '', null, '0', '', '', '',
        '', '2021-02-26 07:53:20', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1086', '日志导出', 'infra:api-error-log:export', '3', '3', '1084', '', '', '', null, '0', '', '', '', '',
        '2021-02-26 07:53:20', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1087', '任务查询', 'infra:job:query', '3', '1', '110', '', '', '', null, '0', '', '', '', '1',
        '2021-03-10 01:26:19', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1088', '日志查询', 'infra:api-access-log:query', '3', '1', '1078', '', '', '', null, '0', '', '', '', '1',
        '2021-03-10 01:28:04', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1089', '日志查询', 'infra:api-error-log:query', '3', '1', '1084', '', '', '', null, '0', '', '', '', '1',
        '2021-03-10 01:29:09', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1090', '文件列表', '', '2', '5', '1243', 'file', 'upload', 'infra/file/index', 'InfraFile', '0', '', '', '',
        '', '2021-03-12 20:16:20', '1', '2023-04-08 09:21:31', '\0');
INSERT INTO `system_menu`
VALUES ('1091', '文件查询', 'infra:file:query', '3', '1', '1090', '', '', '', null, '0', '', '', '', '',
        '2021-03-12 20:16:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1092', '文件删除', 'infra:file:delete', '3', '4', '1090', '', '', '', null, '0', '', '', '', '',
        '2021-03-12 20:16:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1093', '短信管理', '', '1', '11', '1', 'sms', 'validCode', null, null, '0', '', '', '', '1',
        '2021-04-05 01:10:16', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1094', '短信渠道', '', '2', '0', '1093', 'sms-channel', 'phone', 'system/sms/channel/index',
        'SystemSmsChannel', '0', '', '', '', '', '2021-04-01 11:07:15', '1', '2023-04-08 08:50:41', '\0');
INSERT INTO `system_menu`
VALUES ('1095', '短信渠道查询', 'system:sms-channel:query', '3', '1', '1094', '', '', '', null, '0', '', '', '', '',
        '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1096', '短信渠道创建', 'system:sms-channel:create', '3', '2', '1094', '', '', '', null, '0', '', '', '', '',
        '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1097', '短信渠道更新', 'system:sms-channel:update', '3', '3', '1094', '', '', '', null, '0', '', '', '', '',
        '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1098', '短信渠道删除', 'system:sms-channel:delete', '3', '4', '1094', '', '', '', null, '0', '', '', '', '',
        '2021-04-01 11:07:15', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1100', '短信模板', '', '2', '1', '1093', 'sms-template', 'phone', 'system/sms/template/index',
        'SystemSmsTemplate', '0', '', '', '', '', '2021-04-01 17:35:17', '1', '2023-04-08 08:50:50', '\0');
INSERT INTO `system_menu`
VALUES ('1101', '短信模板查询', 'system:sms-template:query', '3', '1', '1100', '', '', '', null, '0', '', '', '', '',
        '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1102', '短信模板创建', 'system:sms-template:create', '3', '2', '1100', '', '', '', null, '0', '', '', '',
        '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1103', '短信模板更新', 'system:sms-template:update', '3', '3', '1100', '', '', '', null, '0', '', '', '',
        '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1104', '短信模板删除', 'system:sms-template:delete', '3', '4', '1100', '', '', '', null, '0', '', '', '',
        '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1105', '短信模板导出', 'system:sms-template:export', '3', '5', '1100', '', '', '', null, '0', '', '', '',
        '', '2021-04-01 17:35:17', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1106', '发送测试短信', 'system:sms-template:send-sms', '3', '6', '1100', '', '', '', null, '0', '', '', '',
        '1', '2021-04-11 00:26:40', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1107', '短信日志', '', '2', '2', '1093', 'sms-log', 'phone', 'system/sms/log/index', 'SystemSmsLog', '0', '',
        '', '', '', '2021-04-11 08:37:05', '1', '2023-04-08 08:50:58', '\0');
INSERT INTO `system_menu`
VALUES ('1108', '短信日志查询', 'system:sms-log:query', '3', '1', '1107', '', '', '', null, '0', '', '', '', '',
        '2021-04-11 08:37:05', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1109', '短信日志导出', 'system:sms-log:export', '3', '5', '1107', '', '', '', null, '0', '', '', '', '',
        '2021-04-11 08:37:05', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1110', '错误码管理', '', '2', '12', '1', 'error-code', 'code', 'system/errorCode/index', 'SystemErrorCode',
        '0', '', '', '', '', '2021-04-13 21:46:42', '1', '2023-04-08 09:01:15', '\0');
INSERT INTO `system_menu`
VALUES ('1111', '错误码查询', 'system:error-code:query', '3', '1', '1110', '', '', '', null, '0', '', '', '', '',
        '2021-04-13 21:46:42', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1112', '错误码创建', 'system:error-code:create', '3', '2', '1110', '', '', '', null, '0', '', '', '', '',
        '2021-04-13 21:46:42', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1113', '错误码更新', 'system:error-code:update', '3', '3', '1110', '', '', '', null, '0', '', '', '', '',
        '2021-04-13 21:46:42', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1114', '错误码删除', 'system:error-code:delete', '3', '4', '1110', '', '', '', null, '0', '', '', '', '',
        '2021-04-13 21:46:42', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1115', '错误码导出', 'system:error-code:export', '3', '5', '1110', '', '', '', null, '0', '', '', '', '',
        '2021-04-13 21:46:42', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1117', '支付管理', '', '1', '30', '0', '/pay', 'money', null, null, '0', '', '', '', '1',
        '2021-12-25 16:43:41', '1', '2022-12-10 16:33:19', '\0');
INSERT INTO `system_menu`
VALUES ('1118', '请假查询', '', '2', '0', '5', 'leave', 'user', 'bpm/oa/leave/index', 'BpmOALeave', '0', '', '', '',
        '', '2021-09-20 08:51:03', '1', '2023-04-08 11:30:40', '\0');
INSERT INTO `system_menu`
VALUES ('1119', '请假申请查询', 'bpm:oa-leave:query', '3', '1', '1118', '', '', '', null, '0', '', '', '', '',
        '2021-09-20 08:51:03', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1120', '请假申请创建', 'bpm:oa-leave:create', '3', '2', '1118', '', '', '', null, '0', '', '', '', '',
        '2021-09-20 08:51:03', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1126', '应用信息', '', '2', '1', '1117', 'app', 'table', 'pay/app/index', 'PayApp', '0', '', '', '', '',
        '2021-11-10 01:13:30', '1', '2023-07-20 12:13:32', '\0');
INSERT INTO `system_menu`
VALUES ('1127', '支付应用信息查询', 'pay:app:query', '3', '1', '1126', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1128', '支付应用信息创建', 'pay:app:create', '3', '2', '1126', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1129', '支付应用信息更新', 'pay:app:update', '3', '3', '1126', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1130', '支付应用信息删除', 'pay:app:delete', '3', '4', '1126', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:31', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1132', '秘钥解析', 'pay:channel:parsing', '3', '6', '1129', '', '', '', null, '0', '', '', '', '1',
        '2021-11-08 15:15:47', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1133', '支付商户信息查询', 'pay:merchant:query', '3', '1', '1132', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:41', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1134', '支付商户信息创建', 'pay:merchant:create', '3', '2', '1132', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:41', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1135', '支付商户信息更新', 'pay:merchant:update', '3', '3', '1132', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:41', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1136', '支付商户信息删除', 'pay:merchant:delete', '3', '4', '1132', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:41', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1137', '支付商户信息导出', 'pay:merchant:export', '3', '5', '1132', '', '', '', null, '0', '', '', '', '',
        '2021-11-10 01:13:41', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1138', '租户列表', '', '2', '0', '1224', 'list', 'peoples', 'system/tenant/index', 'SystemTenant', '0', '',
        '', '', '', '2021-12-14 12:31:43', '1', '2023-04-08 08:29:08', '\0');
INSERT INTO `system_menu`
VALUES ('1139', '租户查询', 'system:tenant:query', '3', '1', '1138', '', '', '', null, '0', '', '', '', '',
        '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1140', '租户创建', 'system:tenant:create', '3', '2', '1138', '', '', '', null, '0', '', '', '', '',
        '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1141', '租户更新', 'system:tenant:update', '3', '3', '1138', '', '', '', null, '0', '', '', '', '',
        '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1142', '租户删除', 'system:tenant:delete', '3', '4', '1138', '', '', '', null, '0', '', '', '', '',
        '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1143', '租户导出', 'system:tenant:export', '3', '5', '1138', '', '', '', null, '0', '', '', '', '',
        '2021-12-14 12:31:44', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1150', '秘钥解析', '', '3', '6', '1129', '', '', '', null, '0', '', '', '', '1', '2021-11-08 15:15:47', '1',
        '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1161', '退款订单', '', '2', '3', '1117', 'refund', 'order', 'pay/refund/index', 'PayRefund', '0', '', '',
        '', '', '2021-12-25 08:29:07', '1', '2023-04-08 10:46:02', '\0');
INSERT INTO `system_menu`
VALUES ('1162', '退款订单查询', 'pay:refund:query', '3', '1', '1161', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:29:07', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1163', '退款订单创建', 'pay:refund:create', '3', '2', '1161', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:29:07', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1164', '退款订单更新', 'pay:refund:update', '3', '3', '1161', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:29:07', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1165', '退款订单删除', 'pay:refund:delete', '3', '4', '1161', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:29:07', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1166', '退款订单导出', 'pay:refund:export', '3', '5', '1161', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:29:07', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1173', '支付订单', '', '2', '2', '1117', 'order', 'pay', 'pay/order/index', 'PayOrder', '0', '', '', '', '',
        '2021-12-25 08:49:43', '1', '2023-04-08 10:43:37', '\0');
INSERT INTO `system_menu`
VALUES ('1174', '支付订单查询', 'pay:order:query', '3', '1', '1173', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:49:43', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1175', '支付订单创建', 'pay:order:create', '3', '2', '1173', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:49:43', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1176', '支付订单更新', 'pay:order:update', '3', '3', '1173', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:49:43', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1177', '支付订单删除', 'pay:order:delete', '3', '4', '1173', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:49:43', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1178', '支付订单导出', 'pay:order:export', '3', '5', '1173', '', '', '', null, '0', '', '', '', '',
        '2021-12-25 08:49:43', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1185', '工作流程', '', '1', '50', '0', '/bpm', 'tool', null, null, '0', '', '', '', '1',
        '2021-12-30 20:26:36', '103', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1186', '流程管理', '', '1', '10', '1185', 'manager', 'nested', null, null, '0', '', '', '', '1',
        '2021-12-30 20:28:30', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1187', '流程表单', '', '2', '0', '1186', 'form', 'form', 'bpm/form/index', 'BpmForm', '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2023-04-08 10:50:37', '\0');
INSERT INTO `system_menu`
VALUES ('1188', '表单查询', 'bpm:form:query', '3', '1', '1187', '', '', '', null, '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1189', '表单创建', 'bpm:form:create', '3', '2', '1187', '', '', '', null, '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1190', '表单更新', 'bpm:form:update', '3', '3', '1187', '', '', '', null, '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1191', '表单删除', 'bpm:form:delete', '3', '4', '1187', '', '', '', null, '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1192', '表单导出', 'bpm:form:export', '3', '5', '1187', '', '', '', null, '0', '', '', '', '',
        '2021-12-30 12:38:22', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1193', '流程模型', '', '2', '5', '1186', 'model', 'guide', 'bpm/model/index', 'BpmModel', '0', '', '', '',
        '1', '2021-12-31 23:24:58', '1', '2023-04-08 10:53:38', '\0');
INSERT INTO `system_menu`
VALUES ('1194', '模型查询', 'bpm:model:query', '3', '1', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:01:10', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1195', '模型创建', 'bpm:model:create', '3', '2', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:01:24', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1196', '模型导入', 'bpm:model:import', '3', '3', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:01:35', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1197', '模型更新', 'bpm:model:update', '3', '4', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:02:28', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1198', '模型删除', 'bpm:model:delete', '3', '5', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:02:43', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1199', '模型发布', 'bpm:model:deploy', '3', '6', '1193', '', '', '', null, '0', '', '', '', '1',
        '2022-01-03 19:03:24', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1200', '任务管理', '', '1', '20', '1185', 'task', 'cascader', null, null, '0', '', '', '', '1',
        '2022-01-07 23:51:48', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1201', '我的流程', '', '2', '0', '1200', 'my', 'people', 'bpm/processInstance/index', 'BpmProcessInstance',
        '0', '', '', '', '', '2022-01-07 15:53:44', '1', '2023-04-08 11:16:55', '\0');
INSERT INTO `system_menu`
VALUES ('1202', '流程实例的查询', 'bpm:process-instance:query', '3', '1', '1201', '', '', '', null, '0', '', '', '',
        '', '2022-01-07 15:53:44', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1207', '待办任务', '', '2', '10', '1200', 'todo', 'eye-open', 'bpm/task/todo/index', 'BpmTodoTask', '0', '',
        '', '', '1', '2022-01-08 10:33:37', '1', '2023-04-08 11:29:08', '\0');
INSERT INTO `system_menu`
VALUES ('1208', '已办任务', '', '2', '20', '1200', 'done', 'eye', 'bpm/task/done/index', 'BpmDoneTask', '0', '', '',
        '', '1', '2022-01-08 10:34:13', '1', '2023-04-08 11:29:00', '\0');
INSERT INTO `system_menu`
VALUES ('1209', '用户分组', '', '2', '2', '1186', 'user-group', 'people', 'bpm/group/index', 'BpmUserGroup', '0', '',
        '', '', '', '2022-01-14 02:14:20', '1', '2023-04-08 10:51:06', '\0');
INSERT INTO `system_menu`
VALUES ('1210', '用户组查询', 'bpm:user-group:query', '3', '1', '1209', '', '', '', null, '0', '', '', '', '',
        '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1211', '用户组创建', 'bpm:user-group:create', '3', '2', '1209', '', '', '', null, '0', '', '', '', '',
        '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1212', '用户组更新', 'bpm:user-group:update', '3', '3', '1209', '', '', '', null, '0', '', '', '', '',
        '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1213', '用户组删除', 'bpm:user-group:delete', '3', '4', '1209', '', '', '', null, '0', '', '', '', '',
        '2022-01-14 02:14:20', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1215', '流程定义查询', 'bpm:process-definition:query', '3', '10', '1193', '', '', '', null, '0', '', '', '',
        '1', '2022-01-23 00:21:43', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1216', '流程任务分配规则查询', 'bpm:task-assign-rule:query', '3', '20', '1193', '', '', '', null, '0', '',
        '', '', '1', '2022-01-23 00:26:53', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1217', '流程任务分配规则创建', 'bpm:task-assign-rule:create', '3', '21', '1193', '', '', '', null, '0', '',
        '', '', '1', '2022-01-23 00:28:15', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1218', '流程任务分配规则更新', 'bpm:task-assign-rule:update', '3', '22', '1193', '', '', '', null, '0', '',
        '', '', '1', '2022-01-23 00:28:41', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1219', '流程实例的创建', 'bpm:process-instance:create', '3', '2', '1201', '', '', '', null, '0', '', '', '',
        '1', '2022-01-23 00:36:15', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1220', '流程实例的取消', 'bpm:process-instance:cancel', '3', '3', '1201', '', '', '', null, '0', '', '', '',
        '1', '2022-01-23 00:36:33', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1221', '流程任务的查询', 'bpm:task:query', '3', '1', '1207', '', '', '', null, '0', '', '', '', '1',
        '2022-01-23 00:38:52', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1222', '流程任务的更新', 'bpm:task:update', '3', '2', '1207', '', '', '', null, '0', '', '', '', '1',
        '2022-01-23 00:39:24', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1224', '租户管理', '', '2', '0', '1', 'tenant', 'peoples', null, null, '0', '', '', '', '1',
        '2022-02-20 01:41:13', '1', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1225', '租户套餐', '', '2', '0', '1224', 'package', 'eye', 'system/tenantPackage/index', 'SystemTenantPackage',
        '0', '', '', '', '', '2022-02-19 17:44:06', '1', '2023-04-08 08:17:08', '\0');
INSERT INTO `system_menu`
VALUES ('1226', '租户套餐查询', 'system:tenant-package:query', '3', '1', '1225', '', '', '', null, '0', '', '', '',
        '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1227', '租户套餐创建', 'system:tenant-package:create', '3', '2', '1225', '', '', '', null, '0', '', '', '',
        '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1228', '租户套餐更新', 'system:tenant-package:update', '3', '3', '1225', '', '', '', null, '0', '', '', '',
        '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1229', '租户套餐删除', 'system:tenant-package:delete', '3', '4', '1225', '', '', '', null, '0', '', '', '',
        '', '2022-02-19 17:44:06', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1237', '文件配置', '', '2', '0', '1243', 'file-config', 'config', 'infra/fileConfig/index', 'InfraFileConfig',
        '0', '', '', '', '', '2022-03-15 14:35:28', '1', '2023-04-08 09:16:05', '\0');
INSERT INTO `system_menu`
VALUES ('1238', '文件配置查询', 'infra:file-config:query', '3', '1', '1237', '', '', '', null, '0', '', '', '', '',
        '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1239', '文件配置创建', 'infra:file-config:create', '3', '2', '1237', '', '', '', null, '0', '', '', '', '',
        '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1240', '文件配置更新', 'infra:file-config:update', '3', '3', '1237', '', '', '', null, '0', '', '', '', '',
        '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1241', '文件配置删除', 'infra:file-config:delete', '3', '4', '1237', '', '', '', null, '0', '', '', '', '',
        '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1242', '文件配置导出', 'infra:file-config:export', '3', '5', '1237', '', '', '', null, '0', '', '', '', '',
        '2022-03-15 14:35:28', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1243', '文件管理', '', '2', '5', '2', 'file', 'download', null, '', '0', '', '', '', '1',
        '2022-03-16 23:47:40', '1', '2023-02-10 13:47:46', '\0');
INSERT INTO `system_menu`
VALUES ('1247', '敏感词管理', '', '2', '13', '1', 'sensitive-word', 'education', 'system/sensitiveWord/index',
        'SystemSensitiveWord', '0', '', '', '', '', '2022-04-07 16:55:03', '1', '2023-04-08 09:00:40', '\0');
INSERT INTO `system_menu`
VALUES ('1248', '敏感词查询', 'system:sensitive-word:query', '3', '1', '1247', '', '', '', null, '0', '', '', '', '',
        '2022-04-07 16:55:03', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1249', '敏感词创建', 'system:sensitive-word:create', '3', '2', '1247', '', '', '', null, '0', '', '', '',
        '', '2022-04-07 16:55:03', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1250', '敏感词更新', 'system:sensitive-word:update', '3', '3', '1247', '', '', '', null, '0', '', '', '',
        '', '2022-04-07 16:55:03', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1251', '敏感词删除', 'system:sensitive-word:delete', '3', '4', '1247', '', '', '', null, '0', '', '', '',
        '', '2022-04-07 16:55:03', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1252', '敏感词导出', 'system:sensitive-word:export', '3', '5', '1247', '', '', '', null, '0', '', '', '',
        '', '2022-04-07 16:55:03', '', '2022-04-20 17:03:10', '\0');
INSERT INTO `system_menu`
VALUES ('1254', '作者动态', '', '1', '0', '0', 'https://www.iocoder.cn', 'people', null, null, '0', '', '', '', '1',
        '2022-04-23 01:03:15', '1', '2023-02-10 00:06:52', '\0');
INSERT INTO `system_menu`
VALUES ('1255', '数据源配置', '', '2', '1', '2', 'data-source-config', 'rate', 'infra/dataSourceConfig/index',
        'InfraDataSourceConfig', '0', '', '', '', '', '2022-04-27 14:37:32', '1', '2023-04-08 09:05:21', '\0');
INSERT INTO `system_menu`
VALUES ('1256', '数据源配置查询', 'infra:data-source-config:query', '3', '1', '1255', '', '', '', null, '0', '', '',
        '', '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32', '\0');
INSERT INTO `system_menu`
VALUES ('1257', '数据源配置创建', 'infra:data-source-config:create', '3', '2', '1255', '', '', '', null, '0', '', '',
        '', '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32', '\0');
INSERT INTO `system_menu`
VALUES ('1258', '数据源配置更新', 'infra:data-source-config:update', '3', '3', '1255', '', '', '', null, '0', '', '',
        '', '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32', '\0');
INSERT INTO `system_menu`
VALUES ('1259', '数据源配置删除', 'infra:data-source-config:delete', '3', '4', '1255', '', '', '', null, '0', '', '',
        '', '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32', '\0');
INSERT INTO `system_menu`
VALUES ('1260', '数据源配置导出', 'infra:data-source-config:export', '3', '5', '1255', '', '', '', null, '0', '', '',
        '', '', '2022-04-27 14:37:32', '', '2022-04-27 14:37:32', '\0');
INSERT INTO `system_menu`
VALUES ('1261', 'OAuth 2.0', '', '1', '10', '1', 'oauth2', 'people', null, null, '0', '', '', '', '1',
        '2022-05-09 23:38:17', '1', '2022-05-11 23:51:46', '\0');
INSERT INTO `system_menu`
VALUES ('1263', '应用管理', '', '2', '0', '1261', 'oauth2/application', 'tool', 'system/oauth2/client/index',
        'SystemOAuth2Client', '0', '', '', '', '', '2022-05-10 16:26:33', '1', '2023-04-08 08:47:31', '\0');
INSERT INTO `system_menu`
VALUES ('1264', '客户端查询', 'system:oauth2-client:query', '3', '1', '1263', '', '', '', null, '0', '', '', '', '',
        '2022-05-10 16:26:33', '1', '2022-05-11 00:31:06', '\0');
INSERT INTO `system_menu`
VALUES ('1265', '客户端创建', 'system:oauth2-client:create', '3', '2', '1263', '', '', '', null, '0', '', '', '', '',
        '2022-05-10 16:26:33', '1', '2022-05-11 00:31:23', '\0');
INSERT INTO `system_menu`
VALUES ('1266', '客户端更新', 'system:oauth2-client:update', '3', '3', '1263', '', '', '', null, '0', '', '', '', '',
        '2022-05-10 16:26:33', '1', '2022-05-11 00:31:28', '\0');
INSERT INTO `system_menu`
VALUES ('1267', '客户端删除', 'system:oauth2-client:delete', '3', '4', '1263', '', '', '', null, '0', '', '', '', '',
        '2022-05-10 16:26:33', '1', '2022-05-11 00:31:33', '\0');
INSERT INTO `system_menu`
VALUES ('1281', '报表管理', '', '1', '40', '0', '/report', 'chart', null, null, '0', '', '', '', '1',
        '2022-07-10 20:22:15', '1', '2023-02-07 17:16:40', '\0');
INSERT INTO `system_menu`
VALUES ('1282', '报表设计器', '', '2', '1', '1281', 'jimu-report', 'example', 'report/jmreport/index', 'GoView', '0',
        '', '', '', '1', '2022-07-10 20:26:36', '1', '2023-04-08 10:47:59', '\0');
INSERT INTO `system_menu`
VALUES ('2000', '商品中心', '', '1', '60', '2362', 'product', 'fa:product-hunt', null, null, '0', '', '', '', '',
        '2022-07-29 15:53:53', '1', '2023-09-30 11:52:36', '\0');
INSERT INTO `system_menu`
VALUES ('2002', '商品分类', '', '2', '2', '2000', 'category', 'ep:cellphone', 'mall/product/category/index',
        'ProductCategory', '0', '', '', '', '', '2022-07-29 15:53:53', '1', '2023-08-21 10:27:15', '\0');
INSERT INTO `system_menu`
VALUES ('2003', '分类查询', 'product:category:query', '3', '1', '2002', '', '', '', null, '0', '', '', '', '',
        '2022-07-29 15:53:53', '', '2022-07-29 15:53:53', '\0');
INSERT INTO `system_menu`
VALUES ('2004', '分类创建', 'product:category:create', '3', '2', '2002', '', '', '', null, '0', '', '', '', '',
        '2022-07-29 15:53:53', '', '2022-07-29 15:53:53', '\0');
INSERT INTO `system_menu`
VALUES ('2005', '分类更新', 'product:category:update', '3', '3', '2002', '', '', '', null, '0', '', '', '', '',
        '2022-07-29 15:53:53', '', '2022-07-29 15:53:53', '\0');
INSERT INTO `system_menu`
VALUES ('2006', '分类删除', 'product:category:delete', '3', '4', '2002', '', '', '', null, '0', '', '', '', '',
        '2022-07-29 15:53:53', '', '2022-07-29 15:53:53', '\0');
INSERT INTO `system_menu`
VALUES ('2008', '商品品牌', '', '2', '3', '2000', 'brand', 'ep:chicken', 'mall/product/brand/index', 'ProductBrand',
        '0', '', '', '', '', '2022-07-30 13:52:44', '1', '2023-08-21 10:27:28', '\0');
INSERT INTO `system_menu`
VALUES ('2009', '品牌查询', 'product:brand:query', '3', '1', '2008', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 13:52:44', '', '2022-07-30 13:52:44', '\0');
INSERT INTO `system_menu`
VALUES ('2010', '品牌创建', 'product:brand:create', '3', '2', '2008', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 13:52:44', '', '2022-07-30 13:52:44', '\0');
INSERT INTO `system_menu`
VALUES ('2011', '品牌更新', 'product:brand:update', '3', '3', '2008', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 13:52:44', '', '2022-07-30 13:52:44', '\0');
INSERT INTO `system_menu`
VALUES ('2012', '品牌删除', 'product:brand:delete', '3', '4', '2008', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 13:52:44', '', '2022-07-30 13:52:44', '\0');
INSERT INTO `system_menu`
VALUES ('2014', '商品列表', '', '2', '1', '2000', 'spu', 'ep:apple', 'mall/product/spu/index', 'ProductSpu', '0', '',
        '', '', '', '2022-07-30 14:22:58', '1', '2023-08-21 10:27:01', '\0');
INSERT INTO `system_menu`
VALUES ('2015', '商品查询', 'product:spu:query', '3', '1', '2014', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 14:22:58', '', '2022-07-30 14:22:58', '\0');
INSERT INTO `system_menu`
VALUES ('2016', '商品创建', 'product:spu:create', '3', '2', '2014', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 14:22:58', '', '2022-07-30 14:22:58', '\0');
INSERT INTO `system_menu`
VALUES ('2017', '商品更新', 'product:spu:update', '3', '3', '2014', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 14:22:58', '', '2022-07-30 14:22:58', '\0');
INSERT INTO `system_menu`
VALUES ('2018', '商品删除', 'product:spu:delete', '3', '4', '2014', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 14:22:58', '', '2022-07-30 14:22:58', '\0');
INSERT INTO `system_menu`
VALUES ('2019', '商品属性', '', '2', '4', '2000', 'property', 'ep:cold-drink', 'mall/product/property/index',
        'ProductProperty', '0', '', '', '', '', '2022-08-01 14:55:35', '1', '2023-08-26 11:01:05', '\0');
INSERT INTO `system_menu`
VALUES ('2020', '规格查询', 'product:property:query', '3', '1', '2019', '', '', '', null, '0', '', '', '', '',
        '2022-08-01 14:55:35', '', '2022-12-12 20:26:24', '\0');
INSERT INTO `system_menu`
VALUES ('2021', '规格创建', 'product:property:create', '3', '2', '2019', '', '', '', null, '0', '', '', '', '',
        '2022-08-01 14:55:35', '', '2022-12-12 20:26:30', '\0');
INSERT INTO `system_menu`
VALUES ('2022', '规格更新', 'product:property:update', '3', '3', '2019', '', '', '', null, '0', '', '', '', '',
        '2022-08-01 14:55:35', '', '2022-12-12 20:26:33', '\0');
INSERT INTO `system_menu`
VALUES ('2023', '规格删除', 'product:property:delete', '3', '4', '2019', '', '', '', null, '0', '', '', '', '',
        '2022-08-01 14:55:35', '', '2022-12-12 20:26:37', '\0');
INSERT INTO `system_menu`
VALUES ('2025', 'Banner', '', '2', '100', '2387', 'banner', 'fa:bandcamp', 'mall/promotion/banner/index', null, '0',
        '', '', '', '', '2022-08-01 14:56:14', '1', '2023-10-24 20:20:06', '\0');
INSERT INTO `system_menu`
VALUES ('2026', 'Banner查询', 'promotion:banner:query', '3', '1', '2025', '', '', '', '', '0', '', '', '', '',
        '2022-08-01 14:56:14', '1', '2023-10-24 20:20:18', '\0');
INSERT INTO `system_menu`
VALUES ('2027', 'Banner创建', 'promotion:banner:create', '3', '2', '2025', '', '', '', '', '0', '', '', '', '',
        '2022-08-01 14:56:14', '1', '2023-10-24 20:20:23', '\0');
INSERT INTO `system_menu`
VALUES ('2028', 'Banner更新', 'promotion:banner:update', '3', '3', '2025', '', '', '', '', '0', '', '', '', '',
        '2022-08-01 14:56:14', '1', '2023-10-24 20:20:28', '\0');
INSERT INTO `system_menu`
VALUES ('2029', 'Banner删除', 'promotion:banner:delete', '3', '4', '2025', '', '', '', '', '0', '', '', '', '',
        '2022-08-01 14:56:14', '1', '2023-10-24 20:20:36', '\0');
INSERT INTO `system_menu`
VALUES ('2030', '营销中心', '', '1', '70', '2362', 'promotion', 'ep:present', null, null, '0', '', '', '', '1',
        '2022-10-31 21:25:09', '1', '2023-09-30 11:54:27', '\0');
INSERT INTO `system_menu`
VALUES ('2032', '优惠劵列表', '', '2', '1', '2365', 'template', 'ep:discount', 'mall/promotion/coupon/template/index',
        'PromotionCouponTemplate', '0', '', '', '', '', '2022-10-31 22:27:14', '1', '2023-10-03 12:40:06', '\0');
INSERT INTO `system_menu`
VALUES ('2033', '优惠劵模板查询', 'promotion:coupon-template:query', '3', '1', '2032', '', '', '', null, '0', '', '',
        '', '', '2022-10-31 22:27:14', '', '2022-10-31 22:27:14', '\0');
INSERT INTO `system_menu`
VALUES ('2034', '优惠劵模板创建', 'promotion:coupon-template:create', '3', '2', '2032', '', '', '', null, '0', '', '',
        '', '', '2022-10-31 22:27:14', '', '2022-10-31 22:27:14', '\0');
INSERT INTO `system_menu`
VALUES ('2035', '优惠劵模板更新', 'promotion:coupon-template:update', '3', '3', '2032', '', '', '', null, '0', '', '',
        '', '', '2022-10-31 22:27:14', '', '2022-10-31 22:27:14', '\0');
INSERT INTO `system_menu`
VALUES ('2036', '优惠劵模板删除', 'promotion:coupon-template:delete', '3', '4', '2032', '', '', '', null, '0', '', '',
        '', '', '2022-10-31 22:27:14', '', '2022-10-31 22:27:14', '\0');
INSERT INTO `system_menu`
VALUES ('2038', '领取记录', '', '2', '2', '2365', 'list', 'ep:collection-tag', 'mall/promotion/coupon/index',
        'PromotionCoupon', '0', '', '', '', '', '2022-11-03 23:21:31', '1', '2023-10-03 12:55:30', '\0');
INSERT INTO `system_menu`
VALUES ('2039', '优惠劵查询', 'promotion:coupon:query', '3', '1', '2038', '', '', '', null, '0', '', '', '', '',
        '2022-11-03 23:21:31', '', '2022-11-03 23:21:31', '\0');
INSERT INTO `system_menu`
VALUES ('2040', '优惠劵删除', 'promotion:coupon:delete', '3', '4', '2038', '', '', '', null, '0', '', '', '', '',
        '2022-11-03 23:21:31', '', '2022-11-03 23:21:31', '\0');
INSERT INTO `system_menu`
VALUES ('2041', '满减送', '', '2', '10', '2390', 'reward-activity', 'ep:goblet-square-full',
        'mall/promotion/rewardActivity/index', 'PromotionRewardActivity', '0', '', '', '', '', '2022-11-04 23:47:49',
        '1', '2023-10-21 19:24:46', '\0');
INSERT INTO `system_menu`
VALUES ('2042', '满减送活动查询', 'promotion:reward-activity:query', '3', '1', '2041', '', '', '', null, '0', '', '',
        '', '', '2022-11-04 23:47:49', '', '2022-11-04 23:47:49', '\0');
INSERT INTO `system_menu`
VALUES ('2043', '满减送活动创建', 'promotion:reward-activity:create', '3', '2', '2041', '', '', '', null, '0', '', '',
        '', '', '2022-11-04 23:47:49', '', '2022-11-04 23:47:49', '\0');
INSERT INTO `system_menu`
VALUES ('2044', '满减送活动更新', 'promotion:reward-activity:update', '3', '3', '2041', '', '', '', null, '0', '', '',
        '', '', '2022-11-04 23:47:50', '', '2022-11-04 23:47:50', '\0');
INSERT INTO `system_menu`
VALUES ('2045', '满减送活动删除', 'promotion:reward-activity:delete', '3', '4', '2041', '', '', '', null, '0', '', '',
        '', '', '2022-11-04 23:47:50', '', '2022-11-04 23:47:50', '\0');
INSERT INTO `system_menu`
VALUES ('2046', '满减送活动关闭', 'promotion:reward-activity:close', '3', '5', '2041', '', '', '', null, '0', '', '',
        '', '1', '2022-11-05 10:42:53', '1', '2022-11-05 10:42:53', '\0');
INSERT INTO `system_menu`
VALUES ('2047', '限时折扣', '', '2', '7', '2390', 'discount-activity', 'ep:timer',
        'mall/promotion/discountActivity/index', 'PromotionDiscountActivity', '0', '', '', '', '',
        '2022-11-05 17:12:15', '1', '2023-10-21 19:24:21', '\0');
INSERT INTO `system_menu`
VALUES ('2048', '限时折扣活动查询', 'promotion:discount-activity:query', '3', '1', '2047', '', '', '', null, '0', '',
        '', '', '', '2022-11-05 17:12:15', '', '2022-11-05 17:12:15', '\0');
INSERT INTO `system_menu`
VALUES ('2049', '限时折扣活动创建', 'promotion:discount-activity:create', '3', '2', '2047', '', '', '', null, '0', '',
        '', '', '', '2022-11-05 17:12:15', '', '2022-11-05 17:12:15', '\0');
INSERT INTO `system_menu`
VALUES ('2050', '限时折扣活动更新', 'promotion:discount-activity:update', '3', '3', '2047', '', '', '', null, '0', '',
        '', '', '', '2022-11-05 17:12:16', '', '2022-11-05 17:12:16', '\0');
INSERT INTO `system_menu`
VALUES ('2051', '限时折扣活动删除', 'promotion:discount-activity:delete', '3', '4', '2047', '', '', '', null, '0', '',
        '', '', '', '2022-11-05 17:12:16', '', '2022-11-05 17:12:16', '\0');
INSERT INTO `system_menu`
VALUES ('2052', '限时折扣活动关闭', 'promotion:discount-activity:close', '3', '5', '2047', '', '', '', null, '0', '',
        '', '', '', '2022-11-05 17:12:16', '', '2022-11-05 17:12:16', '\0');
INSERT INTO `system_menu`
VALUES ('2059', '秒杀商品', '', '2', '2', '2209', 'activity', 'ep:basketball', 'mall/promotion/seckill/activity/index',
        'PromotionSeckillActivity', '0', '', '', '', '', '2022-11-06 22:24:49', '1', '2023-06-24 18:57:25', '\0');
INSERT INTO `system_menu`
VALUES ('2060', '秒杀活动查询', 'promotion:seckill-activity:query', '3', '1', '2059', '', '', '', null, '0', '', '',
        '', '', '2022-11-06 22:24:49', '', '2022-11-06 22:24:49', '\0');
INSERT INTO `system_menu`
VALUES ('2061', '秒杀活动创建', 'promotion:seckill-activity:create', '3', '2', '2059', '', '', '', null, '0', '', '',
        '', '', '2022-11-06 22:24:49', '', '2022-11-06 22:24:49', '\0');
INSERT INTO `system_menu`
VALUES ('2062', '秒杀活动更新', 'promotion:seckill-activity:update', '3', '3', '2059', '', '', '', null, '0', '', '',
        '', '', '2022-11-06 22:24:49', '', '2022-11-06 22:24:49', '\0');
INSERT INTO `system_menu`
VALUES ('2063', '秒杀活动删除', 'promotion:seckill-activity:delete', '3', '4', '2059', '', '', '', null, '0', '', '',
        '', '', '2022-11-06 22:24:49', '', '2022-11-06 22:24:49', '\0');
INSERT INTO `system_menu`
VALUES ('2066', '秒杀时段', '', '2', '1', '2209', 'config', 'ep:baseball', 'mall/promotion/seckill/config/index',
        'PromotionSeckillConfig', '0', '', '', '', '', '2022-11-15 19:46:50', '1', '2023-06-24 18:57:14', '\0');
INSERT INTO `system_menu`
VALUES ('2067', '秒杀时段查询', 'promotion:seckill-config:query', '3', '1', '2066', '', '', '', '', '0', '', '', '',
        '', '2022-11-15 19:46:51', '1', '2023-06-24 17:50:25', '\0');
INSERT INTO `system_menu`
VALUES ('2068', '秒杀时段创建', 'promotion:seckill-config:create', '3', '2', '2066', '', '', '', '', '0', '', '', '',
        '', '2022-11-15 19:46:51', '1', '2023-06-24 17:48:39', '\0');
INSERT INTO `system_menu`
VALUES ('2069', '秒杀时段更新', 'promotion:seckill-config:update', '3', '3', '2066', '', '', '', '', '0', '', '', '',
        '', '2022-11-15 19:46:51', '1', '2023-06-24 17:50:29', '\0');
INSERT INTO `system_menu`
VALUES ('2070', '秒杀时段删除', 'promotion:seckill-config:delete', '3', '4', '2066', '', '', '', '', '0', '', '', '',
        '', '2022-11-15 19:46:51', '1', '2023-06-24 17:50:32', '\0');
INSERT INTO `system_menu`
VALUES ('2072', '订单中心', '', '1', '65', '2362', 'trade', 'ep:eleme', null, null, '0', '', '', '', '1',
        '2022-11-19 18:57:19', '1', '2023-09-30 11:54:07', '\0');
INSERT INTO `system_menu`
VALUES ('2073', '售后退款', '', '2', '2', '2072', 'after-sale', 'ep:refrigerator', 'mall/trade/afterSale/index',
        'TradeAfterSale', '0', '', '', '', '', '2022-11-19 20:15:32', '1', '2023-10-01 21:42:21', '\0');
INSERT INTO `system_menu`
VALUES ('2074', '售后查询', 'trade:after-sale:query', '3', '1', '2073', '', '', '', null, '0', '', '', '', '',
        '2022-11-19 20:15:33', '1', '2022-12-10 21:04:29', '\0');
INSERT INTO `system_menu`
VALUES ('2075', '秒杀活动关闭', 'promotion:seckill-activity:close', '3', '5', '2059', '', '', '', '', '0', '', '',
        '', '1', '2022-11-28 20:20:15', '1', '2023-10-03 18:34:28', '\0');
INSERT INTO `system_menu`
VALUES ('2076', '订单列表', '', '2', '1', '2072', 'order', 'ep:list', 'mall/trade/order/index', 'TradeOrder', '0', '',
        '', '', '1', '2022-12-10 21:05:44', '1', '2023-10-01 21:42:08', '\0');
INSERT INTO `system_menu`
VALUES ('2083', '地区管理', '', '2', '14', '1', 'area', 'row', 'system/area/index', 'SystemArea', '0', '', '', '',
        '1', '2022-12-23 17:35:05', '1', '2023-04-08 09:01:37', '\0');
INSERT INTO `system_menu`
VALUES ('2084', '公众号管理', '', '1', '100', '0', '/mp', 'wechat', null, null, '0', '', '', '', '1',
        '2023-01-01 20:11:04', '1', '2023-01-15 11:28:57', '\0');
INSERT INTO `system_menu`
VALUES ('2085', '账号管理', '', '2', '1', '2084', 'account', 'phone', 'mp/account/index', 'MpAccount', '0', '', '',
        '', '1', '2023-01-01 20:13:31', '1', '2023-02-09 23:56:39', '\0');
INSERT INTO `system_menu`
VALUES ('2086', '新增账号', 'mp:account:create', '3', '1', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-01 20:21:40', '1', '2023-01-07 17:32:53', '\0');
INSERT INTO `system_menu`
VALUES ('2087', '修改账号', 'mp:account:update', '3', '2', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-07 17:32:46', '1', '2023-01-07 17:32:46', '\0');
INSERT INTO `system_menu`
VALUES ('2088', '查询账号', 'mp:account:query', '3', '0', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-07 17:33:07', '1', '2023-01-07 17:33:07', '\0');
INSERT INTO `system_menu`
VALUES ('2089', '删除账号', 'mp:account:delete', '3', '3', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-07 17:33:21', '1', '2023-01-07 17:33:21', '\0');
INSERT INTO `system_menu`
VALUES ('2090', '生成二维码', 'mp:account:qr-code', '3', '4', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-07 17:33:58', '1', '2023-01-07 17:33:58', '\0');
INSERT INTO `system_menu`
VALUES ('2091', '清空 API 配额', 'mp:account:clear-quota', '3', '5', '2085', '', '', '', null, '0', '', '', '', '1',
        '2023-01-07 18:20:32', '1', '2023-01-07 18:20:59', '\0');
INSERT INTO `system_menu`
VALUES ('2092', '数据统计', 'mp:statistics:query', '2', '2', '2084', 'statistics', 'chart', 'mp/statistics/index',
        'MpStatistics', '0', '', '', '', '1', '2023-01-07 20:17:36', '1', '2023-02-09 23:58:34', '\0');
INSERT INTO `system_menu`
VALUES ('2093', '标签管理', '', '2', '3', '2084', 'tag', 'rate', 'mp/tag/index', 'MpTag', '0', '', '', '', '1',
        '2023-01-08 11:37:32', '1', '2023-02-09 23:58:47', '\0');
INSERT INTO `system_menu`
VALUES ('2094', '查询标签', 'mp:tag:query', '3', '0', '2093', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 11:59:03', '1', '2023-01-08 11:59:03', '\0');
INSERT INTO `system_menu`
VALUES ('2095', '新增标签', 'mp:tag:create', '3', '1', '2093', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 11:59:23', '1', '2023-01-08 11:59:23', '\0');
INSERT INTO `system_menu`
VALUES ('2096', '修改标签', 'mp:tag:update', '3', '2', '2093', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 11:59:41', '1', '2023-01-08 11:59:41', '\0');
INSERT INTO `system_menu`
VALUES ('2097', '删除标签', 'mp:tag:delete', '3', '3', '2093', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 12:00:04', '1', '2023-01-08 12:00:13', '\0');
INSERT INTO `system_menu`
VALUES ('2098', '同步标签', 'mp:tag:sync', '3', '4', '2093', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 12:00:29', '1', '2023-01-08 12:00:29', '\0');
INSERT INTO `system_menu`
VALUES ('2099', '粉丝管理', '', '2', '4', '2084', 'user', 'people', 'mp/user/index', 'MpUser', '0', '', '', '', '1',
        '2023-01-08 16:51:20', '1', '2023-02-09 23:58:21', '\0');
INSERT INTO `system_menu`
VALUES ('2100', '查询粉丝', 'mp:user:query', '3', '0', '2099', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 17:16:59', '1', '2023-01-08 17:17:23', '\0');
INSERT INTO `system_menu`
VALUES ('2101', '修改粉丝', 'mp:user:update', '3', '1', '2099', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 17:17:11', '1', '2023-01-08 17:17:11', '\0');
INSERT INTO `system_menu`
VALUES ('2102', '同步粉丝', 'mp:user:sync', '3', '2', '2099', '', '', '', null, '0', '', '', '', '1',
        '2023-01-08 17:17:40', '1', '2023-01-08 17:17:40', '\0');
INSERT INTO `system_menu`
VALUES ('2103', '消息管理', '', '2', '5', '2084', 'message', 'email', 'mp/message/index', 'MpMessage', '0', '', '',
        '', '1', '2023-01-08 18:44:19', '1', '2023-02-09 23:58:02', '\0');
INSERT INTO `system_menu`
VALUES ('2104', '图文发表记录', '', '2', '10', '2084', 'free-publish', 'education', 'mp/freePublish/index',
        'MpFreePublish', '0', '', '', '', '1', '2023-01-13 00:30:50', '1', '2023-02-09 23:57:22', '\0');
INSERT INTO `system_menu`
VALUES ('2105', '查询发布列表', 'mp:free-publish:query', '3', '1', '2104', '', '', '', null, '0', '', '', '', '1',
        '2023-01-13 07:19:17', '1', '2023-01-13 07:19:17', '\0');
INSERT INTO `system_menu`
VALUES ('2106', '发布草稿', 'mp:free-publish:submit', '3', '2', '2104', '', '', '', null, '0', '', '', '', '1',
        '2023-01-13 07:19:46', '1', '2023-01-13 07:19:46', '\0');
INSERT INTO `system_menu`
VALUES ('2107', '删除发布记录', 'mp:free-publish:delete', '3', '3', '2104', '', '', '', null, '0', '', '', '', '1',
        '2023-01-13 07:20:01', '1', '2023-01-13 07:20:01', '\0');
INSERT INTO `system_menu`
VALUES ('2108', '图文草稿箱', '', '2', '9', '2084', 'draft', 'edit', 'mp/draft/index', 'MpDraft', '0', '', '', '',
        '1', '2023-01-13 07:40:21', '1', '2023-02-09 23:56:58', '\0');
INSERT INTO `system_menu`
VALUES ('2109', '新建草稿', 'mp:draft:create', '3', '1', '2108', '', '', '', null, '0', '', '', '', '1',
        '2023-01-13 23:15:30', '1', '2023-01-13 23:15:44', '\0');
INSERT INTO `system_menu`
VALUES ('2110', '修改草稿', 'mp:draft:update', '3', '2', '2108', '', '', '', null, '0', '', '', '', '1',
        '2023-01-14 10:08:47', '1', '2023-01-14 10:08:47', '\0');
INSERT INTO `system_menu`
VALUES ('2111', '查询草稿', 'mp:draft:query', '3', '0', '2108', '', '', '', null, '0', '', '', '', '1',
        '2023-01-14 10:09:01', '1', '2023-01-14 10:09:01', '\0');
INSERT INTO `system_menu`
VALUES ('2112', '删除草稿', 'mp:draft:delete', '3', '3', '2108', '', '', '', null, '0', '', '', '', '1',
        '2023-01-14 10:09:19', '1', '2023-01-14 10:09:19', '\0');
INSERT INTO `system_menu`
VALUES ('2113', '素材管理', '', '2', '8', '2084', 'material', 'skill', 'mp/material/index', 'MpMaterial', '0', '', '',
        '', '1', '2023-01-14 14:12:07', '1', '2023-02-09 23:57:36', '\0');
INSERT INTO `system_menu`
VALUES ('2114', '上传临时素材', 'mp:material:upload-temporary', '3', '1', '2113', '', '', '', null, '0', '', '', '',
        '1', '2023-01-14 15:33:55', '1', '2023-01-14 15:33:55', '\0');
INSERT INTO `system_menu`
VALUES ('2115', '上传永久素材', 'mp:material:upload-permanent', '3', '2', '2113', '', '', '', null, '0', '', '', '',
        '1', '2023-01-14 15:34:14', '1', '2023-01-14 15:34:14', '\0');
INSERT INTO `system_menu`
VALUES ('2116', '删除素材', 'mp:material:delete', '3', '3', '2113', '', '', '', null, '0', '', '', '', '1',
        '2023-01-14 15:35:37', '1', '2023-01-14 15:35:37', '\0');
INSERT INTO `system_menu`
VALUES ('2117', '上传图文图片', 'mp:material:upload-news-image', '3', '4', '2113', '', '', '', null, '0', '', '', '',
        '1', '2023-01-14 15:36:31', '1', '2023-01-14 15:36:31', '\0');
INSERT INTO `system_menu`
VALUES ('2118', '查询素材', 'mp:material:query', '3', '5', '2113', '', '', '', null, '0', '', '', '', '1',
        '2023-01-14 15:39:22', '1', '2023-01-14 15:39:22', '\0');
INSERT INTO `system_menu`
VALUES ('2119', '菜单管理', '', '2', '6', '2084', 'menu', 'button', 'mp/menu/index', 'MpMenu', '0', '', '', '', '1',
        '2023-01-14 17:43:54', '1', '2023-02-09 23:57:50', '\0');
INSERT INTO `system_menu`
VALUES ('2120', '自动回复', '', '2', '7', '2084', 'auto-reply', 'eye', 'mp/autoReply/index', 'MpAutoReply', '0', '',
        '', '', '1', '2023-01-15 22:13:09', '1', '2023-02-09 23:56:28', '\0');
INSERT INTO `system_menu`
VALUES ('2121', '查询回复', 'mp:auto-reply:query', '3', '0', '2120', '', '', '', null, '0', '', '', '', '1',
        '2023-01-16 22:28:41', '1', '2023-01-16 22:28:41', '\0');
INSERT INTO `system_menu`
VALUES ('2122', '新增回复', 'mp:auto-reply:create', '3', '1', '2120', '', '', '', null, '0', '', '', '', '1',
        '2023-01-16 22:28:54', '1', '2023-01-16 22:28:54', '\0');
INSERT INTO `system_menu`
VALUES ('2123', '修改回复', 'mp:auto-reply:update', '3', '2', '2120', '', '', '', null, '0', '', '', '', '1',
        '2023-01-16 22:29:05', '1', '2023-01-16 22:29:05', '\0');
INSERT INTO `system_menu`
VALUES ('2124', '删除回复', 'mp:auto-reply:delete', '3', '3', '2120', '', '', '', null, '0', '', '', '', '1',
        '2023-01-16 22:29:34', '1', '2023-01-16 22:29:34', '\0');
INSERT INTO `system_menu`
VALUES ('2125', '查询菜单', 'mp:menu:query', '3', '0', '2119', '', '', '', null, '0', '', '', '', '1',
        '2023-01-17 23:05:41', '1', '2023-01-17 23:05:41', '\0');
INSERT INTO `system_menu`
VALUES ('2126', '保存菜单', 'mp:menu:save', '3', '1', '2119', '', '', '', null, '0', '', '', '', '1',
        '2023-01-17 23:06:01', '1', '2023-01-17 23:06:01', '\0');
INSERT INTO `system_menu`
VALUES ('2127', '删除菜单', 'mp:menu:delete', '3', '2', '2119', '', '', '', null, '0', '', '', '', '1',
        '2023-01-17 23:06:16', '1', '2023-01-17 23:06:16', '\0');
INSERT INTO `system_menu`
VALUES ('2128', '查询消息', 'mp:message:query', '3', '0', '2103', '', '', '', null, '0', '', '', '', '1',
        '2023-01-17 23:07:14', '1', '2023-01-17 23:07:14', '\0');
INSERT INTO `system_menu`
VALUES ('2129', '发送消息', 'mp:message:send', '3', '1', '2103', '', '', '', null, '0', '', '', '', '1',
        '2023-01-17 23:07:26', '1', '2023-01-17 23:07:26', '\0');
INSERT INTO `system_menu`
VALUES ('2130', '邮箱管理', '', '2', '11', '1', 'mail', 'email', null, null, '0', '', '', '', '1',
        '2023-01-25 17:27:44', '1', '2023-01-25 17:27:44', '\0');
INSERT INTO `system_menu`
VALUES ('2131', '邮箱账号', '', '2', '0', '2130', 'mail-account', 'user', 'system/mail/account/index',
        'SystemMailAccount', '0', '', '', '', '', '2023-01-25 09:33:48', '1', '2023-04-08 08:53:43', '\0');
INSERT INTO `system_menu`
VALUES ('2132', '账号查询', 'system:mail-account:query', '3', '1', '2131', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', '\0');
INSERT INTO `system_menu`
VALUES ('2133', '账号创建', 'system:mail-account:create', '3', '2', '2131', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', '\0');
INSERT INTO `system_menu`
VALUES ('2134', '账号更新', 'system:mail-account:update', '3', '3', '2131', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', '\0');
INSERT INTO `system_menu`
VALUES ('2135', '账号删除', 'system:mail-account:delete', '3', '4', '2131', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 09:33:48', '', '2023-01-25 09:33:48', '\0');
INSERT INTO `system_menu`
VALUES ('2136', '邮件模版', '', '2', '0', '2130', 'mail-template', 'education', 'system/mail/template/index',
        'SystemMailTemplate', '0', '', '', '', '', '2023-01-25 12:05:31', '1', '2023-04-08 08:53:34', '\0');
INSERT INTO `system_menu`
VALUES ('2137', '模版查询', 'system:mail-template:query', '3', '1', '2136', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', '\0');
INSERT INTO `system_menu`
VALUES ('2138', '模版创建', 'system:mail-template:create', '3', '2', '2136', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', '\0');
INSERT INTO `system_menu`
VALUES ('2139', '模版更新', 'system:mail-template:update', '3', '3', '2136', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', '\0');
INSERT INTO `system_menu`
VALUES ('2140', '模版删除', 'system:mail-template:delete', '3', '4', '2136', '', '', '', null, '0', '', '', '', '',
        '2023-01-25 12:05:31', '', '2023-01-25 12:05:31', '\0');
INSERT INTO `system_menu`
VALUES ('2141', '邮件记录', '', '2', '0', '2130', 'mail-log', 'log', 'system/mail/log/index', 'SystemMailLog', '0', '',
        '', '', '', '2023-01-26 02:16:50', '1', '2023-04-08 08:53:49', '\0');
INSERT INTO `system_menu`
VALUES ('2142', '日志查询', 'system:mail-log:query', '3', '1', '2141', '', '', '', null, '0', '', '', '', '',
        '2023-01-26 02:16:50', '', '2023-01-26 02:16:50', '\0');
INSERT INTO `system_menu`
VALUES ('2143', '发送测试邮件', 'system:mail-template:send-mail', '3', '5', '2136', '', '', '', null, '0', '', '',
        '', '1', '2023-01-26 23:29:15', '1', '2023-01-26 23:29:15', '\0');
INSERT INTO `system_menu`
VALUES ('2144', '站内信管理', '', '1', '11', '1', 'notify', 'message', null, null, '0', '', '', '', '1',
        '2023-01-28 10:25:18', '1', '2023-01-28 10:25:46', '\0');
INSERT INTO `system_menu`
VALUES ('2145', '模板管理', '', '2', '0', '2144', 'notify-template', 'education', 'system/notify/template/index',
        'SystemNotifyTemplate', '0', '', '', '', '', '2023-01-28 02:26:42', '1', '2023-04-08 08:54:39', '\0');
INSERT INTO `system_menu`
VALUES ('2146', '站内信模板查询', 'system:notify-template:query', '3', '1', '2145', '', '', '', null, '0', '', '',
        '', '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42', '\0');
INSERT INTO `system_menu`
VALUES ('2147', '站内信模板创建', 'system:notify-template:create', '3', '2', '2145', '', '', '', null, '0', '', '',
        '', '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42', '\0');
INSERT INTO `system_menu`
VALUES ('2148', '站内信模板更新', 'system:notify-template:update', '3', '3', '2145', '', '', '', null, '0', '', '',
        '', '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42', '\0');
INSERT INTO `system_menu`
VALUES ('2149', '站内信模板删除', 'system:notify-template:delete', '3', '4', '2145', '', '', '', null, '0', '', '',
        '', '', '2023-01-28 02:26:42', '', '2023-01-28 02:26:42', '\0');
INSERT INTO `system_menu`
VALUES ('2150', '发送测试站内信', 'system:notify-template:send-notify', '3', '5', '2145', '', '', '', null, '0', '',
        '', '', '1', '2023-01-28 10:54:43', '1', '2023-01-28 10:54:43', '\0');
INSERT INTO `system_menu`
VALUES ('2151', '消息记录', '', '2', '0', '2144', 'notify-message', 'edit', 'system/notify/message/index',
        'SystemNotifyMessage', '0', '', '', '', '', '2023-01-28 04:28:22', '1', '2023-04-08 08:54:11', '\0');
INSERT INTO `system_menu`
VALUES ('2152', '站内信消息查询', 'system:notify-message:query', '3', '1', '2151', '', '', '', null, '0', '', '', '',
        '', '2023-01-28 04:28:22', '', '2023-01-28 04:28:22', '\0');
INSERT INTO `system_menu`
VALUES ('2153', '大屏设计器', '', '2', '2', '1281', 'go-view', 'dashboard', 'report/goview/index', 'JimuReport', '0',
        '', '', '', '1', '2023-02-07 00:03:19', '1', '2023-04-08 10:48:15', '\0');
INSERT INTO `system_menu`
VALUES ('2154', '创建项目', 'report:go-view-project:create', '3', '1', '2153', '', '', '', null, '0', '', '', '',
        '1', '2023-02-07 19:25:14', '1', '2023-02-07 19:25:14', '\0');
INSERT INTO `system_menu`
VALUES ('2155', '更新项目', 'report:go-view-project:delete', '3', '2', '2153', '', '', '', null, '0', '', '', '',
        '1', '2023-02-07 19:25:34', '1', '2023-02-07 19:25:34', '\0');
INSERT INTO `system_menu`
VALUES ('2156', '查询项目', 'report:go-view-project:query', '3', '0', '2153', '', '', '', null, '0', '', '', '', '1',
        '2023-02-07 19:25:53', '1', '2023-02-07 19:25:53', '\0');
INSERT INTO `system_menu`
VALUES ('2157', '使用 SQL 查询数据', 'report:go-view-data:get-by-sql', '3', '3', '2153', '', '', '', null, '0', '',
        '', '', '1', '2023-02-07 19:26:15', '1', '2023-02-07 19:26:15', '\0');
INSERT INTO `system_menu`
VALUES ('2158', '使用 HTTP 查询数据', 'report:go-view-data:get-by-http', '3', '4', '2153', '', '', '', null, '0', '',
        '', '', '1', '2023-02-07 19:26:35', '1', '2023-02-07 19:26:35', '\0');
INSERT INTO `system_menu`
VALUES ('2159', 'Boot 开发文档', '', '1', '1', '0', 'https://doc.iocoder.cn/', 'education', null, null, '0', '', '',
        '', '1', '2023-02-10 22:46:28', '1', '2023-02-10 22:46:28', '\0');
INSERT INTO `system_menu`
VALUES ('2160', 'Cloud 开发文档', '', '1', '2', '0', 'https://cloud.iocoder.cn', 'documentation', null, null, '0', '',
        '', '', '1', '2023-02-10 22:47:07', '1', '2023-02-10 22:47:07', '\0');
INSERT INTO `system_menu`
VALUES ('2161', '接入示例', '', '2', '99', '1117', 'demo-order', 'drag', 'pay/demo/index', null, '0', '', '', '', '',
        '2023-02-11 14:21:42', '1', '2023-02-11 22:26:35', '\0');
INSERT INTO `system_menu`
VALUES ('2162', '商品导出', 'product:spu:export', '3', '5', '2014', '', '', '', null, '0', '', '', '', '',
        '2022-07-30 14:22:58', '', '2022-07-30 14:22:58', '\0');
INSERT INTO `system_menu`
VALUES ('2164', '配送管理', '', '1', '3', '2072', 'delivery', 'ep:shopping-cart', '', '', '0', '', '', '', '1',
        '2023-05-18 09:18:02', '1', '2023-09-28 10:58:09', '\0');
INSERT INTO `system_menu`
VALUES ('2165', '快递发货', '', '1', '0', '2164', 'express', 'ep:bicycle', '', '', '0', '', '', '', '1',
        '2023-05-18 09:22:06', '1', '2023-08-30 21:02:49', '\0');
INSERT INTO `system_menu`
VALUES ('2166', '门店自提', '', '1', '1', '2164', 'pick-up-store', 'ep:add-location', '', '', '0', '', '', '', '1',
        '2023-05-18 09:23:14', '1', '2023-08-30 21:03:21', '\0');
INSERT INTO `system_menu`
VALUES ('2167', '快递公司', '', '2', '0', '2165', 'express', 'ep:compass', 'mall/trade/delivery/express/index',
        'Express', '0', '', '', '', '1', '2023-05-18 09:27:21', '1', '2023-08-30 21:02:59', '\0');
INSERT INTO `system_menu`
VALUES ('2168', '快递公司查询', 'trade:delivery:express:query', '3', '1', '2167', '', '', '', null, '0', '', '', '',
        '', '2023-05-18 09:37:53', '', '2023-05-18 09:37:53', '\0');
INSERT INTO `system_menu`
VALUES ('2169', '快递公司创建', 'trade:delivery:express:create', '3', '2', '2167', '', '', '', null, '0', '', '', '',
        '', '2023-05-18 09:37:53', '', '2023-05-18 09:37:53', '\0');
INSERT INTO `system_menu`
VALUES ('2170', '快递公司更新', 'trade:delivery:express:update', '3', '3', '2167', '', '', '', null, '0', '', '', '',
        '', '2023-05-18 09:37:53', '', '2023-05-18 09:37:53', '\0');
INSERT INTO `system_menu`
VALUES ('2171', '快递公司删除', 'trade:delivery:express:delete', '3', '4', '2167', '', '', '', null, '0', '', '', '',
        '', '2023-05-18 09:37:53', '', '2023-05-18 09:37:53', '\0');
INSERT INTO `system_menu`
VALUES ('2172', '快递公司导出', 'trade:delivery:express:export', '3', '5', '2167', '', '', '', null, '0', '', '', '',
        '', '2023-05-18 09:37:53', '', '2023-05-18 09:37:53', '\0');
INSERT INTO `system_menu`
VALUES ('2173', '运费模版', 'trade:delivery:express-template:query', '2', '1', '2165', 'express-template',
        'ep:coordinate', 'mall/trade/delivery/expressTemplate/index', 'ExpressTemplate', '0', '', '', '', '1',
        '2023-05-20 06:48:10', '1', '2023-08-30 21:03:13', '\0');
INSERT INTO `system_menu`
VALUES ('2174', '快递运费模板查询', 'trade:delivery:express-template:query', '3', '1', '2173', '', '', '', null, '0',
        '', '', '', '', '2023-05-20 06:49:53', '', '2023-05-20 06:49:53', '\0');
INSERT INTO `system_menu`
VALUES ('2175', '快递运费模板创建', 'trade:delivery:express-template:create', '3', '2', '2173', '', '', '', null, '0',
        '', '', '', '', '2023-05-20 06:49:53', '', '2023-05-20 06:49:53', '\0');
INSERT INTO `system_menu`
VALUES ('2176', '快递运费模板更新', 'trade:delivery:express-template:update', '3', '3', '2173', '', '', '', null, '0',
        '', '', '', '', '2023-05-20 06:49:53', '', '2023-05-20 06:49:53', '\0');
INSERT INTO `system_menu`
VALUES ('2177', '快递运费模板删除', 'trade:delivery:express-template:delete', '3', '4', '2173', '', '', '', null, '0',
        '', '', '', '', '2023-05-20 06:49:53', '', '2023-05-20 06:49:53', '\0');
INSERT INTO `system_menu`
VALUES ('2178', '快递运费模板导出', 'trade:delivery:express-template:export', '3', '5', '2173', '', '', '', null, '0',
        '', '', '', '', '2023-05-20 06:49:53', '', '2023-05-20 06:49:53', '\0');
INSERT INTO `system_menu`
VALUES ('2179', '门店管理', '', '2', '1', '2166', 'pick-up-store', 'ep:basketball',
        'mall/trade/delivery/pickUpStore/index', 'PickUpStore', '0', '', '', '', '1', '2023-05-25 10:50:00', '1',
        '2023-08-30 21:03:28', '\0');
INSERT INTO `system_menu`
VALUES ('2180', '自提门店查询', 'trade:delivery:pick-up-store:query', '3', '1', '2179', '', '', '', null, '0', '', '',
        '', '', '2023-05-25 10:53:29', '', '2023-05-25 10:53:29', '\0');
INSERT INTO `system_menu`
VALUES ('2181', '自提门店创建', 'trade:delivery:pick-up-store:create', '3', '2', '2179', '', '', '', null, '0', '',
        '', '', '', '2023-05-25 10:53:29', '', '2023-05-25 10:53:29', '\0');
INSERT INTO `system_menu`
VALUES ('2182', '自提门店更新', 'trade:delivery:pick-up-store:update', '3', '3', '2179', '', '', '', null, '0', '',
        '', '', '', '2023-05-25 10:53:29', '', '2023-05-25 10:53:29', '\0');
INSERT INTO `system_menu`
VALUES ('2183', '自提门店删除', 'trade:delivery:pick-up-store:delete', '3', '4', '2179', '', '', '', null, '0', '',
        '', '', '', '2023-05-25 10:53:29', '', '2023-05-25 10:53:29', '\0');
INSERT INTO `system_menu`
VALUES ('2184', '自提门店导出', 'trade:delivery:pick-up-store:export', '3', '5', '2179', '', '', '', null, '0', '',
        '', '', '', '2023-05-25 10:53:29', '', '2023-05-25 10:53:29', '\0');
INSERT INTO `system_menu`
VALUES ('2209', '秒杀活动', '', '2', '3', '2030', 'seckill', 'ep:place', '', '', '0', '', '', '', '1',
        '2023-06-24 17:39:13', '1', '2023-06-24 18:55:15', '\0');
INSERT INTO `system_menu`
VALUES ('2262', '会员中心', '', '1', '55', '0', '/member', 'ep:bicycle', null, null, '0', '', '', '', '1',
        '2023-06-10 00:42:03', '1', '2023-08-20 09:23:56', '\0');
INSERT INTO `system_menu`
VALUES ('2275', '会员配置', '', '2', '0', '2262', 'config', 'fa:archive', 'member/config/index', 'MemberConfig', '0',
        '', '', '', '', '2023-06-10 02:07:44', '1', '2023-10-01 23:41:29', '\0');
INSERT INTO `system_menu`
VALUES ('2276', '积分设置查询', 'point:config:query', '3', '1', '2275', '', '', '', null, '0', '', '', '', '',
        '2023-06-10 02:07:44', '', '2023-06-10 02:07:44', '\0');
INSERT INTO `system_menu`
VALUES ('2277', '积分设置创建', 'point:config:save', '3', '2', '2275', '', '', '', '', '0', '', '', '', '',
        '2023-06-10 02:07:44', '1', '2023-06-27 20:32:31', '\0');
INSERT INTO `system_menu`
VALUES ('2281', '签到配置', '', '2', '2', '2300', 'config', 'ep:calendar', 'member/signin/config/index', 'SignInConfig',
        '0', '', '', '', '', '2023-06-10 03:26:12', '1', '2023-08-20 19:25:51', '\0');
INSERT INTO `system_menu`
VALUES ('2282', '积分签到规则查询', 'point:sign-in-config:query', '3', '1', '2281', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 03:26:12', '', '2023-06-10 03:26:12', '\0');
INSERT INTO `system_menu`
VALUES ('2283', '积分签到规则创建', 'point:sign-in-config:create', '3', '2', '2281', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 03:26:12', '', '2023-06-10 03:26:12', '\0');
INSERT INTO `system_menu`
VALUES ('2284', '积分签到规则更新', 'point:sign-in-config:update', '3', '3', '2281', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 03:26:12', '', '2023-06-10 03:26:12', '\0');
INSERT INTO `system_menu`
VALUES ('2285', '积分签到规则删除', 'point:sign-in-config:delete', '3', '4', '2281', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 03:26:12', '', '2023-06-10 03:26:12', '\0');
INSERT INTO `system_menu`
VALUES ('2287', '会员积分', '', '2', '10', '2262', 'record', 'fa:asterisk', 'member/point/record/index', 'PointRecord',
        '0', '', '', '', '', '2023-06-10 04:18:50', '1', '2023-10-01 23:42:11', '\0');
INSERT INTO `system_menu`
VALUES ('2288', '用户积分记录查询', 'point:record:query', '3', '1', '2287', '', '', '', null, '0', '', '', '', '',
        '2023-06-10 04:18:50', '', '2023-06-10 04:18:50', '\0');
INSERT INTO `system_menu`
VALUES ('2293', '签到记录', '', '2', '3', '2300', 'record', 'ep:chicken', 'member/signin/record/index', 'SignInRecord',
        '0', '', '', '', '', '2023-06-10 04:48:22', '1', '2023-08-20 19:26:02', '\0');
INSERT INTO `system_menu`
VALUES ('2294', '用户签到积分查询', 'point:sign-in-record:query', '3', '1', '2293', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 04:48:22', '', '2023-06-10 04:48:22', '\0');
INSERT INTO `system_menu`
VALUES ('2297', '用户签到积分删除', 'point:sign-in-record:delete', '3', '4', '2293', '', '', '', null, '0', '', '',
        '', '', '2023-06-10 04:48:22', '', '2023-06-10 04:48:22', '\0');
INSERT INTO `system_menu`
VALUES ('2300', '会员签到', '', '1', '11', '2262', 'signin', 'ep:alarm-clock', '', '', '0', '', '', '', '1',
        '2023-06-27 22:49:53', '1', '2023-08-20 09:23:48', '\0');
INSERT INTO `system_menu`
VALUES ('2301', '回调通知', '', '2', '4', '1117', 'notify', 'example', 'pay/notify/index', 'PayNotify', '0', '', '',
        '', '', '2023-07-20 04:41:32', '1', '2023-07-20 13:45:08', '\0');
INSERT INTO `system_menu`
VALUES ('2302', '支付通知查询', 'pay:notify:query', '3', '1', '2301', '', '', '', null, '0', '', '', '', '',
        '2023-07-20 04:41:32', '', '2023-07-20 04:41:32', '\0');
INSERT INTO `system_menu`
VALUES ('2303', '拼团活动', '', '2', '3', '2030', 'combination', 'fa:group', '', '', '0', '', '', '', '1',
        '2023-08-12 17:19:54', '1', '2023-08-12 17:20:05', '\0');
INSERT INTO `system_menu`
VALUES ('2304', '拼团商品', '', '2', '1', '2303', 'acitivity', 'ep:apple', 'mall/promotion/combination/activity/index',
        'PromotionCombinationActivity', '0', '', '', '', '1', '2023-08-12 17:22:03', '1', '2023-08-12 17:22:29',
        '\0');
INSERT INTO `system_menu`
VALUES ('2305', '拼团活动查询', 'promotion:combination-activity:query	', '3', '1', '2304', '', '', '', '', '0', '',
        '', '', '1', '2023-08-12 17:54:32', '1', '2023-08-12 17:54:32', '\0');
INSERT INTO `system_menu`
VALUES ('2306', '拼团活动创建', 'promotion:combination-activity:create', '3', '2', '2304', '', '', '', '', '0', '',
        '', '', '1', '2023-08-12 17:54:49', '1', '2023-08-12 17:54:49', '\0');
INSERT INTO `system_menu`
VALUES ('2307', '拼团活动更新', 'promotion:combination-activity:update', '3', '3', '2304', '', '', '', '', '0', '',
        '', '', '1', '2023-08-12 17:55:04', '1', '2023-08-12 17:55:04', '\0');
INSERT INTO `system_menu`
VALUES ('2308', '拼团活动删除', 'promotion:combination-activity:delete', '3', '4', '2304', '', '', '', '', '0', '',
        '', '', '1', '2023-08-12 17:55:23', '1', '2023-08-12 17:55:23', '\0');
INSERT INTO `system_menu`
VALUES ('2309', '拼团活动关闭', 'promotion:combination-activity:close', '3', '5', '2304', '', '', '', '', '0', '', '',
        '', '1', '2023-08-12 17:55:37', '1', '2023-10-06 10:51:57', '\0');
INSERT INTO `system_menu`
VALUES ('2310', '砍价活动', '', '2', '4', '2030', 'bargain', 'ep:box', '', '', '0', '', '', '', '1',
        '2023-08-13 00:27:25', '1', '2023-08-13 00:27:25', '\0');
INSERT INTO `system_menu`
VALUES ('2311', '砍价商品', '', '2', '1', '2310', 'activity', 'ep:burger', 'mall/promotion/bargain/activity/index',
        'PromotionBargainActivity', '0', '', '', '', '1', '2023-08-13 00:28:49', '1', '2023-10-05 01:16:23', '\0');
INSERT INTO `system_menu`
VALUES ('2312', '砍价活动查询', 'promotion:bargain-activity:query', '3', '1', '2311', '', '', '', '', '0', '', '',
        '', '1', '2023-08-13 00:32:30', '1', '2023-08-13 00:32:30', '\0');
INSERT INTO `system_menu`
VALUES ('2313', '砍价活动创建', 'promotion:bargain-activity:create', '3', '2', '2311', '', '', '', '', '0', '', '',
        '', '1', '2023-08-13 00:32:44', '1', '2023-08-13 00:32:44', '\0');
INSERT INTO `system_menu`
VALUES ('2314', '砍价活动更新', 'promotion:bargain-activity:update', '3', '3', '2311', '', '', '', '', '0', '', '',
        '', '1', '2023-08-13 00:32:55', '1', '2023-08-13 00:32:55', '\0');
INSERT INTO `system_menu`
VALUES ('2315', '砍价活动删除', 'promotion:bargain-activity:delete', '3', '4', '2311', '', '', '', '', '0', '', '',
        '', '1', '2023-08-13 00:34:50', '1', '2023-08-13 00:34:50', '\0');
INSERT INTO `system_menu`
VALUES ('2316', '砍价活动关闭', 'promotion:bargain-activity:close', '3', '5', '2311', '', '', '', '', '0', '', '',
        '', '1', '2023-08-13 00:35:02', '1', '2023-08-13 00:35:02', '\0');
INSERT INTO `system_menu`
VALUES ('2317', '会员管理', '', '2', '0', '2262', 'user', 'ep:avatar', 'member/user/index', 'MemberUser', '0', '', '',
        '', '', '2023-08-19 04:12:15', '1', '2023-08-24 00:50:55', '\0');
INSERT INTO `system_menu`
VALUES ('2318', '会员用户查询', 'member:user:query', '3', '1', '2317', '', '', '', null, '0', '', '', '', '',
        '2023-08-19 04:12:15', '', '2023-08-19 04:12:15', '\0');
INSERT INTO `system_menu`
VALUES ('2319', '会员用户更新', 'member:user:update', '3', '3', '2317', '', '', '', null, '0', '', '', '', '',
        '2023-08-19 04:12:15', '', '2023-08-19 04:12:15', '\0');
INSERT INTO `system_menu`
VALUES ('2320', '会员标签', '', '2', '1', '2262', 'tag', 'ep:collection-tag', 'member/tag/index', 'MemberTag', '0', '',
        '', '', '', '2023-08-20 01:03:08', '1', '2023-08-20 09:23:19', '\0');
INSERT INTO `system_menu`
VALUES ('2321', '会员标签查询', 'member:tag:query', '3', '1', '2320', '', '', '', null, '0', '', '', '', '',
        '2023-08-20 01:03:08', '', '2023-08-20 01:03:08', '\0');
INSERT INTO `system_menu`
VALUES ('2322', '会员标签创建', 'member:tag:create', '3', '2', '2320', '', '', '', null, '0', '', '', '', '',
        '2023-08-20 01:03:08', '', '2023-08-20 01:03:08', '\0');
INSERT INTO `system_menu`
VALUES ('2323', '会员标签更新', 'member:tag:update', '3', '3', '2320', '', '', '', null, '0', '', '', '', '',
        '2023-08-20 01:03:08', '', '2023-08-20 01:03:08', '\0');
INSERT INTO `system_menu`
VALUES ('2324', '会员标签删除', 'member:tag:delete', '3', '4', '2320', '', '', '', null, '0', '', '', '', '',
        '2023-08-20 01:03:08', '', '2023-08-20 01:03:08', '\0');
INSERT INTO `system_menu`
VALUES ('2325', '会员等级', '', '2', '2', '2262', 'level', 'fa:level-up', 'member/level/index', 'MemberLevel', '0', '',
        '', '', '', '2023-08-22 12:41:01', '1', '2023-08-22 21:47:00', '\0');
INSERT INTO `system_menu`
VALUES ('2326', '会员等级查询', 'member:level:query', '3', '1', '2325', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 12:41:02', '', '2023-08-22 12:41:02', '\0');
INSERT INTO `system_menu`
VALUES ('2327', '会员等级创建', 'member:level:create', '3', '2', '2325', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 12:41:02', '', '2023-08-22 12:41:02', '\0');
INSERT INTO `system_menu`
VALUES ('2328', '会员等级更新', 'member:level:update', '3', '3', '2325', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 12:41:02', '', '2023-08-22 12:41:02', '\0');
INSERT INTO `system_menu`
VALUES ('2329', '会员等级删除', 'member:level:delete', '3', '4', '2325', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 12:41:02', '', '2023-08-22 12:41:02', '\0');
INSERT INTO `system_menu`
VALUES ('2330', '会员分组', '', '2', '3', '2262', 'group', 'fa:group', 'member/group/index', 'MemberGroup', '0', '',
        '', '', '', '2023-08-22 13:50:06', '1', '2023-10-01 23:42:01', '\0');
INSERT INTO `system_menu`
VALUES ('2331', '用户分组查询', 'member:group:query', '3', '1', '2330', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 13:50:06', '', '2023-08-22 13:50:06', '\0');
INSERT INTO `system_menu`
VALUES ('2332', '用户分组创建', 'member:group:create', '3', '2', '2330', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 13:50:06', '', '2023-08-22 13:50:06', '\0');
INSERT INTO `system_menu`
VALUES ('2333', '用户分组更新', 'member:group:update', '3', '3', '2330', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 13:50:06', '', '2023-08-22 13:50:06', '\0');
INSERT INTO `system_menu`
VALUES ('2334', '用户分组删除', 'member:group:delete', '3', '4', '2330', '', '', '', null, '0', '', '', '', '',
        '2023-08-22 13:50:06', '', '2023-08-22 13:50:06', '\0');
INSERT INTO `system_menu`
VALUES ('2335', '用户等级修改', 'member:user:update-level', '3', '5', '2317', '', '', '', null, '0', '', '', '', '',
        '2023-08-23 16:49:05', '', '2023-08-23 16:50:48', '\0');
INSERT INTO `system_menu`
VALUES ('2336', '商品评论', '', '2', '5', '2000', 'comment', 'ep:comment', 'mall/product/comment/index',
        'ProductComment', '0', '', '', '', '1', '2023-08-26 11:03:00', '1', '2023-08-26 11:03:38', '\0');
INSERT INTO `system_menu`
VALUES ('2337', '评论查询', 'product:comment:query', '3', '1', '2336', '', '', '', '', '0', '', '', '', '1',
        '2023-08-26 11:04:01', '1', '2023-08-26 11:04:01', '\0');
INSERT INTO `system_menu`
VALUES ('2338', '添加自评', 'product:comment:create', '3', '2', '2336', '', '', '', '', '0', '', '', '', '1',
        '2023-08-26 11:04:23', '1', '2023-08-26 11:08:18', '\0');
INSERT INTO `system_menu`
VALUES ('2339', '商家回复', 'product:comment:update', '3', '3', '2336', '', '', '', '', '0', '', '', '', '1',
        '2023-08-26 11:04:37', '1', '2023-08-26 11:04:37', '\0');
INSERT INTO `system_menu`
VALUES ('2340', '显隐评论', 'product:comment:update', '3', '4', '2336', '', '', '', '', '0', '', '', '', '1',
        '2023-08-26 11:04:55', '1', '2023-08-26 11:04:55', '\0');
INSERT INTO `system_menu`
VALUES ('2341', '优惠劵发送', 'promotion:coupon:send', '3', '2', '2038', '', '', '', '', '0', '', '', '', '1',
        '2023-09-02 00:03:14', '1', '2023-09-02 00:03:14', '\0');
INSERT INTO `system_menu`
VALUES ('2342', '交易配置', '', '2', '0', '2072', 'config', 'ep:setting', 'trade/config/index', 'TradeConfig', '0', '',
        '', '', '', '2023-09-28 02:46:22', '1', '2023-09-28 10:57:36', '\0');
INSERT INTO `system_menu`
VALUES ('2343', '交易中心配置查询', 'trade:config:query', '3', '1', '2342', '', '', '', null, '0', '', '', '', '',
        '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2344', '交易中心配置保存', 'trade:config:save', '3', '2', '2342', '', '', '', null, '0', '', '', '', '',
        '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2345', '分销管理', '', '1', '4', '2072', 'brokerage', 'fa-solid:project-diagram', '', '', '0', '', '', '',
        '', '2023-09-28 02:46:22', '1', '2023-09-28 10:58:44', '\0');
INSERT INTO `system_menu`
VALUES ('2346', '分销用户', '', '2', '0', '2345', 'brokerage-user', 'fa-solid:user-tie', 'trade/brokerage/user/index',
        'TradeBrokerageUser', '0', '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2347', '分销用户查询', 'trade:brokerage-user:query', '3', '1', '2346', '', '', '', null, '0', '', '', '',
        '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2348', '分销用户推广人查询', 'trade:brokerage-user:user-query', '3', '2', '2346', '', '', '', null, '0', '',
        '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2349', '分销用户推广订单查询', 'trade:brokerage-user:order-query', '3', '3', '2346', '', '', '', null, '0',
        '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2350', '分销用户修改推广资格', 'trade:brokerage-user:update-brokerage-enable', '3', '4', '2346', '', '', '',
        null, '0', '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2351', '分销用户修改推广员', 'trade:brokerage-user:update-bind-user', '3', '5', '2346', '', '', '', null, '0',
        '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2352', '分销用户清除推广员', 'trade:brokerage-user:clear-bind-user', '3', '6', '2346', '', '', '', null, '0',
        '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2353', '佣金记录', '', '2', '1', '2345', 'brokerage-record', 'fa:money', 'trade/brokerage/record/index',
        'TradeBrokerageRecord', '0', '', '', '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2354', '佣金记录查询', 'trade:brokerage-record:query', '3', '1', '2353', '', '', '', null, '0', '', '', '',
        '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2355', '佣金提现', '', '2', '2', '2345', 'brokerage-withdraw', 'fa:credit-card',
        'trade/brokerage/withdraw/index', 'TradeBrokerageWithdraw', '0', '', '', '', '', '2023-09-28 02:46:22', '',
        '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2356', '佣金提现查询', 'trade:brokerage-withdraw:query', '3', '1', '2355', '', '', '', null, '0', '', '',
        '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2357', '佣金提现审核', 'trade:brokerage-withdraw:audit', '3', '2', '2355', '', '', '', null, '0', '', '',
        '', '', '2023-09-28 02:46:22', '', '2023-09-28 02:46:22', '\0');
INSERT INTO `system_menu`
VALUES ('2358', '统计中心', '', '1', '75', '2362', 'statistics', 'ep:data-line', '', '', '0', '', '', '', '',
        '2023-09-30 03:22:40', '1', '2023-09-30 11:54:48', '\0');
INSERT INTO `system_menu`
VALUES ('2359', '交易统计', '', '2', '4', '2358', 'trade', 'fa-solid:credit-card', 'statistics/trade/index',
        'TradeStatistics', '0', '', '', '', '', '2023-09-30 03:22:40', '', '2023-09-30 03:22:40', '\0');
INSERT INTO `system_menu`
VALUES ('2360', '交易统计查询', 'statistics:trade:query', '3', '1', '2359', '', '', '', null, '0', '', '', '', '',
        '2023-09-30 03:22:40', '', '2023-09-30 03:22:40', '\0');
INSERT INTO `system_menu`
VALUES ('2361', '交易统计导出', 'statistics:trade:export', '3', '2', '2359', '', '', '', null, '0', '', '', '', '',
        '2023-09-30 03:22:40', '', '2023-09-30 03:22:40', '\0');
INSERT INTO `system_menu`
VALUES ('2362', '商城系统', '', '1', '59', '0', '/mall', 'ep:shop', '', '', '0', '', '', '', '1',
        '2023-09-30 11:52:02', '1', '2023-09-30 11:52:18', '\0');
INSERT INTO `system_menu`
VALUES ('2363', '用户积分修改', 'member:user:update-point', '3', '6', '2317', '', '', '', null, '0', '', '', '', '',
        '2023-10-01 14:39:43', '', '2023-10-01 14:39:43', '\0');
INSERT INTO `system_menu`
VALUES ('2364', '用户余额修改', 'member:user:update-balance', '3', '7', '2317', '', '', '', '', '0', '', '', '', '',
        '2023-10-01 14:39:43', '1', '2023-10-01 22:42:31', '\0');
INSERT INTO `system_menu`
VALUES ('2365', '优惠劵', '', '1', '2', '2030', 'coupon', 'fa-solid:disease', '', '', '0', '', '', '', '1',
        '2023-10-03 12:39:15', '1', '2023-10-05 00:16:07', '\0');
INSERT INTO `system_menu`
VALUES ('2366', '砍价记录', '', '2', '2', '2310', 'record', 'ep:list', 'mall/promotion/bargain/record/index',
        'PromotionBargainRecord', '0', '', '', '', '', '2023-10-05 02:49:06', '1', '2023-10-05 10:50:38', '\0');
INSERT INTO `system_menu`
VALUES ('2367', '砍价记录查询', 'promotion:bargain-record:query', '3', '1', '2366', '', '', '', null, '0', '', '',
        '', '', '2023-10-05 02:49:06', '', '2023-10-05 02:49:06', '\0');
INSERT INTO `system_menu`
VALUES ('2368', '助力记录查询', 'promotion:bargain-help:query', '3', '2', '2366', '', '', '', '', '0', '', '', '',
        '1', '2023-10-05 12:27:49', '1', '2023-10-05 12:27:49', '\0');
INSERT INTO `system_menu`
VALUES ('2369', '拼团记录', 'promotion:combination-record:query', '2', '2', '2303', 'record', 'ep:avatar',
        'mall/promotion/combination/record/index.vue', 'PromotionCombinationRecord', '0', '', '', '', '1',
        '2023-10-08 07:10:22', '1', '2023-10-08 07:34:11', '\0');
INSERT INTO `system_menu`
VALUES ('2374', '会员统计', '', '2', '2', '2358', 'member', 'ep:avatar', 'statistics/member/index', 'MemberStatistics',
        '0', '', '', '', '', '2023-10-11 04:39:24', '1', '2023-10-11 12:50:22', '\0');
INSERT INTO `system_menu`
VALUES ('2375', '会员统计查询', 'statistics:member:query', '3', '1', '2374', '', '', '', null, '0', '', '', '', '',
        '2023-10-11 04:39:24', '', '2023-10-11 04:39:24', '\0');
INSERT INTO `system_menu`
VALUES ('2376', '订单核销', 'trade:order:pick-up', '3', '10', '2076', '', '', '', '', '0', '', '', '', '1',
        '2023-10-14 17:11:58', '1', '2023-10-14 17:11:58', '\0');
INSERT INTO `system_menu`
VALUES ('2377', '文章分类', '', '2', '0', '2387', 'article/category', 'fa:certificate',
        'mall/promotion/article/category/index', 'ArticleCategory', '0', '', '', '', '', '2023-10-16 01:26:18', '1',
        '2023-10-16 09:38:26', '\0');
INSERT INTO `system_menu`
VALUES ('2378', '分类查询', 'promotion:article-category:query', '3', '1', '2377', '', '', '', null, '0', '', '', '',
        '', '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2379', '分类创建', 'promotion:article-category:create', '3', '2', '2377', '', '', '', null, '0', '', '', '',
        '', '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2380', '分类更新', 'promotion:article-category:update', '3', '3', '2377', '', '', '', null, '0', '', '', '',
        '', '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2381', '分类删除', 'promotion:article-category:delete', '3', '4', '2377', '', '', '', null, '0', '', '', '',
        '', '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2382', '文章列表', '', '2', '2', '2387', 'article', 'ep:connection', 'mall/promotion/article/index', 'Article',
        '0', '', '', '', '', '2023-10-16 01:26:18', '1', '2023-10-16 09:41:19', '\0');
INSERT INTO `system_menu`
VALUES ('2383', '文章管理查询', 'promotion:article:query', '3', '1', '2382', '', '', '', null, '0', '', '', '', '',
        '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2384', '文章管理创建', 'promotion:article:create', '3', '2', '2382', '', '', '', null, '0', '', '', '', '',
        '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2385', '文章管理更新', 'promotion:article:update', '3', '3', '2382', '', '', '', null, '0', '', '', '', '',
        '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2386', '文章管理删除', 'promotion:article:delete', '3', '4', '2382', '', '', '', null, '0', '', '', '', '',
        '2023-10-16 01:26:18', '', '2023-10-16 01:26:18', '\0');
INSERT INTO `system_menu`
VALUES ('2387', '内容管理', '', '1', '1', '2030', 'content', 'ep:collection', '', '', '0', '', '', '', '1',
        '2023-10-16 09:37:31', '1', '2023-10-16 09:37:31', '\0');
INSERT INTO `system_menu`
VALUES ('2388', '商城首页', '', '2', '1', '2362', 'home', 'ep:home-filled', 'mall/home/index', 'MallHome', '0', '',
        '', '', '', '2023-10-16 12:10:33', '', '2023-10-16 12:10:33', '\0');
INSERT INTO `system_menu`
VALUES ('2389', '核销订单', '', '2', '2', '2166', 'pick-up-order', 'ep:list', 'mall/trade/delivery/pickUpOrder/index',
        'PickUpOrder', '0', '', '', '', '', '2023-10-19 16:09:51', '', '2023-10-19 16:09:51', '\0');
INSERT INTO `system_menu`
VALUES ('2390', '优惠活动', '', '1', '99', '2030', 'youhui', 'ep:aim', '', '', '0', '', '', '', '1',
        '2023-10-21 19:23:49', '1', '2023-10-21 19:23:49', '\0');

-- ----------------------------
-- Table structure for system_oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `system_oauth2_client`;
CREATE TABLE `system_oauth2_client`
(
    `id`                             bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `client_id`                      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `secret`                         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
    `name`                           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
    `logo`                           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用图标',
    `description`                    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '应用描述',
    `status`                         tinyint(4)                              NOT NULL COMMENT '状态',
    `access_token_validity_seconds`  int(11)                                 NOT NULL COMMENT '访问令牌的有效期',
    `refresh_token_validity_seconds` int(11)                                 NOT NULL COMMENT '刷新令牌的有效期',
    `redirect_uris`                  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
    `authorized_grant_types`         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
    `scopes`                         varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '授权范围',
    `auto_approve_scopes`            varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '自动通过的授权范围',
    `authorities`                    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '权限',
    `resource_ids`                   varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '资源',
    `additional_information`         varchar(4096) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '附加信息',
    `creator`                        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`                    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                        varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`                    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                        bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 43
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='OAuth2 客户端表';

-- ----------------------------
-- Records of system_oauth2_client
-- ----------------------------
INSERT INTO `system_oauth2_client`
VALUES ('1', 'default', 'admin123', '凉茶', '', '我是描述', '0', '1800', '43200', '[\"https://liangchay.cn\"]',
        '[\"password\",\"authorization_code\",\"implicit\",\"refresh_token\"]', '[\"user.read\",\"user.write\"]', '[]',
        '[\"user.read\",\"user.write\"]', '[]', '{}', '1', '2022-05-11 21:47:12', '1', '2023-11-11 15:25:44', '\0');
INSERT INTO `system_oauth2_client`
VALUES ('40', 'liangcha', 'liangcha', 'liangcha', '', 'liangcha', '0', '1800', '43200', '[\"https://liangchay.cn\"]',
        '[\"password\",\"authorization_code\",\"implicit\"]', '[\"user_info\",\"projects\"]', '[\"user_info\"]', '[]',
        '[]', '{}', '1', '2022-05-12 00:28:20', '1', '2023-11-11 15:25:44', '\0');

-- ----------------------------
-- Table structure for system_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `system_operate_log`;
CREATE TABLE `system_operate_log`
(
    `id`               bigint(20)                               NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `trace_id`         varchar(64) COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `user_id`          bigint(20)                               NOT NULL COMMENT '用户编号',
    `user_type`        tinyint(4)                               NOT NULL DEFAULT '0' COMMENT '用户类型',
    `module`           varchar(50) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '模块标题',
    `name`             varchar(50) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '操作名',
    `type`             bigint(20)                               NOT NULL DEFAULT '0' COMMENT '操作分类',
    `content`          varchar(2000) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作内容',
    `exts`             varchar(512) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '拓展字段',
    `request_method`   varchar(16) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '请求方法名',
    `request_url`      varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '请求地址',
    `user_ip`          varchar(50) COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT '用户 IP',
    `user_agent`       varchar(200) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '浏览器 UA',
    `java_method`      varchar(512) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT 'Java 方法名',
    `java_method_args` varchar(8000) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT 'Java 方法的参数',
    `start_time`       datetime                                 NOT NULL COMMENT '操作时间',
    `duration`         int(11)                                  NOT NULL COMMENT '执行时长',
    `result_code`      int(11)                                  NOT NULL DEFAULT '0' COMMENT '结果码',
    `result_msg`       varchar(512) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '结果提示',
    `result_data`      varchar(4000) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '结果数据',
    `creator`          varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`      datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`      datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`        bigint(20)                               NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8757
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='操作日志记录';

-- ----------------------------
-- Records of system_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for system_post
-- ----------------------------
DROP TABLE IF EXISTS `system_post`;
CREATE TABLE `system_post`
(
    `id`          bigint(20)                             NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `code`        varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
    `name`        varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
    `sort`        int(11)                                NOT NULL COMMENT '显示顺序',
    `status`      tinyint(4)                             NOT NULL COMMENT '状态（0正常 1停用）',
    `remark`      varchar(500) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20)                             NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='岗位信息表';

-- ----------------------------
-- Records of system_post
-- ----------------------------
INSERT INTO `system_post`
VALUES ('1', 'ceo', '董事长', '1', '0', '', 'admin', '2021-01-06 17:03:48', '1', '2023-02-11 15:19:04', '\0', '1');
INSERT INTO `system_post`
VALUES ('2', 'se', '项目经理', '2', '0', '', 'admin', '2021-01-05 17:03:48', '1', '2021-12-12 10:47:47', '\0', '1');
INSERT INTO `system_post`
VALUES ('4', 'user', '普通员工', '4', '0', '111', 'admin', '2021-01-05 17:03:48', '1', '2023-02-11 15:19:00', '\0',
        '1');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`
(
    `id`                  bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`                varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '角色名称',
    `code`                varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
    `sort`                int(11)                                 NOT NULL COMMENT '显示顺序',
    `data_scope`          tinyint(4)                              NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `data_scope_dept_ids` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `status`              tinyint(4)                              NOT NULL COMMENT '角色状态（0正常 1停用）',
    `type`                tinyint(4)                              NOT NULL COMMENT '角色类型',
    `remark`              varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`             varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`             varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`             bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`           bigint(20)                              NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 140
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色信息表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role`
VALUES ('1', '超级管理员', 'super_admin', '1', '1', '', '0', '1', '超级管理员', 'admin', '2021-01-05 17:03:48', '',
        '2022-02-22 05:08:21', '\0', '1');
INSERT INTO `system_role`
VALUES ('2', '普通角色', 'common', '2', '2', '', '0', '1', '普通角色', 'admin', '2021-01-05 17:03:48', '',
        '2022-02-22 05:08:20', '\0', '1');
INSERT INTO `system_role`
VALUES ('101', '测试账号', 'test', '0', '1', '[]', '0', '2', '132', '', '2021-01-06 13:49:35', '1',
        '2023-07-25 23:53:32', '\0', '1');
INSERT INTO `system_role`
VALUES ('109', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-02-22 00:56:14', '1',
        '2022-02-22 00:56:14', '\0', '121');
INSERT INTO `system_role`
VALUES ('110', '测试角色', 'test', '0', '1', '[]', '0', '2', '嘿嘿', '110', '2022-02-23 00:14:34', '110',
        '2022-02-23 13:14:58', '\0', '121');
INSERT INTO `system_role`
VALUES ('111', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-03-07 21:37:58', '1',
        '2022-03-07 21:37:58', '\0', '122');
INSERT INTO `system_role`
VALUES ('113', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-05-17 10:07:10', '1',
        '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role`
VALUES ('114', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-12-30 11:32:03', '1',
        '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role`
VALUES ('115', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-12-30 11:33:42', '1',
        '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role`
VALUES ('116', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-12-30 11:33:48', '1',
        '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role`
VALUES ('118', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2022-12-30 11:47:52', '1',
        '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role`
VALUES ('136', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2023-03-05 21:23:32', '1',
        '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role`
VALUES ('137', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2023-03-05 21:42:27', '1',
        '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role`
VALUES ('138', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2023-03-05 21:59:02', '1',
        '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role`
VALUES ('139', '租户管理员', 'tenant_admin', '0', '1', '', '0', '1', '系统自动生成', '1', '2023-07-25 23:06:04', '1',
        '2023-07-25 23:06:04', '\0', '150');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `role_id`     bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id`     bigint(20) NOT NULL COMMENT '菜单ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime   NOT NULL                    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime   NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)     NOT NULL                    DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20) NOT NULL                    DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2901
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色和菜单关联表';

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu`
VALUES ('263', '109', '1', '1', '2022-02-22 00:56:14', '1', '2022-02-22 00:56:14', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('434', '2', '1', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('454', '2', '1093', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('455', '2', '1094', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('460', '2', '1100', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('467', '2', '1107', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('470', '2', '1110', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('476', '2', '1117', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('477', '2', '100', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('478', '2', '101', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('479', '2', '102', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('480', '2', '1126', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('481', '2', '103', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('483', '2', '104', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('485', '2', '105', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('488', '2', '107', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('490', '2', '108', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('492', '2', '109', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('498', '2', '1138', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('523', '2', '1224', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('524', '2', '1225', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('541', '2', '500', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('543', '2', '501', '1', '2022-02-22 13:09:12', '1', '2022-02-22 13:09:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('675', '2', '2', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('689', '2', '1077', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('690', '2', '1078', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('692', '2', '1083', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('693', '2', '1084', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('699', '2', '1090', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('703', '2', '106', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('704', '2', '110', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('705', '2', '111', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('706', '2', '112', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('707', '2', '113', '1', '2022-02-22 13:16:57', '1', '2022-02-22 13:16:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1296', '110', '1', '110', '2022-02-23 00:23:55', '110', '2022-02-23 00:23:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1489', '1', '1', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1490', '1', '2', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1494', '1', '1077', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1495', '1', '1078', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1496', '1', '1083', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1497', '1', '1084', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1498', '1', '1090', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1499', '1', '1093', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1500', '1', '1094', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1501', '1', '1100', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1502', '1', '1107', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1503', '1', '1110', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1505', '1', '1117', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1506', '1', '100', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1507', '1', '101', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1508', '1', '102', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1509', '1', '1126', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1510', '1', '103', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1511', '1', '104', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1512', '1', '105', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1513', '1', '106', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1514', '1', '107', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1515', '1', '108', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1516', '1', '109', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1517', '1', '110', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1518', '1', '111', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1519', '1', '112', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1520', '1', '113', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1522', '1', '1138', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1525', '1', '1224', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1526', '1', '1225', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1527', '1', '500', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1528', '1', '501', '1', '2022-02-23 20:03:57', '1', '2022-02-23 20:03:57', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1578', '111', '1', '1', '2022-03-07 21:37:58', '1', '2022-03-07 21:37:58', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1604', '101', '1216', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1605', '101', '1217', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1606', '101', '1218', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1607', '101', '1219', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1608', '101', '1220', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1609', '101', '1221', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1610', '101', '5', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1611', '101', '1222', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1612', '101', '1118', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1613', '101', '1119', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1614', '101', '1120', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1615', '101', '1185', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1616', '101', '1186', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1617', '101', '1187', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1618', '101', '1188', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1619', '101', '1189', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1620', '101', '1190', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1621', '101', '1191', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1622', '101', '1192', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1623', '101', '1193', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1624', '101', '1194', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1625', '101', '1195', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1626', '101', '1196', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1627', '101', '1197', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1628', '101', '1198', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1629', '101', '1199', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1630', '101', '1200', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1631', '101', '1201', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1632', '101', '1202', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1633', '101', '1207', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1634', '101', '1208', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1635', '101', '1209', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1636', '101', '1210', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1637', '101', '1211', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1638', '101', '1212', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1639', '101', '1213', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1640', '101', '1215', '1', '2022-03-19 21:45:52', '1', '2022-03-19 21:45:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1641', '101', '2', '1', '2022-04-01 22:21:24', '1', '2022-04-01 22:21:24', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1642', '101', '1031', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1643', '101', '1032', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1644', '101', '1033', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1645', '101', '1034', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1646', '101', '1035', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1647', '101', '1050', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1648', '101', '1051', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1649', '101', '1052', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1650', '101', '1053', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1651', '101', '1054', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1652', '101', '1056', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1653', '101', '1057', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1654', '101', '1058', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1655', '101', '1059', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1656', '101', '1060', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1657', '101', '1066', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1658', '101', '1067', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1659', '101', '1070', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1660', '101', '1071', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1661', '101', '1072', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1662', '101', '1073', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1663', '101', '1074', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1664', '101', '1075', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1665', '101', '1076', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1666', '101', '1077', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1667', '101', '1078', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1668', '101', '1082', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1669', '101', '1083', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1670', '101', '1084', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1671', '101', '1085', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1672', '101', '1086', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1673', '101', '1087', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1674', '101', '1088', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1675', '101', '1089', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1679', '101', '1237', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1680', '101', '1238', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1681', '101', '1239', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1682', '101', '1240', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1683', '101', '1241', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1684', '101', '1242', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1685', '101', '1243', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1687', '101', '106', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1688', '101', '110', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1689', '101', '111', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1690', '101', '112', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1691', '101', '113', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1692', '101', '114', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1693', '101', '115', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1694', '101', '116', '1', '2022-04-01 22:21:37', '1', '2022-04-01 22:21:37', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1712', '113', '1024', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1713', '113', '1025', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1714', '113', '1', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1715', '113', '102', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1716', '113', '103', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1717', '113', '104', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1718', '113', '1013', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1719', '113', '1014', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1720', '113', '1015', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1721', '113', '1016', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1722', '113', '1017', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1723', '113', '1018', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1724', '113', '1019', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1725', '113', '1020', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1726', '113', '1021', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1727', '113', '1022', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1728', '113', '1023', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_role_menu`
VALUES ('1729', '109', '100', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1730', '109', '101', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1731', '109', '1063', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1732', '109', '1064', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1733', '109', '1001', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1734', '109', '1065', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1735', '109', '1002', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1736', '109', '1003', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1737', '109', '1004', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1738', '109', '1005', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1739', '109', '1006', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1740', '109', '1007', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1741', '109', '1008', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1742', '109', '1009', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1743', '109', '1010', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1744', '109', '1011', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1745', '109', '1012', '1', '2022-09-21 22:08:51', '1', '2022-09-21 22:08:51', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1746', '111', '100', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1747', '111', '101', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1748', '111', '1063', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1749', '111', '1064', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1750', '111', '1001', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1751', '111', '1065', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1752', '111', '1002', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1753', '111', '1003', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1754', '111', '1004', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1755', '111', '1005', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1756', '111', '1006', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1757', '111', '1007', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1758', '111', '1008', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1759', '111', '1009', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1760', '111', '1010', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1761', '111', '1011', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1762', '111', '1012', '1', '2022-09-21 22:08:52', '1', '2022-09-21 22:08:52', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1763', '109', '100', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1764', '109', '101', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1765', '109', '1063', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1766', '109', '1064', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1767', '109', '1001', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1768', '109', '1065', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1769', '109', '1002', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1770', '109', '1003', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1771', '109', '1004', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1772', '109', '1005', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1773', '109', '1006', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1774', '109', '1007', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1775', '109', '1008', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1776', '109', '1009', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1777', '109', '1010', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1778', '109', '1011', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1779', '109', '1012', '1', '2022-09-21 22:08:53', '1', '2022-09-21 22:08:53', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1780', '111', '100', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1781', '111', '101', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1782', '111', '1063', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1783', '111', '1064', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1784', '111', '1001', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1785', '111', '1065', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1786', '111', '1002', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1787', '111', '1003', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1788', '111', '1004', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1789', '111', '1005', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1790', '111', '1006', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1791', '111', '1007', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1792', '111', '1008', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1793', '111', '1009', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1794', '111', '1010', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1795', '111', '1011', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1796', '111', '1012', '1', '2022-09-21 22:08:54', '1', '2022-09-21 22:08:54', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1797', '109', '100', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1798', '109', '101', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1799', '109', '1063', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1800', '109', '1064', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1801', '109', '1001', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1802', '109', '1065', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1803', '109', '1002', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1804', '109', '1003', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1805', '109', '1004', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1806', '109', '1005', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1807', '109', '1006', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1808', '109', '1007', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1809', '109', '1008', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1810', '109', '1009', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1811', '109', '1010', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1812', '109', '1011', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1813', '109', '1012', '1', '2022-09-21 22:08:55', '1', '2022-09-21 22:08:55', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1814', '111', '100', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1815', '111', '101', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1816', '111', '1063', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1817', '111', '1064', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1818', '111', '1001', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1819', '111', '1065', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1820', '111', '1002', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1821', '111', '1003', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1822', '111', '1004', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1823', '111', '1005', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1824', '111', '1006', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1825', '111', '1007', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1826', '111', '1008', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1827', '111', '1009', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1828', '111', '1010', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1829', '111', '1011', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1830', '111', '1012', '1', '2022-09-21 22:08:56', '1', '2022-09-21 22:08:56', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1831', '109', '103', '1', '2022-09-21 22:43:23', '1', '2022-09-21 22:43:23', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1832', '109', '1017', '1', '2022-09-21 22:43:23', '1', '2022-09-21 22:43:23', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1833', '109', '1018', '1', '2022-09-21 22:43:23', '1', '2022-09-21 22:43:23', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1834', '109', '1019', '1', '2022-09-21 22:43:23', '1', '2022-09-21 22:43:23', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1835', '109', '1020', '1', '2022-09-21 22:43:23', '1', '2022-09-21 22:43:23', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1836', '111', '103', '1', '2022-09-21 22:43:24', '1', '2022-09-21 22:43:24', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1837', '111', '1017', '1', '2022-09-21 22:43:24', '1', '2022-09-21 22:43:24', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1838', '111', '1018', '1', '2022-09-21 22:43:24', '1', '2022-09-21 22:43:24', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1839', '111', '1019', '1', '2022-09-21 22:43:24', '1', '2022-09-21 22:43:24', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1840', '111', '1020', '1', '2022-09-21 22:43:24', '1', '2022-09-21 22:43:24', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1841', '109', '1036', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1842', '109', '1037', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1843', '109', '1038', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1844', '109', '1039', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1845', '109', '107', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '121');
INSERT INTO `system_role_menu`
VALUES ('1846', '111', '1036', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1847', '111', '1037', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1848', '111', '1038', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1849', '111', '1039', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1850', '111', '107', '1', '2022-09-21 22:48:13', '1', '2022-09-21 22:48:13', '\0', '122');
INSERT INTO `system_role_menu`
VALUES ('1851', '114', '1', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1852', '114', '1036', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1853', '114', '1037', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1854', '114', '1038', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1855', '114', '1039', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1856', '114', '100', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1857', '114', '101', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1858', '114', '1063', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1859', '114', '103', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1860', '114', '1064', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1861', '114', '1001', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1862', '114', '1065', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1863', '114', '1002', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1864', '114', '1003', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1865', '114', '107', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1866', '114', '1004', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1867', '114', '1005', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1868', '114', '1006', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1869', '114', '1007', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1870', '114', '1008', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1871', '114', '1009', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1872', '114', '1010', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1873', '114', '1011', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1874', '114', '1012', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1875', '114', '1017', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1876', '114', '1018', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1877', '114', '1019', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1878', '114', '1020', '1', '2022-12-30 11:32:03', '1', '2022-12-30 11:32:03', '\0', '125');
INSERT INTO `system_role_menu`
VALUES ('1879', '115', '1', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1880', '115', '1036', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1881', '115', '1037', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1882', '115', '1038', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1883', '115', '1039', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1884', '115', '100', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1885', '115', '101', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1886', '115', '1063', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1887', '115', '103', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1888', '115', '1064', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1889', '115', '1001', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1890', '115', '1065', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1891', '115', '1002', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1892', '115', '1003', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1893', '115', '107', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1894', '115', '1004', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1895', '115', '1005', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1896', '115', '1006', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1897', '115', '1007', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1898', '115', '1008', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1899', '115', '1009', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1900', '115', '1010', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1901', '115', '1011', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1902', '115', '1012', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1903', '115', '1017', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1904', '115', '1018', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1905', '115', '1019', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1906', '115', '1020', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_role_menu`
VALUES ('1907', '116', '1', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1908', '116', '1036', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1909', '116', '1037', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1910', '116', '1038', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1911', '116', '1039', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1912', '116', '100', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1913', '116', '101', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1914', '116', '1063', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1915', '116', '103', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1916', '116', '1064', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1917', '116', '1001', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1918', '116', '1065', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1919', '116', '1002', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1920', '116', '1003', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1921', '116', '107', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1922', '116', '1004', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1923', '116', '1005', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1924', '116', '1006', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1925', '116', '1007', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1926', '116', '1008', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1927', '116', '1009', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1928', '116', '1010', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1929', '116', '1011', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1930', '116', '1012', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1931', '116', '1017', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1932', '116', '1018', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1933', '116', '1019', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1934', '116', '1020', '1', '2022-12-30 11:33:48', '1', '2022-12-30 11:33:48', '\0', '127');
INSERT INTO `system_role_menu`
VALUES ('1963', '118', '1', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1964', '118', '1036', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1965', '118', '1037', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1966', '118', '1038', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1967', '118', '1039', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1968', '118', '100', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1969', '118', '101', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1970', '118', '1063', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1971', '118', '103', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1972', '118', '1064', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1973', '118', '1001', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1974', '118', '1065', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1975', '118', '1002', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1976', '118', '1003', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1977', '118', '107', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1978', '118', '1004', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1979', '118', '1005', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1980', '118', '1006', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1981', '118', '1007', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1982', '118', '1008', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1983', '118', '1009', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1984', '118', '1010', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1985', '118', '1011', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1986', '118', '1012', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1987', '118', '1017', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1988', '118', '1018', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1989', '118', '1019', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1990', '118', '1020', '1', '2022-12-30 11:47:52', '1', '2022-12-30 11:47:52', '\0', '129');
INSERT INTO `system_role_menu`
VALUES ('1991', '2', '1024', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1992', '2', '1025', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1993', '2', '1026', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1994', '2', '1027', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1995', '2', '1028', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1996', '2', '1029', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1997', '2', '1030', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1998', '2', '1031', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('1999', '2', '1032', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2000', '2', '1033', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2001', '2', '1034', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2002', '2', '1035', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2003', '2', '1036', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2004', '2', '1037', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2005', '2', '1038', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2006', '2', '1039', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2007', '2', '1040', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2008', '2', '1042', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2009', '2', '1043', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2010', '2', '1045', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2011', '2', '1046', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2012', '2', '1048', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2013', '2', '1050', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2014', '2', '1051', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2015', '2', '1052', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2016', '2', '1053', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2017', '2', '1054', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2018', '2', '1056', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2019', '2', '1057', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2020', '2', '1058', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2021', '2', '2083', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2022', '2', '1059', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2023', '2', '1060', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2024', '2', '1063', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2025', '2', '1064', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2026', '2', '1065', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2027', '2', '1066', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2028', '2', '1067', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2029', '2', '1070', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2030', '2', '1071', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2031', '2', '1072', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2032', '2', '1073', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2033', '2', '1074', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2034', '2', '1075', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2035', '2', '1076', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2036', '2', '1082', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2037', '2', '1085', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2038', '2', '1086', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2039', '2', '1087', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2040', '2', '1088', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2041', '2', '1089', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2042', '2', '1091', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2043', '2', '1092', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2044', '2', '1095', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2045', '2', '1096', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2046', '2', '1097', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2047', '2', '1098', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2048', '2', '1101', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2049', '2', '1102', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2050', '2', '1103', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2051', '2', '1104', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2052', '2', '1105', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2053', '2', '1106', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2054', '2', '1108', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2055', '2', '1109', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2056', '2', '1111', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2057', '2', '1112', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2058', '2', '1113', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2059', '2', '1114', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2060', '2', '1115', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2061', '2', '1127', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2062', '2', '1128', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2063', '2', '1129', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2064', '2', '1130', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2066', '2', '1132', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2067', '2', '1133', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2068', '2', '1134', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2069', '2', '1135', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2070', '2', '1136', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2071', '2', '1137', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2072', '2', '114', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2073', '2', '1139', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2074', '2', '115', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2075', '2', '1140', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2076', '2', '116', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2077', '2', '1141', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2078', '2', '1142', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2079', '2', '1143', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2080', '2', '1150', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2081', '2', '1161', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2082', '2', '1162', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2083', '2', '1163', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2084', '2', '1164', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2085', '2', '1165', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2086', '2', '1166', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2087', '2', '1173', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2088', '2', '1174', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2089', '2', '1175', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2090', '2', '1176', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2091', '2', '1177', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2092', '2', '1178', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2099', '2', '1226', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2100', '2', '1227', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2101', '2', '1228', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2102', '2', '1229', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2103', '2', '1237', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2104', '2', '1238', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2105', '2', '1239', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2106', '2', '1240', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2107', '2', '1241', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2108', '2', '1242', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2109', '2', '1243', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2110', '2', '1247', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2111', '2', '1248', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2112', '2', '1249', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2113', '2', '1250', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2114', '2', '1251', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2115', '2', '1252', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2116', '2', '1254', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2117', '2', '1255', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2118', '2', '1256', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2119', '2', '1257', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2120', '2', '1258', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2121', '2', '1259', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2122', '2', '1260', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2123', '2', '1261', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2124', '2', '1263', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2125', '2', '1264', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2126', '2', '1265', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2127', '2', '1266', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2128', '2', '1267', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2129', '2', '1001', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2130', '2', '1002', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2131', '2', '1003', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2132', '2', '1004', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2133', '2', '1005', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2134', '2', '1006', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2135', '2', '1007', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2136', '2', '1008', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2137', '2', '1009', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2138', '2', '1010', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2139', '2', '1011', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2140', '2', '1012', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2141', '2', '1013', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2142', '2', '1014', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2143', '2', '1015', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2144', '2', '1016', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2145', '2', '1017', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2146', '2', '1018', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2147', '2', '1019', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2148', '2', '1020', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2149', '2', '1021', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2150', '2', '1022', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2151', '2', '1023', '1', '2023-01-25 08:42:52', '1', '2023-01-25 08:42:52', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2152', '2', '1281', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2153', '2', '1282', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2154', '2', '2000', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2155', '2', '2002', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2156', '2', '2003', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2157', '2', '2004', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2158', '2', '2005', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2159', '2', '2006', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2160', '2', '2008', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2161', '2', '2009', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2162', '2', '2010', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2163', '2', '2011', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2164', '2', '2012', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2170', '2', '2019', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2171', '2', '2020', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2172', '2', '2021', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2173', '2', '2022', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2174', '2', '2023', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2175', '2', '2025', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2177', '2', '2027', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2178', '2', '2028', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2179', '2', '2029', '1', '2023-01-25 08:42:58', '1', '2023-01-25 08:42:58', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2180', '2', '2014', '1', '2023-01-25 08:43:12', '1', '2023-01-25 08:43:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2181', '2', '2015', '1', '2023-01-25 08:43:12', '1', '2023-01-25 08:43:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2182', '2', '2016', '1', '2023-01-25 08:43:12', '1', '2023-01-25 08:43:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2183', '2', '2017', '1', '2023-01-25 08:43:12', '1', '2023-01-25 08:43:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2184', '2', '2018', '1', '2023-01-25 08:43:12', '1', '2023-01-25 08:43:12', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2188', '101', '1024', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2189', '101', '1', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2190', '101', '1025', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2191', '101', '1026', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2192', '101', '1027', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2193', '101', '1028', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2194', '101', '1029', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2195', '101', '1030', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2196', '101', '1036', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2197', '101', '1037', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2198', '101', '1038', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2199', '101', '1039', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2200', '101', '1040', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2201', '101', '1042', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2202', '101', '1043', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2203', '101', '1045', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2204', '101', '1046', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2205', '101', '1048', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2206', '101', '2083', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2207', '101', '1063', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2208', '101', '1064', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2209', '101', '1065', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2210', '101', '1093', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2211', '101', '1094', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2212', '101', '1095', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2213', '101', '1096', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2214', '101', '1097', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2215', '101', '1098', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2216', '101', '1100', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2217', '101', '1101', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2218', '101', '1102', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2219', '101', '1103', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2220', '101', '1104', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2221', '101', '1105', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2222', '101', '1106', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2223', '101', '2130', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2224', '101', '1107', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2225', '101', '2131', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2226', '101', '1108', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2227', '101', '2132', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2228', '101', '1109', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2229', '101', '2133', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2230', '101', '2134', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2231', '101', '1110', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2232', '101', '2135', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2233', '101', '1111', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2234', '101', '2136', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2235', '101', '1112', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2236', '101', '2137', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2237', '101', '1113', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2238', '101', '2138', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2239', '101', '1114', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2240', '101', '2139', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2241', '101', '1115', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2242', '101', '2140', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2243', '101', '2141', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2244', '101', '2142', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2245', '101', '2143', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2246', '101', '2144', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2247', '101', '2145', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2248', '101', '2146', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2249', '101', '2147', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2250', '101', '100', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2251', '101', '2148', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2252', '101', '101', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2253', '101', '2149', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2254', '101', '102', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2255', '101', '2150', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2256', '101', '103', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2257', '101', '2151', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2258', '101', '104', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2259', '101', '2152', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2260', '101', '105', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2261', '101', '107', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2262', '101', '108', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2263', '101', '109', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2264', '101', '1138', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2265', '101', '1139', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2266', '101', '1140', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2267', '101', '1141', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2268', '101', '1142', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2269', '101', '1143', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2270', '101', '1224', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2271', '101', '1225', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2272', '101', '1226', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2273', '101', '1227', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2274', '101', '1228', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2275', '101', '1229', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2276', '101', '1247', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2277', '101', '1248', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2278', '101', '1249', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2279', '101', '1250', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2280', '101', '1251', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2281', '101', '1252', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2282', '101', '1261', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2283', '101', '1263', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2284', '101', '1264', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2285', '101', '1265', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2286', '101', '1266', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2287', '101', '1267', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2288', '101', '1001', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2289', '101', '1002', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2290', '101', '1003', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2291', '101', '1004', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2292', '101', '1005', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2293', '101', '1006', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2294', '101', '1007', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2295', '101', '1008', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2296', '101', '1009', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2297', '101', '1010', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2298', '101', '1011', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2299', '101', '1012', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2300', '101', '500', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2301', '101', '1013', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2302', '101', '501', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2303', '101', '1014', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2304', '101', '1015', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2305', '101', '1016', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2306', '101', '1017', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2307', '101', '1018', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2308', '101', '1019', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2309', '101', '1020', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2310', '101', '1021', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2311', '101', '1022', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2312', '101', '1023', '1', '2023-02-09 23:49:46', '1', '2023-02-09 23:49:46', '\0', '1');
INSERT INTO `system_role_menu`
VALUES ('2789', '136', '1', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2790', '136', '1036', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2791', '136', '1037', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2792', '136', '1038', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2793', '136', '1039', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2794', '136', '100', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2795', '136', '101', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2796', '136', '1063', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2797', '136', '103', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2798', '136', '1064', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2799', '136', '1001', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2800', '136', '1065', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2801', '136', '1002', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2802', '136', '1003', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2803', '136', '107', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2804', '136', '1004', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2805', '136', '1005', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2806', '136', '1006', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2807', '136', '1007', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2808', '136', '1008', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2809', '136', '1009', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2810', '136', '1010', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2811', '136', '1011', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2812', '136', '1012', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2813', '136', '1017', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2814', '136', '1018', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2815', '136', '1019', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2816', '136', '1020', '1', '2023-03-05 21:23:32', '1', '2023-03-05 21:23:32', '\0', '147');
INSERT INTO `system_role_menu`
VALUES ('2817', '137', '1', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2818', '137', '1036', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2819', '137', '1037', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2820', '137', '1038', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2821', '137', '1039', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2822', '137', '100', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2823', '137', '101', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2824', '137', '1063', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2825', '137', '103', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2826', '137', '1064', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2827', '137', '1001', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2828', '137', '1065', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2829', '137', '1002', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2830', '137', '1003', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2831', '137', '107', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2832', '137', '1004', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2833', '137', '1005', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2834', '137', '1006', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2835', '137', '1007', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2836', '137', '1008', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2837', '137', '1009', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2838', '137', '1010', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2839', '137', '1011', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2840', '137', '1012', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2841', '137', '1017', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2842', '137', '1018', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2843', '137', '1019', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2844', '137', '1020', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_role_menu`
VALUES ('2845', '138', '1', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2846', '138', '1036', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2847', '138', '1037', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2848', '138', '1038', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2849', '138', '1039', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2850', '138', '100', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2851', '138', '101', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2852', '138', '1063', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2853', '138', '103', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2854', '138', '1064', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2855', '138', '1001', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2856', '138', '1065', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2857', '138', '1002', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2858', '138', '1003', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2859', '138', '107', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2860', '138', '1004', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2861', '138', '1005', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2862', '138', '1006', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2863', '138', '1007', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2864', '138', '1008', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2865', '138', '1009', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2866', '138', '1010', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2867', '138', '1011', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2868', '138', '1012', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2869', '138', '1017', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2870', '138', '1018', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2871', '138', '1019', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2872', '138', '1020', '1', '2023-03-05 21:59:02', '1', '2023-03-05 21:59:02', '\0', '149');
INSERT INTO `system_role_menu`
VALUES ('2873', '139', '1', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2874', '139', '1036', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2875', '139', '1037', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2876', '139', '1038', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2877', '139', '1039', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2878', '139', '100', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2879', '139', '101', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2880', '139', '1063', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2881', '139', '103', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2882', '139', '1064', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2883', '139', '1001', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2884', '139', '1065', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2885', '139', '1002', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2886', '139', '1003', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2887', '139', '107', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2888', '139', '1004', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2889', '139', '1005', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2890', '139', '1006', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2891', '139', '1007', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2892', '139', '1008', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2893', '139', '1009', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2894', '139', '1010', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2895', '139', '1011', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2896', '139', '1012', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2897', '139', '1017', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2898', '139', '1018', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2899', '139', '1019', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');
INSERT INTO `system_role_menu`
VALUES ('2900', '139', '1020', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');

-- ----------------------------
-- Table structure for system_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `system_sms_log`;
CREATE TABLE `system_sms_log`
(
    `id`               bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `mobile`           varchar(11) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '手机号',
    `user_id`          bigint(20)                                       DEFAULT NULL COMMENT '用户编号',
    `user_type`        tinyint(4)                                       DEFAULT NULL COMMENT '用户类型',
    `template_code`    varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板编码',
    `template_type`    tinyint(4)                              NOT NULL COMMENT '短信类型',
    `template_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短信内容',
    `template_params`  varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短信参数',
    `template_id`      varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信 API 的模板编号',
    `create_ip`        varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL,
    `use_ip`           varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL,
    `send_time`        datetime                                         DEFAULT NULL COMMENT '发送时间',
    `error_code`       varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '错误码',
    `err_message`      varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '错误信息',
    `message`          varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '返回消息',
    `biz_id`           varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '回执id',
    `creator`          varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time`      datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time`      datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1723964367770914818
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信日志';

-- ----------------------------
-- Records of system_sms_log
-- ----------------------------

-- ----------------------------
-- Table structure for system_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `system_sms_template`;
CREATE TABLE `system_sms_template`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `status`      tinyint(4)                              NOT NULL COMMENT '开启状态',
    `code`        varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板编码',
    `name`        varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '模板名称',
    `type`        int(1)                                           DEFAULT NULL COMMENT '短信类型',
    `content`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板内容',
    `params`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数数组',
    `remark`      varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `template_id` varchar(63) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '短信 API 的模板编号',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 667
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信模板';

-- ----------------------------
-- Records of system_sms_template
-- ----------------------------
INSERT INTO `system_sms_template`
VALUES ('666', '0', 'admin-sms-login', '登录', '1',
        '亲爱的用户，您正在申请登录，验证码为：{1}，{2}分钟有效，若非本人操作，请勿泄露。', '[\"1\",\"2\"]', null, '1978630',
        '', '2023-11-12 12:41:18', '', '2023-11-13 06:46:25', '\0');

-- ----------------------------
-- Table structure for system_social_user
-- ----------------------------
DROP TABLE IF EXISTS `system_social_user`;
CREATE TABLE `system_social_user`
(
    `id`             bigint(20) unsigned                      NOT NULL AUTO_INCREMENT COMMENT '主键(自增策略)',
    `type`           tinyint(4)                               NOT NULL COMMENT '社交平台的类型',
    `openid`         varchar(32) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '社交 openid',
    `token`          varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '社交 token',
    `raw_token_info` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始 Token 数据，一般是 JSON 格式',
    `nickname`       varchar(32) COLLATE utf8mb4_unicode_ci   NOT NULL COMMENT '用户昵称',
    `avatar`         varchar(255) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '用户头像',
    `raw_user_info`  varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始用户数据，一般是 JSON 格式',
    `code`           varchar(256) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '最后一次的认证 code',
    `state`          varchar(256) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '最后一次的认证 state',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci            DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                                 NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)                                   NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint(20)                               NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='社交用户表';

-- ----------------------------
-- Records of system_social_user
-- ----------------------------

-- ----------------------------
-- Table structure for system_social_user_bind
-- ----------------------------
DROP TABLE IF EXISTS `system_social_user_bind`;
CREATE TABLE `system_social_user_bind`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键(自增策略)',
    `user_id`        bigint(20)          NOT NULL COMMENT '用户编号',
    `user_type`      tinyint(4)          NOT NULL COMMENT '用户类型',
    `social_type`    tinyint(4)          NOT NULL COMMENT '社交平台的类型',
    `social_user_id` bigint(20)          NOT NULL COMMENT '社交用户的编号',
    `creator`        varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time`    datetime            NOT NULL           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time`    datetime            NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)              NOT NULL           DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint(20)          NOT NULL           DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 81
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='社交绑定表';

-- ----------------------------
-- Records of system_social_user_bind
-- ----------------------------

-- ----------------------------
-- Table structure for system_user_post
-- ----------------------------
DROP TABLE IF EXISTS `system_user_post`;
CREATE TABLE `system_user_post`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`     bigint(20) NOT NULL                    DEFAULT '0' COMMENT '用户ID',
    `post_id`     bigint(20) NOT NULL                    DEFAULT '0' COMMENT '岗位ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime   NOT NULL                    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime   NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)     NOT NULL                    DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20) NOT NULL                    DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 118
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户岗位表';

-- ----------------------------
-- Records of system_user_post
-- ----------------------------
INSERT INTO `system_user_post`
VALUES ('112', '1', '1', 'admin', '2022-05-02 07:25:24', 'admin', '2022-05-02 07:25:24', '\0', '1');
INSERT INTO `system_user_post`
VALUES ('113', '100', '1', 'admin', '2022-05-02 07:25:24', 'admin', '2022-05-02 07:25:24', '\0', '1');
INSERT INTO `system_user_post`
VALUES ('114', '114', '3', 'admin', '2022-05-02 07:25:24', 'admin', '2022-05-02 07:25:24', '\0', '1');
INSERT INTO `system_user_post`
VALUES ('115', '104', '1', '1', '2022-05-16 19:36:28', '1', '2022-05-16 19:36:28', '\0', '1');
INSERT INTO `system_user_post`
VALUES ('116', '117', '2', '1', '2022-07-09 17:40:26', '1', '2022-07-09 17:40:26', '\0', '1');
INSERT INTO `system_user_post`
VALUES ('117', '118', '1', '1', '2022-07-09 17:44:44', '1', '2022-07-09 17:44:44', '\0', '1');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `user_id`     bigint(20) NOT NULL COMMENT '用户ID',
    `role_id`     bigint(20) NOT NULL COMMENT '角色ID',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time` datetime                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time` datetime                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                 DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20) NOT NULL                    DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户和角色关联表';

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role`
VALUES ('1', '1', '1', '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:17', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('2', '2', '2', '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:13', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('4', '100', '101', '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:13', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('5', '100', '1', '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:12', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('6', '100', '2', '', '2022-01-11 13:19:45', '', '2022-05-12 12:35:11', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('10', '103', '1', '1', '2022-01-11 13:19:45', '1', '2022-01-11 13:19:45', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('11', '107', '106', '1', '2022-02-20 22:59:33', '1', '2022-02-20 22:59:33', '\0', '118');
INSERT INTO `system_user_role`
VALUES ('12', '108', '107', '1', '2022-02-20 23:00:50', '1', '2022-02-20 23:00:50', '\0', '119');
INSERT INTO `system_user_role`
VALUES ('13', '109', '108', '1', '2022-02-20 23:11:50', '1', '2022-02-20 23:11:50', '\0', '120');
INSERT INTO `system_user_role`
VALUES ('14', '110', '109', '1', '2022-02-22 00:56:14', '1', '2022-02-22 00:56:14', '\0', '121');
INSERT INTO `system_user_role`
VALUES ('15', '111', '110', '110', '2022-02-23 13:14:38', '110', '2022-02-23 13:14:38', '\0', '121');
INSERT INTO `system_user_role`
VALUES ('16', '113', '111', '1', '2022-03-07 21:37:58', '1', '2022-03-07 21:37:58', '\0', '122');
INSERT INTO `system_user_role`
VALUES ('17', '114', '101', '1', '2022-03-19 21:51:13', '1', '2022-03-19 21:51:13', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('18', '1', '2', '1', '2022-05-12 20:39:29', '1', '2022-05-12 20:39:29', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('19', '116', '113', '1', '2022-05-17 10:07:10', '1', '2022-05-17 10:07:10', '\0', '124');
INSERT INTO `system_user_role`
VALUES ('20', '104', '101', '1', '2022-05-28 15:43:57', '1', '2022-05-28 15:43:57', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('22', '115', '2', '1', '2022-07-21 22:08:30', '1', '2022-07-21 22:08:30', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('23', '119', '114', '1', '2022-12-30 11:32:04', '1', '2022-12-30 11:32:04', '\0', '125');
INSERT INTO `system_user_role`
VALUES ('24', '120', '115', '1', '2022-12-30 11:33:42', '1', '2022-12-30 11:33:42', '\0', '126');
INSERT INTO `system_user_role`
VALUES ('25', '121', '116', '1', '2022-12-30 11:33:49', '1', '2022-12-30 11:33:49', '\0', '127');
INSERT INTO `system_user_role`
VALUES ('26', '122', '118', '1', '2022-12-30 11:47:53', '1', '2022-12-30 11:47:53', '\0', '129');
INSERT INTO `system_user_role`
VALUES ('27', '112', '101', '1', '2023-02-09 23:18:51', '1', '2023-02-09 23:18:51', '\0', '1');
INSERT INTO `system_user_role`
VALUES ('28', '123', '136', '1', '2023-03-05 21:23:35', '1', '2023-03-05 21:23:35', '\0', '147');
INSERT INTO `system_user_role`
VALUES ('29', '124', '137', '1', '2023-03-05 21:42:27', '1', '2023-03-05 21:42:27', '\0', '148');
INSERT INTO `system_user_role`
VALUES ('30', '125', '138', '1', '2023-03-05 21:59:03', '1', '2023-03-05 21:59:03', '\0', '149');
INSERT INTO `system_user_role`
VALUES ('31', '126', '139', '1', '2023-07-25 23:06:04', '1', '2023-07-25 23:06:04', '\0', '150');

-- ----------------------------
-- Table structure for system_users
-- ----------------------------
DROP TABLE IF EXISTS `system_users`;
CREATE TABLE `system_users`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(30) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户昵称',
    `remark`      varchar(500) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `dept_id`     bigint(20)                                       DEFAULT NULL COMMENT '部门ID',
    `post_ids`    varchar(255) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '岗位编号数组',
    `email`       varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '用户邮箱',
    `mobile`      varchar(11) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '手机号码',
    `sex`         tinyint(4)                                       DEFAULT '0' COMMENT '用户性别',
    `avatar`      varchar(512) COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '头像地址',
    `status`      tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `login_ip`    varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime                                         DEFAULT NULL COMMENT '最后登录时间',
    `creator`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_username` (`username`, `update_time`, `tenant_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 127
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户信息表';

-- ----------------------------
-- Records of system_users
-- ----------------------------
INSERT INTO `system_users`
VALUES ('1', 'admin', '$2a$10$mRMIYLDtRHlf6.9ipiqH1.Z.bh/R9dO9d5iHiGYPigi6r5KOoR2Wm', '凉茶', '管理员', '103', '[1]',
        'G2494552478@hotmail.com', '19923209856', '1',
        'http://127.0.0.1:48080/admin-api/infra/file/4/get/37e56010ecbee472cdd821ac4b608e151e62a74d9633f15d085aee026eedeb60.png',
        '0', '0:0:0:0:0:0:0:1', '2023-11-13 00:17:25', 'admin', '2021-01-05 17:03:47', null, '2023-11-13 00:17:25',
        '\0', '1');
