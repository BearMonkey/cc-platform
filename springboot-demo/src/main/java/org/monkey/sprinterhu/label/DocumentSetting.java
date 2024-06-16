package org.monkey.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.szmsd.mps.carrier.api.dto.sprinterhu.label.enums.DocumentFormat;
import com.szmsd.mps.carrier.api.dto.sprinterhu.label.enums.DocumentType;
import com.szmsd.mps.carrier.api.dto.sprinterhu.label.enums.LabelDocumentPosition;
import com.szmsd.mps.carrier.api.dto.sprinterhu.label.enums.LabelDocumentSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DocumentSetting
 *
 * @author cc
 * @since 2024/6/11 14:36
 */
@Data
public class DocumentSetting {

    @ApiModelProperty("can be DF_PDF or DF_ZPL")
    @JSONField(name = "Format")
    private DocumentFormat Format;  // Mandatory=YES

    @ApiModelProperty("true = label false = A4 sized page")
    @JSONField(name = "IsPositioned")
    private boolean IsPositioned;  // Mandatory=NO

    @ApiModelProperty("From P_0 To P_7")
    @JSONField(name = "Position")
    private LabelDocumentPosition Position;  // Mandatory=NO

    @ApiModelProperty("2x2 or 2x4 or A6")
    @JSONField(name = "Size")
    private LabelDocumentSize Size;  // Mandatory=NO

    @ApiModelProperty("Barcode")
    @JSONField(name = "SupplimentJSONData")
    private String suplimentJsonData;  // Mandatory=NO

    @ApiModelProperty("Label/Delivery note/Proof of receipt or each of them")
    @JSONField(name = "Type")
    private DocumentType Type;  // Mandatory=NO

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
