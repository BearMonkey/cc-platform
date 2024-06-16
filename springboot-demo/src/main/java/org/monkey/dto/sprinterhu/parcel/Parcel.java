package org.monkey.dto.sprinterhu.parcel;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.monkey.dto.sprinterhu.parcel.enums.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Parcel
 *
 * @author cc
 * @since 2024/6/11 10:33
 */
@Data
public class Parcel {
    @ApiModelProperty("Payment deadline ")
    @JSONField(name = "AccountingDeadlineDate")
    private Date accountingDeadlineDate;          // type=dateTime                Mandatory=No

    @ApiModelProperty("Order code, which is the partner’s own parcel code or invoice number (if an invoice can be found in the box). ")
    @JSONField(name = "AutorizationCode")
    private String autorizationCode;                    // type=string (28)             Mandatory=Yes

    @ApiModelProperty("Parcel barcode ")
    @JSONField(name = "BarCode")
    private String barCode;                             // type=string (25)             Mandatory=YES

    @ApiModelProperty("Contact name ")
    @JSONField(name = "ContactName")
    private String contactName;                         // type=string (64)             Mandatory=NO

    @ApiModelProperty("Customer’s address (street) ")
    @JSONField(name = "CustomerAddress")
    private String customerAddress;                     // type=string (256)            Mandatory=YES

    @ApiModelProperty("Customer’s address (city) ")
    @JSONField(name = "CustomerCity")
    private String customerCity;                        // type=string (25)             Mandatory=YES

    @ApiModelProperty("Customer code, if it  exists ")
    @JSONField(name = "CustomerCode")
    private String customerCode;                        // type=string (10)             Mandatory=No

    @ApiModelProperty("Customer’s country code ")
    @JSONField(name = "CustomerCountryCode")
    private String customerCountryCode;                 // type=string (2)              Mandatory=NO

    @ApiModelProperty("Customer’s e-mail address ")
    @JSONField(name = "CustomerEmail")
    private String customerEmail;                       // type=string (64)             Mandatory=YES

    @ApiModelProperty("Customer’s name ")
    @JSONField(name = "CustomerName")
    private String customerName;                        // type=string (256)            Mandatory=YES

    @ApiModelProperty("Customer’s mobile phone number for sending SMS  notification, format:  +36201234567 ")
    @JSONField(name = "CustomerPhone")
    private String customerPhone;                       // type=string (64)             Mandatory=YES

    @ApiModelProperty("Customer’s postal code ")
    @JSONField(name = "CustomerPostalCode")
    private String customerPostalCode;                  // type=string (8)              Mandatory=YES

    @ApiModelProperty("Customer’s region/county ")
    @JSONField(name = "CustomerRegion")
    private String customerRegion;                      // type=string (40)             Mandatory=NO

    @ApiModelProperty("Customer’s house number, and further address details (floor, door) ")
    @JSONField(name = "CustomerStreetNumber")
    private String customerStreetNumber;                // type=string (40)             Mandatory=YES

    @ApiModelProperty("Customer type (always B2C) ")
    @JSONField(name = "CustomerType")
    private CustomerTypeEnum customerType;                  // type=CustomerType            Mandatory=NO

    @ApiModelProperty("This field will be discontinued. Previously it contained the transport fee; currently the system calculates the value of the field. ")
    @JSONField(name = "DeliveryPrice")
    private BigDecimal deliveryPrice;                   // type=decimal(9,2)            Mandatory=NO

    @ApiModelProperty("Currency of the transport fee ")
    @JSONField(name = "DeliveryPriceCurrency")
    private String deliveryPriceCurrency;               // type=string(3)               Mandatory=NO

    @ApiModelProperty("It defines the delivery type. In case of ")
    @JSONField(name = "DeliveryType")
    private ParcelDeliveryTypeEnum deliveryType;            // type=ParcelDeliveryType      Mandatory=HD

    @ApiModelProperty("Remarks about the package and/or message for the courier ")
    @JSONField(name = "Description")
    private String description;                         // type=string (100)            Mandatory=NO

    @ApiModelProperty("The ID of destination PPP, where the parcel has to be delivered. If the destination is the central depot of Lapker, then 2000 has to be used as destination code. Always ShopCode has to be given. YES In case of ")
    @JSONField(name = "DestinationLocationId")
    private String destinationLocationId;               // type=string (10)             Mandatory=PPP

    @ApiModelProperty("This field will be discontinued. Barcode of Direct parcel type ")
    @JSONField(name = "DirectPackageBarCode")
    private String directPackageBarCode;                // type=string(28)              Mandatory=NO

    @ApiModelProperty("This field will be discontinued.Invoice number placed in the parcel, AuthorizationCode takes its place. ")
    @JSONField(name = "InvoiceNumber")
    private String invoiceNumber;                       // type=string (64)             Mandatory=NO

    @ApiModelProperty("This field sets whether the invoice has to be made out for the Partner or not. ")
    @JSONField(name = "IsPartnerInvoiced")
    private boolean isPartnerInvoiced;                  // type=boolean                 Mandatory=NO

    @ApiModelProperty("Value of the parcel (That value is charged in case of compensation). Maximum value: 13 000 000 HUF. Only integer numbers can be given. Decimals will be rounded by the system according to matachematic rules of rounding. ")
    @JSONField(name = "PackagePrice")
    private BigDecimal packagePrice;                    // type=decimal(9,2)            Mandatory=YES

    @ApiModelProperty("Currency of parcel price, default HUF ")
    @JSONField(name = "PackagePriceCurrency")
    private String packagePriceCurrency;                // type=string(3)               Mandatory=YES

