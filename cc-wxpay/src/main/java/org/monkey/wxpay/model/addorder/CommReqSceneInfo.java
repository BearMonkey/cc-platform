package org.monkey.wxpay.model.addorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CommReqSceneInfo {

    /**
     * 【用户终端IP】 用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。
     * 必填
     */
    private String payerClientIp;

    /**
     * 【商户端设备号】 商户端设备号（门店号或收银设备ID）。
     * 选填
     */
    private String deviceId;

    /**
     * 【商户门店信息】 商户门店信息
     * 选填
     */
    private StoreInfo storeInfo;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
