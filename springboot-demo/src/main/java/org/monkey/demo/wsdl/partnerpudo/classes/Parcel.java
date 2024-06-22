package org.monkey.demo.wsdl.partnerpudo.classes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * Parcel
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Parcel {

    @XmlElement(name = "AccountingDeadlineDate", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "AccountingDeadlineDate")
    private String accountingDeadlineDate;

    @XmlElement(name = "AutorizationCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "AutorizationCode")
    private String autorizationCode;

    @XmlElement(name = "BarCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "BarCode")
    private String barCode;

    @XmlElement(name = "ContactName", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ContactName")
    private String contactName;

    @XmlElement(name = "CustomerAddress", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerAddress")
    private String customerAddress;

    @XmlElement(name = "CustomerCity", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerCity")
    private String customerCity;

    @XmlElement(name = "CustomerCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerCode")
    private String customerCode;

    @XmlElement(name = "CustomerCountryCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerCountryCode")
    private String customerCountryCode;

    @XmlElement(name = "CustomerEmail", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerEmail")
    private String customerEmail;

    @XmlElement(name = "CustomerName", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerName")
    private String customerName;

    @XmlElement(name = "CustomerPhone", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerPhone")
    private String customerPhone;

    @XmlElement(name = "CustomerPostalCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerPostalCode")
    private String customerPostalCode;

    @XmlElement(name = "CustomerRegion", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerRegion")
    private String customerRegion;

    @XmlElement(name = "CustomerStreetNumber", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerStreetNumber")
    private String customerStreetNumber;

    @XmlElement(name = "CustomerType", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "CustomerType")
    private String customerType;

    @XmlElement(name = "DeliveryPrice", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "DeliveryPrice")
    private String deliveryPrice;

    @XmlElement(name = "DeliveryPriceCurrency", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "DeliveryPriceCurrency")
    private String deliveryPriceCurrency;

    @XmlElement(name = "DeliveryType", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "DeliveryType")
    private String deliveryType;

    @XmlElement(name = "Description", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "Description")
    private String description;

    @XmlElement(name = "DestinationLocationId", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "DestinationLocationId")
    private Boolean destinationLocationId;

    @XmlElement(name = "DirectPackageBarCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "DirectPackageBarCode")
    private String directPackageBarCode;

    @XmlElement(name = "InvoiceNumber", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "InvoiceNumber")
    private String invoiceNumber;

    @XmlElement(name = "IsPartnerInvoiced", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "IsPartnerInvoiced")
    private Boolean partnerInvoiced;

    @XmlElement(name = "PackagePrice", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackagePrice")
    private BigDecimal packagePrice;

    @XmlElement(name = "PackagePriceCurrency", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackagePriceCurrency")
    private String packagePriceCurrency;

    @XmlElement(name = "PackageSizeX", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageSizeX")
    private BigDecimal packageSizeX;

    @XmlElement(name = "PackageSizeY", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageSizeY")
    private BigDecimal packageSizeY;

    @XmlElement(name = "PackageSizeZ", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageSizeZ")
    private BigDecimal packageSizeZ;

    @XmlElement(name = "PackageType", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageType")
    private String packageType;

    @XmlElement(name = "PackageVolume", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageVolume")
    private BigDecimal packageVolume;

    @XmlElement(name = "PackageWeight", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PackageWeight")
    private BigDecimal packageWeight;

    @XmlElement(name = "ParcelContentDescription1", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ParcelContentDescription1")
    private String parcelContentDescription1;

    @XmlElement(name = "ParcelContentDescription2", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ParcelContentDescription2")
    private String parcelContentDescription2;

    @XmlElement(name = "ParcelCount", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ParcelCount")
    private Integer parcelCount;

    @XmlElement(name = "ParcelCreationStatus", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ParcelCreationStatus")
    private String parcelCreationStatus;

    @XmlElement(name = "PickupCustomerName", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PickupCustomerName")
    private String pickupCustomerName;

    @XmlElement(name = "PickupLocationId", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PickupLocationId")
    private String pickupLocationId;

    @XmlElement(name = "PickupNotificationEmailAddress", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PickupNotificationEmailAddress")
    private String pickupNotificationEmailAddress;

    @XmlElement(name = "PickupNotificationPhone", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PickupNotificationPhone")
    private String pickupNotificationPhone;

    @XmlElement(name = "PriceAtDelivery", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PriceAtDelivery")
    private BigDecimal priceAtDelivery;

    @XmlElement(name = "PriceAtDeliveryCurrency", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "PriceAtDeliveryCurrency")
    private String priceAtDeliveryCurrency;

    @XmlElement(name = "ReferenceBarCode", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ReferenceBarCode")
    private String referenceBarCode;

    @XmlElement(name = "RegisterDate", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "RegisterDate")
    private String registerDate;

    @XmlElement(name = "ReturnOfDocument", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ReturnOfDocument")
    private Boolean returnOfDocument;

    @XmlElement(name = "ServiceType", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ServiceType")
    private String serviceType;

    @XmlElement(name = "SupplimentJSONData", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "SupplimentJSONData")
    private String supplimentJSONData;

    @XmlElement(name = "ThirdPersonDelivery", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "ThirdPersonDelivery")
    private ThirdPersonDelivery thirdPersonDelivery;

    @XmlElement(name = "Tracking", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "Tracking")
    private Boolean tracking;

    @XmlElement(name = "TransitTime", namespace = "http://Lapker.Pudo.PudoService.Interface.PartnerPudo.Classes")
    @JSONField(name = "TransitTime")
    private String transitTime;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
