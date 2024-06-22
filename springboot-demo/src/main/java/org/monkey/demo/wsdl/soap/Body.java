package org.monkey.demo.wsdl.soap;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.monkey.demo.wsdl.partnerpudo.RegisterParcelContainer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Body
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Body {
    @XmlElement(name = "RegisterParcelContainer", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "RegisterParcelContainer")
    private RegisterParcelContainer registerParcelContainer;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
