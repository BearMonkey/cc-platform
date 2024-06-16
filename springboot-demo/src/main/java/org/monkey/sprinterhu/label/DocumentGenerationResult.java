package org.monkey.sprinterhu.label;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DocumentGenerationResult
 *
 * @author cc
 * @since 2024/6/11 15:04
 */
@Data
public class DocumentGenerationResult {

    @ApiModelProperty("Name of the generated document based on value of DocumentSettings field.")
    @JSONField(name = "DocumentName")
    private String DocumentName;

    @ApiModelProperty("Result information of the generated document")
    @JSONField(name = "Result")
    private String Result;

    @ApiModelProperty(" ")
    @JSONField(name = "ResultMessage")
    private String ResultMessage;

    @ApiModelProperty(" ")
    @JSONField(name = "SuplimentJSONData")
    private String suplimentJsonData;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
