package org.monkey.wxpay.model.closeorder;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 该接口无应答包体，目前保持为空
 * @author cc
 */
@Data
public class CloseOrderResp {


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
