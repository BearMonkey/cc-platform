package org.monkey.sprinterhu.parcel.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Lab1ParcelContainer
 *
 * @author cc
 * @since 2024/6/15 16:38
 */
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class Lab1ParcelContainer {

    @XmlElement(name = "lap2:Parcel")
    private Parcel parcel;

    @XmlAttribute(name = "xmlns:lap2")
    private final String xmlnsLab2 = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes";
}
