package org.monkey.dto.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * ParcelDeliveryTypeEnum
 *
 * @author cc
 * @since 2024/6/11 11:09
 */
@Getter
public enum ParcelDeliveryTypeEnum {

    THIRD_PERSON_DELIVERY("ThirdPersonDelivery", "Third person delivery"),
    DELIVERY_AND_RETURNS("DeliveryAndReturns", "Delivery and return"),
    ONLY_DELIVERY("OnlyDelivery", "Only delivery"),
    ONLY_RETURNS("OnlyReturns", "Only return"),
    NONE("None", "Default value, the system automatically sets as OnlyDelivery");

    private final String name;
    private final String remark;


    ParcelDeliveryTypeEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
