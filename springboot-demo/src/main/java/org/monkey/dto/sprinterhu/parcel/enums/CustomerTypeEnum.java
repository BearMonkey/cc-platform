package org.monkey.dto.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * CustomerTypeEnum
 *
 * @author cc
 * @since 2024/6/11 11:07
 */
@Getter
public enum CustomerTypeEnum {
    B2B("B2B", "Business-to-Business type"),
    B2C("B2C", "Business-to-Consumer type"),
    C2C("C2C", "Consumer-to-Consumer type");

    private final String name;
    private final String remark;


    CustomerTypeEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
