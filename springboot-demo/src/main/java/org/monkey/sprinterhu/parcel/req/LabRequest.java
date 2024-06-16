package org.monkey.sprinterhu.parcel.req;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * LabRequest
 *
 * @author cc
 * @since 2024/6/15 16:34
 */
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class LabRequest {

    @XmlElement(name = "lap1:FinalizeLabelPrinting")
    private Boolean finalizeLabelPrinting;

    @XmlElement(name = "lap1:OnlyLabelPrinting")
    private Boolean onlyLabelPrinting;

    @XmlElement(name = "lap1:ParcelContainer")
    private Lab1ParcelContainer lab1ParcelContainer;

    @XmlElement(name = "lap1:PartnerAddress")
    private String partnerAddress;

    @XmlElement(name = "lap1:PartnerCode")
    private String partnerCode;

    @XmlElement(name = "lap1:Supplier")
    private String supplier;

    @XmlElement(name = "lap1:SupplimentJSONData")
    private String supplimentJSONData;

    @XmlElement(name = "lap1:Token")
    private String token;

    @XmlAttribute(name = "xmlns:lap1")
    private final String xmlnsLab1 = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Messages";
    
}
