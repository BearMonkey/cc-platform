package ort.monkey.ccplatform.api.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ort.monkey.ccplatform.api.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class CcOrderDto extends BaseEntity {

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
