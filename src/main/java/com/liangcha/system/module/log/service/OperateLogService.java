package com.liangcha.system.module.log.service;

import com.liangcha.system.module.log.dto.OperateLogCreateReqDTO;


/**
 * 操作日志 Service 接口
 *
 * @author 凉茶
 */
public interface OperateLogService {

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    void createOperateLog(OperateLogCreateReqDTO createReqDTO);

}
