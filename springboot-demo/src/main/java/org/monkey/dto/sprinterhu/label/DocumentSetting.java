package org.monkey.dto.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.monkey.dto.sprinterhu.label.enums.DocumentFormat;
import org.monkey.dto.sprinterhu.label.enums.DocumentType;
import org.monkey.dto.sprinterhu.label.enums.LabelDocumentPosition;
import org.monkey.dto.sprinterhu.label.enums.LabelDocumentSize;

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
