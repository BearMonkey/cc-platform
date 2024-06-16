package org.monkey.dto.sprinterhu.tracking;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * TrackingInfo
 *
 * @author cc
 * @since 2024/6/11 17:12
 */
@Data
public class TrackingInfo {

    @ApiModelProperty("Unique tracking identifier. With which it is possible to check, if we have already sent this data or not.")
    @JSONField(name = "TrackingID")
    private Integer TrackingID;

    @ApiModelProperty("Parcel identifier. This is the parcel’s identifier for logistic")
    @JSONField(name = "ParcelBarcode")
    private String ParcelBarcode;

    @ApiModelProperty("Time of the status change")
    @JSONField(name = "EventCreatedTime")
    private Date EventCreatedTime;

    @ApiModelProperty("Delivery mode, its possible values: PICKPACKPOINT or HOMEDELIVERY")
    @JSONField(name = "DeliveryMode")
    private String DeliveryMode;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
