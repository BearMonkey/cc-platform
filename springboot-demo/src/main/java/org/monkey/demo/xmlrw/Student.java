package org.monkey.demo.xmlrw;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Person
 *
 * @author cc
 * @since 2024/6/12 11:52
 */
@Data
public class Student {
    private String name;

    private int age;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
