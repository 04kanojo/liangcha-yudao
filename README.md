## 🐟说明

**此项目仅供交流学习**

## 🐇前言

**无意间发现gitee上[ruoyi-vue-yuedao](https://gitee.com/zhijiantianya/ruoyi-vue-pro)项目
，决定在从0开始搭建框架，模仿此项目进行学习**

本人目前是个大三学生，因为是学习阶段，所有大部分代码是很像的，
但是不代表一模一样，我融入了自己的想法，
比如OAuth权限部分，我把数据库设计全部改为了Redis，验证码使用的Hutool等等。

**注：**

- _本项目只是实现了相关功能，但是不代表每个应该使用的地方都在使用，
  大部分只用在了一个地方（进行测试），比如权限校验板块，
  项目本身只在OAuth2授权范围认证使用。_
- _Java类与数据库部分字段可能不对应导致报错，
  请自行更改（服务器密码泄露导致最开始使用的数据库被修改，
  于是更换了一个数据库，但是我懒得再去找数据表差异了_

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

| 项目名            | GitHub地址                                   | Gitee地址                                   |
|----------------|--------------------------------------------|-------------------------------------------|
| liangcha-yudao | https://github.com/04kanojo/liangcha-yudao | https://gitee.com/kanojo39/liangcha-yudao |

## 🐹后端更新日志

|     时间     |         新增功能          |                        优化功能                         |
|:----------:|:---------------------:|:---------------------------------------------------:|
| 2023/10/09 |    管理员登录<br/>验证码获取    |                                                     |
| 2023/10/10 |      短信登录系统(半成品)      |                                                     |
| 2023/10/11 |                       |         缓存使用jetcache<br/>修复全局异常捕捉参数校验时无法捕捉          |
| 2023/10/13 |    完成关于token的全部操作     |                                                     |
| 2023/10/15 |       数据权限(半成品)       |                                                     |
| 2023/10/16 |                       |          权限部分将使用mysql改为使用redis<br/>更改项目结构           |
| 2023/10/17 |                       |            新增权限配置类注入bean<br/>过期时间提取成配置文件            |
| 2023/10/18 |         数据权限          |                                                     |
| 2023/10/26 |     操作日志记录和登录日志记录     |                    lombok提出配置文件                     |
| 2023/11/04 |      OAuth2授权码认证      |                                                     |
| 2023/11/05 |      OAuth2刷新码认证      |   更改accessToken存入redis键(防止客户端拿accessToken做过分的事情)    |
| 2023/11/06 |     OAuth2密码模式认证      | 解决第三方登录无法通过security上下文获取，走过滤器的时候没存，因为只存默认客户端，做了些许优化 |
| 2023/11/08 |     上传文件<br/>预览文件     |                                                     |
| 2023/11/09 |         下载文件          |                     修复上传文件若干问题                      |
| 2023/11/11 |         删除文件          |                      优化刷新token                      |
| 2023/11/12 |    短信登录<br/>短信日志记录    |                                                     |
| 2023/11/14 |                       |                 优化短信日志记录<br/>修改项目结构                 |
| 2024/4/10  | 开启商城模块<br/>完成商品品牌部分功能 |                                                     |

## 🐼技术要点记录

- **关于基本架构，以及类的创建**

```
 dto是各种地方传递使用，例如OperateLogFrameworkServiceImpl这个类使用OperateLogService的方法
  ，便传递dto，但是如果是前端调用接口，给controller，再给service，即可以用vo
  ```

- **数据权限**

```
@Permission注解使用

假如要给selectUserByUsername添加数据权限功能，在selectUserByUsername上添加注解并无效果
，因为此方法最后调用的是selectOne， 所以要重写selectOne方法，并且把注解加到selectOne方法上
        ，否则无法实现数据权限
  ```

- OAuth2认证流程

```
一.授权码模式
1.申请授权码(/oauth2/authorize)
2.使用授权码获取访问令牌(/oauth2/token)

二.密码模式
申请授权码(/oauth2/token)
  ```

- **操作权限**

```
@PreAuthorize注解使用
此注解分为三种模式：
1.scope：根据数据授权校验
2.role：角色校验
3.permission：菜单校验
  ```

- **docker**

```
基本参数
-p 端口映射
-d 后台运行
-v 文件映射
--name 容器名称
```

```
创建mysql容器
docker run --name mysql
-p 3306:3306 # 端口映射
-v /data/mysql/conf/my.cnf:/etc/mysql/my.cnf
-v /data/mysql/data:/var/lib/mysql
-v /data/mysql/log:/var/log/mysql
-e MYSQL_ROOT_PASSWORD=root
--restart=always #容器的重启策略，如果容器因任何原因退出，Docker 将自动重新启动容器。always 策略确保容器无论退出状态如何都会被重新启动。
-d mysql:5.7.30
```

```
创建nginx容器
docker run --name nginx
-d -p 80:80
-v /root/docker/nginx/conf.d/default.conf:/etc/nginx/conf.d/default.conf
-v /root/docker/nginx/logs:/var/log/nginx
-v /root/docker/nginx/nginx-config/html:/usr/share/nginx/html
nginx


附上配置文件（default.conf）
server {
#服务器端口使用443，开启ssl, 这里ssl就是上面安装的ssl模块
listen 80;
listen 443 ssl;
#域名，多个空格分开
server_name www.liangchay.cn liangchay.cn;

    #ssl证书地址
    ssl_certificate /nginx_ssl/liangchay.cn_bundle.crt;
    ssl_certificate_key /nginx_ssl/liangchay.cn.key;

    #ssl验证相关配置
    ssl_session_timeout 5m;    #缓存有效期
    ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;    #加密算法
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;    #安全链接可选的加密协议
    ssl_prefer_server_ciphers off;   #使用服务器端的首选算法

    location / {
        root /usr/share/nginx/html;
        index index.html index.htm;
    }

}
 ```

## 🐾展望未来

* **完成第三方登录(必须要网站开发完成才能申请)**
* **完成支付功能**
* **使用websocket监听用户状态，实现上下线提醒，在线聊天等**
* **项目改造成cloud项目**

****

## 🐣写在最后

**感谢你看到了这里，如果文档或者项目有什么不合适的地方，
项目存在bug请联系我，比如作者名字全部换成了本人(sorry，强迫症)**

**🐻邮箱 ： G2494552478@hotmail.com**<br>
**🐻微信 ： yibeiliangchay**

### bug随笔
