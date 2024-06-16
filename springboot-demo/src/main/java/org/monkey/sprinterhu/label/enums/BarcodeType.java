package org.monkey.sprinterhu.label.enums;

import lombok.Getter;

/**
 * BarcodeType
 *
 * @author cc
 * @since 2024/6/11 14:37
 */
@Getter
public enum BarcodeType {
    BT_PACKAGE_BARCODE("BT_PackageBarcode", "Parcel Barcode"),
    BT_SHIPMENT_BARCODE("BT_ShipmentBarcode", "Shipment Barcode");

    private final String name;
    private final String remark;


    BarcodeType(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

}
