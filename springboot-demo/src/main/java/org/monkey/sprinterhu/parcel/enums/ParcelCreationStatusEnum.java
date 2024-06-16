package org.monkey.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * ParcelCreationStatusEnum
 *
 * @author cc
 * @since 2024/6/11 11:33
 */
@Getter
public enum ParcelCreationStatusEnum {

    CREATE("Create", "Creation of a parcel");

    private final String name;
    private final String remark;


    ParcelCreationStatusEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
