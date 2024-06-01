package org.monkey.platform.user.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * LoginResp
 *
 * @author cc
 * @since 2024/6/1 18:06
 */
@Data
@ApiModel
public class LoginResp {
    private String token;
    private String username;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
