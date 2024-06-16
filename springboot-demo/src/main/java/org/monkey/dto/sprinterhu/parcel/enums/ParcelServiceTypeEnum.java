package org.monkey.dto.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * ParcelServiceTypeEnum
 *
 * @author cc
 * @since 2024/6/11 11:34
 */
@Getter
public enum ParcelServiceTypeEnum {

    NORMAL("Normal", "Normal PPP parcel, dispatch from depot"),
    DIRECT("Direct", "The dispatch point and the destination is the same"),
    POS2POS("Pos2Pos", "Delivery from a PPP to another PPP"),
    HOME_DELIVER("HomeDeliver", "Home delivery, courier performs the delivery"),
    RETURN("Return", "Return parcels sent from a PPP");

    private final String name;
    private final String remark;


    ParcelServiceTypeEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
