package org.monkey.sprinterhu.label.enums;

import lombok.Getter;

/**
 * DocumentType
 *
 * @author cc
 * @since 2024/6/11 14:57
 */
@Getter
public enum DocumentType {
    DT_All("DT_All", "Sign all types, except if DocumentFormat is DF_ZPL, new request will have to be made with DF_PDF to get all types"),
    DT_PACKAGELABEL("DT_PackageLabel", "Package label, DF_ZPL only returns this type of document"),
    DT_DELIVERYNOTE("DT_DeliveryNote", "Delivery Note"),
    DT_RECEIPTREPORT("DT_ReceiptReport", "Receipt Report");

    private final String name;
    private final String remark;


    DocumentType(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
