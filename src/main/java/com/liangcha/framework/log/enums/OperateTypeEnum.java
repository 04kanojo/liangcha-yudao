package com.liangcha.framework.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志的操作类型
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {

    /**
     * 其它
     */
    OTHER(0),

    /**
     * 查询
     */
    GET(1),

    /**
     * 新增
     */
    CREATE(2),

    /**
     * 修改
     */
    UPDATE(3),

    /**
     * 删除
     */
    DELETE(4),

    /**
     * 导出
     */
    EXPORT(5),

    /**
     * 导入
     */
    IMPORT(6);

    /**
     * 类型
     */
    private final Integer type;

}
