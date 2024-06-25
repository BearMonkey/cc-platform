package org.monkey.demo.wsdl.partnerpudo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.monkey.demo.wsdl.partnerpudo.messages.ParcelContainer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RequestData
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RequestData {

    @XmlElement(name = "ArriveDate", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "ArriveDate")
    private String arriveDate;

    @XmlElement(name = "FinalizeLabelPrinting", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "FinalizeLabelPrinting")
    private Boolean finalizeLabelPrinting;

    @XmlElement(name = "OnlyLabelPrinting", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "OnlyLabelPrinting")
    private Boolean onlyLabelPrinting;

    @XmlElement(name = "ParcelContainer", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "ParcelContainer")
    private ParcelContainer parcelContainer;

    @XmlElement(name = "PartnerAddress", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "PartnerAddress")
    private String partnerAddress;

    @XmlElement(name = "PartnerCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "PartnerCode")
    private String partnerCode;

    @XmlElement(name = "Supplier", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "Supplier")
    private String supplier;

    @XmlElement(name = "SupplimentJSONData", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "SupplimentJSONData")
    private String supplimentJSONData;

    @XmlElement(name = "Token", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo")
    @JSONField(name = "Token")
    private String token;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
