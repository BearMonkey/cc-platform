package org.monkey.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * TestDto
 *
 * @author cc
 * @since 2024/6/4 15:25
 */
@Data
public class TestDto {
    private String name;
    private String no;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
