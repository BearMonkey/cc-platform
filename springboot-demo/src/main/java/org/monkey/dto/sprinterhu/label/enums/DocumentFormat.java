package org.monkey.dto.sprinterhu.label.enums;

import lombok.Getter;

/**
 * DocumentFormat
 *
 * @author cc
 * @since 2024/6/11 14:48
 */
@Getter
public enum DocumentFormat {
    DF_PDF("DF_PDF", "Sign of the PDF format"),
    DF_ZPL("DF_ZPL", "Sign of the ZPL format");

    private final String name;
    private final String remark;


    DocumentFormat(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
