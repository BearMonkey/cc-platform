package org.monkey.platform.user.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * LoginDto
 *
 * @author cc
 * @since 2024/6/1 18:04
 */
@ApiModel
@Data
public class LoginDto {
    private String username;
    private String password;
    private String code;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
