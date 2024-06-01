package org.monkey.platform.api.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CcRegion extends BaseEntity {
    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("上一级id")
    private Integer pid;

    @ApiModelProperty("地点名称")
    private String name;

    @ApiModelProperty("英文名称")
    private String enName;

    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("状态")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
