package org.monkey.demo.alipay.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * OrdOrder
 *
 * @author Monkey
 * @since 2024/6/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrdOrder extends BaseDto {

    private String orderNo;

    private BigDecimal amount;

    private List<GoodsInfo> goodsInfoList = new ArrayList<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
