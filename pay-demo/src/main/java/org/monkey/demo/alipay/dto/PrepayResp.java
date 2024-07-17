package org.monkey.demo.alipay.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * PrepayResp
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Data
public class PrepayResp {

    private boolean success;

    private String msg;

    private String data;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
