package org.monkey.platform.auth.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * CcLogin
 *
 * @author cc
 * @since 2024/6/7 11:37
 */
@Data
public class CcLogin {
    private String token;  // token
    private String username; // 用户名
    private String source; // source

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
