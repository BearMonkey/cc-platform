package org.monkey.wxpay.model.queryorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cc
 */
@Data
public class CommRespPayerInfo {

    /**
     * 【用户标识】 用户标识
     * 选填
     */
    private String openId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
