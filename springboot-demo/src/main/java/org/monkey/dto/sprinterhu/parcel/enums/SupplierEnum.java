package org.monkey.dto.sprinterhu.parcel.enums;

import lombok.Getter;

/**
 * SupplierEnum
 *
 * @author cc
 * @since 2024/6/11 11:04
 */
@Getter
public enum SupplierEnum {

    PARTNER("Partner", "Partner brings the parcel"),
    LAPKER("Lapker", "Lapker brings the parcel"),
    CUSTOMER("Customer", "Customer brings the customer");

    private final String name;
    private final String remark;


    SupplierEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
