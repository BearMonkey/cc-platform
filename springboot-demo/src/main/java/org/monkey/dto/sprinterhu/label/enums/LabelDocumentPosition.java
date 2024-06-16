package org.monkey.dto.sprinterhu.label.enums;

import lombok.Getter;

/**
 * LabelDocumentPosition
 *
 * @author cc
 * @since 2024/6/11 14:49
 */
@Getter
public enum LabelDocumentPosition {
                        // 2x2  2x4
    P_0("P_0"),   //  1    1
    P_1("P_1"),   //  2    2
    P_2("P_2"),   //  3    3
    P_3("P_3"),   //  4    4
    P_4("P_4"),   //  -    5
    P_5("P_5"),   //  -    6
    P_6("P_6"),   //  -    7
    P_7("P_7");   //  -    8

    private final String name;


    LabelDocumentPosition(String name) {
        this.name = name;
    }
}
