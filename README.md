## 🐟说明

**无意间发现gitee上[ruoyi-vue-yuedao](https://gitee.com/zhijiantianya/ruoyi-vue-pro)项目 ,决定在从0开始搭建框架模仿此项目进行学习
**

本人目前是个大三学生，因为是学习阶段，所有大部分代码是很像的，
但是不代表一模一样，我加入了自己的想法，
比如OAuth权限部分,我把数据库设计全部改为了redis，验证码使用的Hutool等等

## 🐶使用技术

**无先后顺序**

* **Hutool**
* **JetCache**
* **JustAuth**
* **Kkfileview**
* **Knife4j**
* **Mapstruct**
* **Maven**
* **Minio**
* **MybatisPlus**
* **Redis**
* **SpringBoot**
* **SpringSecurity**

## 🐷项目传送门

| 项目名            | GitHub地址                                   |
|----------------|--------------------------------------------|
| liangcha-yudao | https://github.com/04kanojo/liangcha-yudao |

## 🐹后端更新日志

|     时间     |      新增功能       |                        优化功能                         |
|:----------:|:---------------:|:---------------------------------------------------:|
| 2023/10/9  | 管理员登录<br/>验证码获取 |                                                     |
| 2023/10/10 |   短信登录系统(半成品)   |                                                     |
| 2023/10/11 |                 |         缓存使用jetcache<br/>修复全局异常捕捉参数校验时无法捕捉          |
| 2023/10/13 | 完成关于token的全部操作  |                                                     |
| 2023/10/15 |    数据权限(半成品)    |                                                     |
| 2023/10/16 |                 |          权限部分将使用mysql改为使用redis<br/>更改项目结构           |
| 2023/10/17 |                 |            新增权限配置类注入bean<br/>过期时间提取成配置文件            |
| 2023/10/18 |      数据权限       |                                                     |
| 2023/10/26 |  操作日志记录和登录日志记录  |                    lombok提出配置文件                     |
| 2023/11/4  |   OAuth2授权码认证   |                                                     |
| 2023/11/5  |   OAuth2刷新码认证   |   更改accessToken存入redis键(防止客户端拿accessToken做过分的事情)    |
| 2023/11/6  |  OAuth2密码模式认证   | 解决第三方登录无法通过security上下文获取，走过滤器的时候没存，因为只存默认客户端，做了些许优化 |
|  2023/118  |      上传文件       |                                                     |

## 🐼技术要点记录

- **关于基本架构,以及类的创建**

```
 dto是各种地方传递使用,例如OperateLogFrameworkServiceImpl这个类使用OperateLogService的方法
  ,便传递dto,但是如果是前端调用接口,给controller,再给service,即可以用vo
  ```

- **数据权限**

@Permission注解使用

```
假如要给selectUserByUsername添加数据权限功能,在selectUserByUsername上添加注解并无效果
        ,因为此方法最后调用的是selectOne, 所以要重写selectOne方法,并且把注解加到selectOne方法上
        ,否则无法实现数据权限
  ```

- OAuth2认证流程

```
一.授权码模式
1.申请授权码(/oauth2/authorize)
2.使用授权码获取访问令牌(/oauth2/token)

二.密码模式
申请授权码(/oauth2/token)
  ```

## 🐾展望未来

* **使用此框架完成实训和毕设**
* **完成第三方登录和短信登录(申请原因：必须要网站开发完成)**
* **使用websocket监听用户状态，实现上下线提醒，在线聊天等**
* **项目改造成cloud项目**

****

## 🐣写在最后

**感谢你看到了这里，如果文档或者项目有什么不合适的地方，
项目存在超大的bug请联系我，比如作者名字全部换成了本人(sorry，强迫症)**

**🐻邮箱 ： G2494552478@hotmail.com**

### bug随笔

用户使用refreshToken刷新令牌,refreshToken未删除之前的
