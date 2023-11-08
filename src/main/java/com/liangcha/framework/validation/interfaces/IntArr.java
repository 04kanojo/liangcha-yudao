package com.liangcha.framework.validation.interfaces;

/**
 * 返回int类型的数组(因为stream流处理数组只能转为int类型)
 * <p>
 * 服务于枚举类校验
 *
 * @author 凉茶
 */
public interface IntArr {
    int[] getIntArr();
}
