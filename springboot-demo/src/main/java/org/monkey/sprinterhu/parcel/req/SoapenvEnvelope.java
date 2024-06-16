package org.monkey.sprinterhu.parcel.req;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * RegisterReq
 *
 * @author cc
 * @since 2024/6/15 16:30
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "soapenv:Envelope")
public class SoapenvEnvelope {
    @XmlElement(name = "soapenv:Header")
    private SoapenvHeader header;

    @XmlElement(name = "soapenv:Body")
    private SoapenvBody body;

    @XmlAttribute(name = "xmlns:soapenv")
    private final String xmlnsSoapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    @XmlAttribute(name = "xmlns:lap")
    private final String xmlnsLap = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo";
}
