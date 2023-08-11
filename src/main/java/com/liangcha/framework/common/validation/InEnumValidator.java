package com.liangcha.framework.common.validation;

import com.liangcha.framework.common.interfaces.IntArr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * InEnum注解的具体实现类
 *
 * @author 凉茶
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Integer> {

    //获取枚举的code码
    private List<Integer> values;

    /**
     * 初始化
     *
     * @param annotation 注解类
     */
    @Override
    public void initialize(InEnum annotation) {
        IntArr[] values = annotation.value().getEnumConstants();
        if (values.length == 0) {
            this.values = Collections.emptyList();
        } else {
            // boxed将int类型的流转为Integer类型
            this.values = Arrays.stream(values[0].getIntArr()).boxed().collect(Collectors.toList());
        }

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 为空时，默认不校验，即认为通过
        if (value == null) {
            return true;
        }
        // 校验通过
        if (values.contains(value)) {
            return true;
        }
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}", values.toString())).addConstraintViolation(); // 重新添加错误提示语句
        return false;
    }

}

