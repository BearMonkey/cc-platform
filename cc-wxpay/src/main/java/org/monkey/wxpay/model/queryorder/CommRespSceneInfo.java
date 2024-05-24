package org.monkey.wxpay.model.queryorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 【场景信息】 场景信息
 * @author cc
 */
@Data
public class CommRespSceneInfo {

    /**
     * 【商户端设备号】 商户端设备号
     * 选填
     */
    private String deviceId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
