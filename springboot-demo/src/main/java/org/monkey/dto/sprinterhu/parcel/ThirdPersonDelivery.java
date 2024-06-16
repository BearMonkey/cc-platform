package org.monkey.dto.sprinterhu.parcel;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ThirdPersonDelivery
 *
 * @author cc
 * @since 2024/6/11 11:36
 */
@Data
public class ThirdPersonDelivery {
    @ApiModelProperty("Sender name")
    @JSONField(name = "Name")
    private String name;            //type=String, Mandatory=YES,  Sender name

    @ApiModelProperty("Sender country code")
    @JSONField(name = "CountryCode")
    private String countryCode;     //type=String, Mandatory=YES,  Sender country code

    @ApiModelProperty("Sender postal code")
    @JSONField(name = "PostalCode")
    private String postalCode;      //type=String, Mandatory=YES,  Sender postal code

    @ApiModelProperty("Sender city")
    @JSONField(name = "City")
    private String city;            //type=String, Mandatory=YES,  Sender city

    @ApiModelProperty("Sender street")
    @JSONField(name = "Street")
    private String street;          //type=String, Mandatory=YES,  Sender street

    @ApiModelProperty("Sender street number")
    @JSONField(name = "StreetNumber")
    private String streetNumber;    //type=String, Mandatory=YES,  Sender street number

    @ApiModelProperty("Sender floor")
    @JSONField(name = "Floor")
    private String floor;           //type=String, Mandatory=NO,   Sender floor

    @ApiModelProperty("Sender room number")
    @JSONField(name = "RoomNumber")
    private String roomNumber;      //type=String, Mandatory=NO,   Sender room number

    @ApiModelProperty("Sender email address")
    @JSONField(name = "EmailAddress")
    private String emailAddress;    //type=String, Mandatory=NO,   Sender email address

    @ApiModelProperty("Sender phone number")
    @JSONField(name = "PhoneNumber")
    private String phoneNumber;     //type=String, Mandatory=NO,   Sender phone number

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
