package org.monkey.sprinterhu.label.enums;

import lombok.Getter;

/**
 * LabelDocumentSize
 *
 * @author cc
 * @since 2024/6/11 14:55
 */
@Getter
public enum LabelDocumentSize {
    DS_2x2("DS_2x2", "At label printing the A4 size paper is split to 2x2 segments"),
    DS_2x4("DS_2x4", "At label printing the A4 size paper is split to 2x4 segments"),
    DS_A6("DS_A6", "There is one A6 label per page");

    private final String name;
    private final String remark;


    LabelDocumentSize(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
}
