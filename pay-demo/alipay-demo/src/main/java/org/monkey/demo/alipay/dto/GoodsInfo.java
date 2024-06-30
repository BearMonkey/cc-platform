package org.monkey.demo.alipay.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * GoodsInfo
 *
 * @author Monkey
 * @since 2024/6/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cc_goods")
public class GoodsInfo extends BaseDto {
    private String goodsName;

    private Integer quantity;

    private BigDecimal unitPrice;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
