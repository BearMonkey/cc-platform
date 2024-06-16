package org.monkey.sprinterhu.parcel.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * LabRegisterParcelContainer
 *
 * @author cc
 * @since 2024/6/15 17:11
 */
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class LabRegisterParcelContainer {

    @XmlElement(name = "lap:request")
    private LabRequest labRequest;
}
