package org.monkey.dto.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.monkey.dto.sprinterhu.label.enums.BarcodeType;

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
    private String Barcode;  // Mandatory=YES

    @ApiModelProperty("Barcode type")
    @JSONField(name = "Type")
    private BarcodeType Type;  // Mandatory=YES

    @ApiModelProperty(" ")
    @JSONField(name = "SuplimentJSONData")
    private String suplimentJsonData;  // Mandatory=NO

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
