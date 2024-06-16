package org.monkey.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * MassDocumentResp
 *
 * @author cc
 * @since 2024/6/11 14:59
 */
@Data
public class MassDocumentResp {

    @ApiModelProperty("Document printed Status based on the barcodes.")
    @JSONField(name = "DocumentGenerationResults")
    private List<DocumentGenerationResult> DocumentGenerationResults = new ArrayList<>(0);

    @ApiModelProperty("Document belongs to the barcode.")
    @JSONField(name = "Documents")
    private List<DocumentData> Documents = new ArrayList<>(0);

    @ApiModelProperty("Result response of the document generation success")
    @JSONField(name = "Result")
    private String Result;

    @ApiModelProperty(" ")
    @JSONField(name = "SuplimentJSONData")
    private String suplimentJsonData;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
