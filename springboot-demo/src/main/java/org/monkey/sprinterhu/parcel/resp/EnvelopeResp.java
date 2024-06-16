package org.monkey.sprinterhu.parcel.resp;

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
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class EnvelopeResp {

    @XmlElement(name = "s:Body")
    private SoapenvBody body;

    @XmlAttribute(name = "xmlns:s")
    private String xmlnsS;
}
