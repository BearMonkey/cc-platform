package org.monkey.sprinterhu.parcel;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.szmsd.mps.carrier.api.dto.sprinterhu.parcel.enums.SupplierEnum;
import com.szmsd.mps.carrier.api.dto.sprinterhu.parcel.req.Parcel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * RegisterParcelContainerReq
 *
 * @author cc
 * @since 2024/6/11 10:23
 */
@Data
public class RegisterParcelContainerReq {

    @ApiModelProperty("Partner (sender) code, Mandatory=YES Type=string (10)")
    @JSONField(name = "PartnerCode")
    private String partnerCode;

    @ApiModelProperty("This field will be discontinued. Partner address, Mandatory=NO Type=string")
    @JSONField(name = "PartnerAddress")
    private String partnerAddress;

    @ApiModelProperty("Bunch of parcel data, which contains the parcel’s list to be registered. " +
            "It depends on the setting of the Parcel item, which service type, Mandatory=YES Type=List<Parcel>")
    @JSONField(name = "ParcelContainer")
    private List<Parcel> parcelContainer;

    @ApiModelProperty("Expected delivery time. It is not shown on any surface. Mandatory=NO Type=Date")
    @JSONField(name = "ArriveDate")
    private Date arriveDate;

    @ApiModelProperty("This field will be discontinued.ENUM for differentiating the supplier type. " +
            "It is set based on PPP and HD supplier types stored in the Master data. " +
            "Currently the field must be filled per Partner. Mandatory=YES Type=Supplier")
    @JSONField(name = "Supplier")
    private SupplierEnum supplier;

    @ApiModelProperty("A field verifiying the partner’s package sending. 10 character long, " +
            "it contains non-capital letters and numbers. Mandatory=YES Type=string (10)")
    @JSONField(name = "Token")
    private String token;

    @ApiModelProperty("Data transfer for the sole purpose of label printing Mandatory=NO Type=bool")
    @JSONField(name = "OnlyLabelPrinting")
    private boolean onlyLabelPrinting;

    @ApiModelProperty("Finalizing label printing, transfering the package register data Mandatory=NO Type=bool")
    @JSONField(name = "FinalizeLabelPrinting")
    private boolean finalizeLabelPrinting;

    @ApiModelProperty("Other data, Mandatory=NO Type=string")
    @JSONField(name = "SupplimentJSONData")
    private String suplimentJsonData;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
