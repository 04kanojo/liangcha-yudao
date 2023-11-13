package com.liangcha.system.module.log.service;

import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.convert.log.OperateLogConvert;
import com.liangcha.system.module.log.dao.OperateLogMapper;
import com.liangcha.system.module.log.domain.OperateLogDO;
import com.liangcha.system.module.log.dto.OperateLogCreateReqDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 凉茶
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        OperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        // 查询数据转换为json可能很多
        logDO.setJavaMethodArgs(StrUtil.maxLength(logDO.getJavaMethodArgs(), OperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtil.maxLength(logDO.getResultData(), OperateLogDO.RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

}
