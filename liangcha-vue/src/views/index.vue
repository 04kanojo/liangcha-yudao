<template>
  <div class="container">
    <div class="logo"></div>
    <!-- 登录区域 -->
    <div class="content">
      <!-- 配图 -->
      <div class="pic"></div>
      <!-- 表单 -->
      <div class="field">
        <!-- [移动端]标题 -->
        <h2 class="mobile-title">
          <h3 class="title">芋道后台管理系统</h3>
        </h2>

        <!-- 表单 -->
        <div class="form-cont">
          <el-tabs class="form" v-model="loginForm.loginType" style=" float:none;">
            <el-tab-pane label="账号密码登录" name="uname">
            </el-tab-pane>
            <el-tab-pane label="短信验证码登录" name="sms">
            </el-tab-pane>
          </el-tabs>
          <div>
            <el-form ref="loginForm" :model="loginForm" :rules="LoginRules" class="login-form">
              <el-form-item prop="tenantName" v-if="tenantEnable">
                <el-input v-model="loginForm.tenantName" type="text" auto-complete="off" placeholder='租户'>
                  <svg-icon slot="prefix" icon-class="tree" class="el-input__icon input-icon"/>
                </el-input>
              </el-form-item>
              <!-- 账号密码登录 -->
              <div v-if="loginForm.loginType === 'uname'">
                <el-form-item prop="username">
                  <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
                    <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
                  </el-input>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                            @keyup.enter.native="getCode">
                    <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
                  </el-input>
                </el-form-item>
                <el-checkbox v-model="loginForm.rememberMe" style="margin:0 0 25px 0;">记住密码</el-checkbox>
              </div>

              <!-- 短信验证码登录 -->
              <div v-if="loginForm.loginType === 'sms'">
                <el-form-item prop="mobile">
                  <el-input v-model="loginForm.mobile" type="text" auto-complete="off" placeholder="请输入手机号">
                    <svg-icon slot="prefix" icon-class="phone" class="el-input__icon input-icon"/>
                  </el-input>
                </el-form-item>
                <el-form-item prop="mobileCode">
                  <el-input v-model="loginForm.mobileCode" type="text" auto-complete="off" placeholder="短信验证码"
                            class="sms-login-mobile-code-prefix"
                            @keyup.enter.native="handleLogin">
                    <template>
                      <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
                    </template>
                    <template slot="append">
                      <span v-if="mobileCodeTimer <= 0" class="getMobileCode" @click="getSmsCode"
                            style="cursor: pointer;">获取验证码</span>
                      <span v-if="mobileCodeTimer > 0" class="getMobileCode">{{ mobileCodeTimer }}秒后可重新获取</span>
                    </template>
                  </el-input>
                </el-form-item>
              </div>

              <!-- 下方的登录按钮 -->
              <el-form-item style="width:100%;">
                <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
                           @click.native.prevent="getCode">
                  <span v-if="!loading">登 录</span>
                  <span v-else>登 录 中...</span>
                </el-button>
              </el-form-item>

              <!--  社交登录 -->
              <el-form-item style="width:100%;">
                <div class="oauth-login" style="display:flex">
                  <div class="oauth-login-item" v-for="item in SysUserSocialTypeEnum" :key="item.type"
                       @click="doSocialLogin(item)">
                    <img :src="item.img" height="25px" width="25px" alt="登录">
                    <span>{{ item.title }}</span>
                  </div>
                </div>
              </el-form-item>

              <!-- 教程说明 -->
              <el-form-item style="width:100%; margin-top:-25px">
                <el-link href="https://doc.iocoder.cn/" target="_blank">📚开发指南</el-link>
                <el-link href="https://doc.iocoder.cn/video/" target="_blank" style="padding-left: 10px">🔥视频教程
                </el-link>
                <el-link href="https://www.iocoder.cn/Interview/good-collection/" target="_blank"
                         style="padding-left: 10px">⚡面试手册
                </el-link>
                <el-link href="http://static.yudao.iocoder.cn/mp/Aix9975.jpeg" target="_blank"
                         style="padding-left: 10px">🤝外包咨询
                </el-link>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>

    <!--    &lt;!&ndash; 图形验证码 &ndash;&gt;-->
    <!--    <Verify ref="verify" :captcha-type="'blockPuzzle'" :img-size="{width:'400px',height:'200px'}"-->
    <!--            @success="handleLogin"/>-->

    <!-- footer -->
    <div class="footer">
      Copyright © 2020-2022 iocoder.cn All Rights Reserved.
    </div>
  </div>
</template>

<script>
export default {
  name: "index"
}
</script>

<style scoped>

</style>
