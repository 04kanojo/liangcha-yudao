package com.liangcha.framework.log.service;

import com.liangcha.system.module.log.domain.OperateLogDO;

/**
 * 操作日志 Framework Service 接口
 *
 * @author 凉茶
 */
public interface OperateLogFrameworkService {

    /**
     * 记录操作日志
     *
     * @param operateLog 操作日志请求
     */
    void createOperateLog(OperateLogDO operateLog);

}
