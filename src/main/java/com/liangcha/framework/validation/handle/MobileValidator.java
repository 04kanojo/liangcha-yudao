package com.liangcha.framework.validation.handle;

import cn.hutool.core.util.StrUtil;
import com.liangcha.framework.validation.annotation.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author 凉茶
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private Pattern MOBILE;

    @Override
    public void initialize(Mobile annotation) {
        //初始化手机号的正则表达式
        MOBILE = Pattern.compile("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[0,1,4-9])|(?:5[0-3,5-9])|(?:6[2,5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[0-3,5-9]))\\d{8}$");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        // 校验手机
        return StringUtils.hasText(value)
                && MOBILE.matcher(value).matches();
    }

}
