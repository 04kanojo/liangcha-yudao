package com.liangcha.system.module.log.service;


import com.liangcha.system.module.log.dto.LoginLogCreateReqDTO;

import javax.validation.Valid;

/**
 * 登录日志 Service 接口
 *
 * @author 凉茶
 */
public interface LoginLogService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateReqDTO reqDTO);
}
