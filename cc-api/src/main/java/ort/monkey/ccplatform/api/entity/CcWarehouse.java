package ort.monkey.ccplatform.api.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CcWarehouse extends BaseEntity {

    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("国家id")
    private Integer warehouseCountryId;

    @ApiModelProperty("省份id")
    private Integer warehouseProvinceId;

    @ApiModelProperty("城市id")
    private Integer warehouseCityId;

    @ApiModelProperty("地区id")
    private Integer warehouseAreaId;

    @ApiModelProperty("详细地址")
    private String warehouseAddr;

    @ApiModelProperty("状态")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
