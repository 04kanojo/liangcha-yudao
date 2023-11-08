package com.liangcha.system.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 凉茶
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FileTypeEnum {
    AVATAR("avatar"),

    TEST("test");
    private String type;
}
