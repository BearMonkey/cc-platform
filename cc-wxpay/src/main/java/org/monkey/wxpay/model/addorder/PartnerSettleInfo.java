package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 【结算信息】 结算信息
 * @author cc
 */
@Data
public class PartnerSettleInfo {

    /**
     * 【是否指定分账】 是否指定分账，
     * 枚举值:
     *   - true：是
     *   - false：否
     */
    private boolean profitSharing;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
