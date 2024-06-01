package org.monkey.platform.api.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("订单")
public class CcOrder extends BaseEntity{
    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("商品id")
    private Integer goodsId;

    @ApiModelProperty("订单状态")
    private String status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
