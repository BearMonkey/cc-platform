package org.monkey.platform.auth.enums;

import lombok.Getter;

/**
 * TokenStorageEnum
 *
 * @author cc
 * @since 2024/6/7 11:44
 */
@Getter
public enum TokenStorageEnum {

    DB("DB", "storeTokenToDb"),
    REDIS("Redis", "storeTokenToRedis");

    private final String name;
    private final String value;

    TokenStorageEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static TokenStorageEnum getByValue(String value) {
        for (TokenStorageEnum tokenStorageEnum : TokenStorageEnum.values()) {
            if (tokenStorageEnum.getValue().equals(value)) {
                return tokenStorageEnum;
            }
        }
        return TokenStorageEnum.DB; // 默认方式
    }
}
