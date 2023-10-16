package com.liangcha.common.enums;

import com.liangcha.framework.validation.interfaces.IntArr;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 通用状态枚举
 *
 * @author 凉茶
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements IntArr {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public int[] getIntArr() {
        return Arrays.stream(values()).mapToInt(CommonStatusEnum::getStatus).toArray();
    }
}
