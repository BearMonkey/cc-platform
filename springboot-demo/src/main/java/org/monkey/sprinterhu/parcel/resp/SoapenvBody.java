package org.monkey.sprinterhu.parcel.resp;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * SoapenvBody
 *
 * @author cc
 * @since 2024/6/15 16:34
 */
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class SoapenvBody {

    @XmlElement(name = "RegisterParcelContainerResponse")
    private RegisterParcelContainerResp registerParcelContainerResp;
}
