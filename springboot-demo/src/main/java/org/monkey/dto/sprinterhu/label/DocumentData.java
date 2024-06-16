package org.monkey.dto.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.monkey.dto.sprinterhu.label.enums.DocumentType;

/**
 * DocumentData
 *
 * @author cc
 * @since 2024/6/11 15:07
 */
@Data
public class DocumentData {

    @ApiModelProperty("This byte block should contain the generated document in case of PDF")
    @JSONField(name = "Document")
    private String Document;

    @ApiModelProperty("This string should contain the generated ZPL code in case of ZPL")
    @JSONField(name = "DocumentString")
    private String DocumentString;

    @ApiModelProperty("Name of the generated document based on value of DocumentSettings field.")
    @JSONField(name = "DocumentName")
    private String DocumentName;

    @ApiModelProperty("Label or not")
    @JSONField(name = "IsPositioned")
    private boolean IsPositioned;

    @ApiModelProperty(" ")
    @JSONField(name = "SupplimentJSONData")
    private String suplimentJsonData;

    @ApiModelProperty("Type of the generated document.")
    @JSONField(name = "Type")
    private DocumentType Type;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
