package org.monkey.dto.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * MassDocumentRequest
 *
 * @author cc
 * @since 2024/6/11 14:35
 */
@Data
public class MassDocumentReq {

    @ApiModelProperty("Barcode list")
    @JSONField(name = "Barcodes")
    private List<BarcodeData> Barcodes = new ArrayList<>(0);  // Mandatory=YES

    @ApiModelProperty("Document Settings")
    @JSONField(name = "DocumentSettings")
    private List<DocumentSetting> DocumentSettings = new ArrayList<>(0);  // Mandatory=YES

    @ApiModelProperty(" ")
    @JSONField(name = "SuplimentJSONData")
    private String suplimentJsonData; // Mandatory=NO

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
