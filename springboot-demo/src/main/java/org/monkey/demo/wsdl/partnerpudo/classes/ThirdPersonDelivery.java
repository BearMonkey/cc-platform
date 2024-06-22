package org.monkey.demo.wsdl.partnerpudo.classes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * ThirdPersonDelivery
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ThirdPersonDelivery {

    @XmlElement(name = "City", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "City")
    private String city;

    @XmlElement(name = "CountryCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CountryCode")
    private String countryCode;

    @XmlElement(name = "EmailAddress", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "EmailAddress")
    private String emailAddress;

    @XmlElement(name = "Floor", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "Floor")
    private String floor;

    @XmlElement(name = "Name", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "Name")
    private String name;

    @XmlElement(name = "PhoneNumber", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PhoneNumber")
    private String phoneNumber;

    @XmlElement(name = "PostalCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PostalCode")
    private String postalCode;

    @XmlElement(name = "RoomNumber", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "RoomNumber")
    private String roomNumber;

    @XmlElement(name = "Street", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "Street")
    private String street;


    @XmlElement(name = "StreetNumber", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "StreetNumber")
    private String streetNumber;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
