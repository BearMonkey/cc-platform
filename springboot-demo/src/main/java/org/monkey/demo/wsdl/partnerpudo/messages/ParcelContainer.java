package org.monkey.demo.wsdl.partnerpudo.messages;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.monkey.demo.wsdl.partnerpudo.classes.Parcel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ParcelContainer
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ParcelContainer {

    @XmlElement(name = "Parcel", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "Parcel")
    private Parcel parcel;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
