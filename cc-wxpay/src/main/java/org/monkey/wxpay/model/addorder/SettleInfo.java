package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class SettleInfo {

    /**
     * 【是否指定分账】 是否指定分账，
     * 枚举值:
     * true：是
     * false：否
     * 选填
     */
    private Boolean profitSharing;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
