package org.monkey.demo.mybatisplus.enums;

import lombok.Getter;

/**
 * GenderEnum
 *
 * @author cc
 * @since 2024/5/31 10:38
 */
@Getter
public enum DelFlagEnum {

    USEING("0", "使用中"),
    DEL("1", "已删除"),
    Other("2","其他");

    private final String code;
    private final String value;

    DelFlagEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 根据code 获取 value
     * @param code : 0:女, 1:男, 2:其他
     * @return value
     */
    public static String getValueByCode(String code) {
        for (DelFlagEnum genderEnum : DelFlagEnum.values()) {
            if (genderEnum.getCode().equals(code)) {
                return genderEnum.getValue();
            }
        }
        return DelFlagEnum.Other.getValue();
    }


    /**
     * 根据value 获取 code
     * @param value value
     * @return code
     */
    public static String getCodeByValue(String value) {
        for (DelFlagEnum genderEnum : DelFlagEnum.values()) {
            if (genderEnum.getValue().equals(value)) {
                return genderEnum.getCode();
            }
        }
        return DelFlagEnum.Other.getCode();
    }
}
