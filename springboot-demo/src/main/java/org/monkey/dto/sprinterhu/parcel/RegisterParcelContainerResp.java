package org.monkey.dto.sprinterhu.parcel;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * RegisterParcelContainerResp
 *
 * @author cc
 * @since 2024/6/11 11:45
 */
@Data
public class RegisterParcelContainerResp {

    @ApiModelProperty("Error code")
    @JSONField(name = "ErrorCode")
    private String errorCode;  // type=String

    @ApiModelProperty("Parcel registration result, information")
    @JSONField(name = "ParcelResults")
    private List<ParcelResult> parcelResults;  // type=List<ParcelResult>

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
