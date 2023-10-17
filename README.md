# 说明

无意间发现gitee上[ruoyi-vue-yuedao](https://gitee.com/zhijiantianya/ruoyi-vue-pro)项目火了,决定在模仿此项目进行学习(
大部分一样,作者名字都被改成我的了,如有问题请联系我)

## 使用技术

_**说明:此列表无先后顺序,按照首字母进行排序,如有未列出的技术,请联系我进行修改**_

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
| 2023/10/9  | 1.管理员登录<br>2.验证码获取 |                   /                    |
| 2023/10/10 |  短信登录系统(半成品)<br/>  |                   /                    |
| 2023/10/11 |         /          | 1.缓存使用jetcache<br/>2.修复全局异常捕捉参数校验时无法捕捉 |
| 2023/10/13 |   完成关于token的全部操作   |                   /                    |
| 2023/10/15 |     数据权限(半成品)      |                   /                    |
| 2023/10/16 |         /          |         1.大改权限部分<br/>2.更改项目结构          |

## 联系方式

**邮箱 G2494552478@hotmail.com**

##

平时记录:jecache缓存token和refreshtoken都未存入redis,存入的时候没有部门和角色
找到原因了,passwordEncode注入(气死我了)

解决方案:查看yudao源码看这个放在哪的,给@bean password换个地方
