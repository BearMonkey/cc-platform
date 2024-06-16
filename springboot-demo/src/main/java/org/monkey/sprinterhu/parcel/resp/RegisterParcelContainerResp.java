package org.monkey.sprinterhu.parcel.resp;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * RegisterParcelContainerResp
 *
 * @author cc
 * @since 2024/6/11 11:45
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
public class RegisterParcelContainerResp {

    @XmlAttribute(name = "xmlns")
    private String xmlns;

    @XmlElement(name = "RegisterParcelContainerResult")
    private RegisterParcelContainerResult registerParcelContainerResult;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