    @ApiModelProperty("Parcel size In case of ")
    @JSONField(name = "PackageSizeX")
    private BigDecimal packageSizeX;                    // type=decimal                 Mandatory=HD

    @ApiModelProperty("Parcel size In case of ")
    @JSONField(name = "PackageSizeY")
    private BigDecimal packageSizeY;                    // type=decimal                 Mandatory=HD

    @ApiModelProperty("Parcel size In case of ")
    @JSONField(name = "PackageSizeZ")
    private BigDecimal packageSizeZ;                    // type=decimal                 Mandatory=HD

    @ApiModelProperty("Parcel type according to the size")
    @JSONField(name = "PackageType")
    private PackageTypeEnum packageType;                    // type=PackageType             Mandatory=In case of PPP

    @ApiModelProperty("Volume of the parcel (m3) ")
    @JSONField(name = "PackageVolume")
    private BigDecimal PackageVolume;                    // type=PackageType             Mandatory=In case of HD

    @ApiModelProperty("Weight of the parcel in kg, maximum weight is 20kg")
    @JSONField(name = "PackageWeight")
    private BigDecimal PackageWeight;                    // type=PackageType             Mandatory=In case of HD

    @ApiModelProperty("Description of the parcel content ")
    @JSONField(name = "ParcelContentDescription1")
    private String parcelContentDescription1;           // type=string (64)             Mandatory=NO

    @ApiModelProperty("Product name ")
    @JSONField(name = "ParcelContentDescription2")
    private String parcelContentDescription2;           // type=string (64)             Mandatory=NO

    @ApiModelProperty("The quantity of the parcels, which have to be delivered together in case of HD. The parcel number within a Parcel object can be given in this field. E.g.: ParcelCount = 100 means, that 100 pieces of parcels will be registered.In case of ")
    @JSONField(name = "ParcelCount")
    private Integer parcelCount;                        // type=int                     Mandatory=HD

    @ApiModelProperty("The actual status of parcel creation, the request has to be sent always with “Create” status. ")
    @JSONField(name = "ParcelCreationStatus")
    private ParcelCreationStatusEnum parcelCreationStatus;  // type=ParcelCreationStatus    Mandatory=YES

    @ApiModelProperty("Sender name ")
    @JSONField(name = "PickupCustomerName")
    private String pickupCustomerName;                  // type=string (64)             Mandatory=NO

    @ApiModelProperty("Dispatch location, ShopCode has to be given. In case of ")
    @JSONField(name = "PickupLocationId")
    private String pickupLocationId;                    // type=string (10)             Mandatory=POS2POS

    @ApiModelProperty("lAddress Sender’s email address ")
    @JSONField(name = "PickupNotificationEmailAddress")
    private String pickupNotificationEmailAddress;              // type=string (64)             Mandatory=NO

    @ApiModelProperty("Sender’s phone number ")
    @JSONField(name = "PickupNotificationPhone")
    private String pickupNotificationPhone;             // type=string (32)             Mandatory=NO

    @ApiModelProperty("Cash on delivery, price to be paid at the parcel handover. Maximum amount: 700 000 HUF. Only integer numbers can be given. Decimals will be rounded by the system according to matachematic rules of  rounding. ")
    @JSONField(name = "PriceAtDelivery")
    private BigDecimal priceAtDelivery;                 // type=decimal(9,2)            Mandatory=YES

    @ApiModelProperty("COD currency, default HUF ")
    @JSONField(name = "PriceAtDeliveryCurrency")
    private String priceAtDeliveryCurrency;             // type=string (3)              Mandatory=YES

    @ApiModelProperty("Reference barcode, concerned values are displayed in the accounting files. In case of document return this will be a reference data for the return parcel in the system. ")
    @JSONField(name = "ReferenceBarCode")
    private String referenceBarCode;                    // type=string (28)             Mandatory=NO

    @ApiModelProperty("Date of the dispatch ")
    @JSONField(name = "RegisterDate")
    private Date registerDate;                          // type=dateTime                Mandatory=NO

    @ApiModelProperty("Claim for returning a document. In case of Pick Pack Point parcels the ReferenceBarcode field is to be filled. ")
    @JSONField(name = "ReturnOfDocument")
    private boolean returnOfDocument;                   // type=boolean                 Mandatory=NO

    @ApiModelProperty("Delivery type. It can be decided based on the given value at parcel registration which service type has to be applied at the parcel. ")
    @JSONField(name = "ServiceType")
    private ParcelServiceTypeEnum serviceType;              // type=ParcelServiceType       Mandatory=YES

    @ApiModelProperty("Other, specific data can be sent in this field. ")
    @JSONField(name = "SupplimentJSONData")
    private String suplimentJsonData;                  // type=string                  Mandatory=NO

    @ApiModelProperty("This field will be discontinued. Claim for Tracking  information ")
    @JSONField(name = "Tracking")
    private boolean tracking;                           // type=boolean                 Mandatory=NO

    @ApiModelProperty("Delivery time. Possible values are: " +
            "0 – Before 9 o’clock " +
            "1 – forenoon between 9-12 o’clock  " +
            "2 - 1 workday  " +
            "3 - 2 workdays  " +
            "4 - 3 workdays  " +
            "5 - 5 workdays In case of HD")
    @JSONField(name = "TransitTime")
    private Integer transitTime;                        // type=int                     Mandatory=HD

    @ApiModelProperty("Senders data in case of third person delivery  type. ")
    @JSONField(name = "ThirdPersonDelivery")
    private ThirdPersonDelivery thirdPersonDelivery;    // type=ThirdPersonDelivery     Mandatory=NO


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
