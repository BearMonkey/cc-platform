package org.monkey.demo.wsdl.partnerpudo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * RegisterParcelContainer
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RegisterParcelContainer {

    @XmlElement(name = "request", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "request")
    private RequestData request;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
