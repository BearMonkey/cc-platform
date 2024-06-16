package org.monkey.dto.sprinterhu.test;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Person
 *
 * @author cc
 * @since 2024/6/13 10:47
 */
@Data
public class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
