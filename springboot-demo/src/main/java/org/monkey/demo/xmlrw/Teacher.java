package org.monkey.demo.xmlrw;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

/**
 * Teacher
 *
 * @author cc
 * @since 2024/6/12 13:57
 */
@Data
public class Teacher {
    private String name;
    private String type;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
