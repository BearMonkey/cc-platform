package org.monkey.dto.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * PackageTypeEnum
 *
 * @author cc
 * @since 2024/6/11 11:11
 */
@Getter
public enum PackageTypeEnum {

    SMALL("Small", "Small", "X: 20cm, Y: 30cm, Z: 10cm, Volume: 0.006000m3"),
    MEDIUM("Medium", "Medium", "X: 30cm, Y: 30cm, Z: 20cm, Volume: 0.018000m3"),
    LARGE("Large", "Large", "X: 50cm, Y: 50cm, Z: 50cm, Volume: 0.125000m3"),
    SPECIAL("Special", "Special", "X: 60cm, Y: 60cm, Z: 60cm, Volume: 0.216000m3"),
    NONE("None", "Size is not given", "");

    private final String name;
    private final String remark;
    private final String size;



    PackageTypeEnum(String name, String remark, String size) {
        this.name = name;
        this.remark = remark;
        this.size = size;
    }
}
