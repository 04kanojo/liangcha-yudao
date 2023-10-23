package com.liangcha.framework.log.service;

import com.liangcha.framework.convert.log.OperateLogConvert;
import com.liangcha.system.log.domain.OperateLogDO;
import com.liangcha.system.log.dto.OperateLogCreateReqDTO;
import com.liangcha.system.log.service.OperateLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 操作日志 Framework Service 实现类
 *
 * @author 凉茶
 */
@Component
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    @Resource
    private OperateLogService logService;

    @Override
    @Async
    public void createOperateLog(OperateLogDO operateLog) {
        OperateLogCreateReqDTO reqDTO = OperateLogConvert.INSTANCE.convert(operateLog);
        logService.createOperateLog(reqDTO);
    }

}
