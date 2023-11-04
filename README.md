# 说明

无意间发现gitee上[ruoyi-vue-yuedao](https://gitee.com/zhijiantianya/ruoyi-vue-pro)项目,决定在模仿此项目进行学习(
大部分一样,作者名字全部被改成本人,如有问题请联系我)

## 使用技术

**说明:无先后顺序**

1. Hutool
2. JetCache
3. JustAuth
4. Knife4j
5. Mapstruct
6. Maven
7. MybatisPlus
8. Redis
9. SpringSecurity
10. SpringBoot

## 更新日志

|     时间     |        新增功能        |                  优化功能                  |
|:----------:|:------------------:|:--------------------------------------:|
| 2023/10/9  | 1.管理员登录<br>2.验证码获取 |                                        |
| 2023/10/10 |  短信登录系统(半成品)<br/>  |                                        |
| 2023/10/11 |                    | 1.缓存使用jetcache<br/>2.修复全局异常捕捉参数校验时无法捕捉 |
| 2023/10/13 |   完成关于token的全部操作   |                                        |
| 2023/10/15 |     数据权限(半成品)      |                                        |
| 2023/10/16 |                    |  1.权限部分将使用mysql改为使用redis<br/>2.更改项目结构  |
| 2023/10/17 |                    |   1.新增权限配置类注入bean<br/>2.过期时间提取成配置文件    |
| 2023/10/18 |        数据权限        |                                        |
| 2023/10/26 |   操作日志记录和登录日志记录    |              lombok提出配置文件              |
| 2023/11/2  |     OAuth2授权认证     |                                        |

## 联系方式

**邮箱 G2494552478@hotmail.com**

## 技术要点记录

### 关于基本架构,以及类的创建

- **_dto是各种地方传递使用,例如OperateLogFrameworkServiceImpl这个类使用OperateLogService的方法
  ,便传递dto,但是如果是前端调用接口,给controller,再给service,即可以用vo_**

### 数据权限

#### @Permission注解使用

_**假如要给selectUserByUsername添加数据权限功能,在selectUserByUsername上添加注解并无效果
,因为此方法最后调用的是selectOne, 所以要重写selectOne方法,并且把注解加到selectOne方法上
,否则无法实现数据权限**_

### oauth2认证流程

_**1.申请授权码(/oauth2/authorize)<br>
2.使用授权码获取访问令牌(/oauth2/token)<br>
3.使用访问令牌获取用户信息(/..)**_

实现用户一个地方登录使用websocket。监听用户状态，在线聊天等
token和refresh会存入两个，排查原因，其次继续调整oauth2流程
