package org.monkey.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.szmsd.mps.carrier.api.dto.sprinterhu.label.enums.BarcodeType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BarcodeData
 *
 * @author cc
 * @since 2024/6/11 14:36
 */
@Data
public class BarcodeData {

    @ApiModelProperty("Barcode")
    @JSONField(name = "Barcode")
    private String barcode;  // Mandatory=YES

    @ApiModelProperty("Barcode type")
    @JSONField(name = "Type")
    private BarcodeType type;  // Mandatory=YES

    @ApiModelProperty(" ")
    @JSONField(name = "SuplimentJSONData")
    private String suplimentJsonData;  // Mandatory=NO

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
