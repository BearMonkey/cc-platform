package org.monkey.dto.sprinterhu.tracking;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * UnifiedParcelTrackingInfo
 *
 * @author cc
 * @since 2024/6/11 17:10
 */
@Data
public class UnifiedParcelTrackingInfo {

    @ApiModelProperty("Partner (Sender) identifier")
    @JSONField(name = "PartnerCode")
    private String PartnerCode;


    @ApiModelProperty("List of tracking data")
    @JSONField(name = "TrackingInfoList")
    private List<TrackingInfo> TrackingInfoList = new ArrayList<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
