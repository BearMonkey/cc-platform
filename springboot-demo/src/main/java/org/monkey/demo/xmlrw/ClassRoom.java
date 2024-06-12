package org.monkey.demo.xmlrw;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassRoom
 *
 * @author cc
 * @since 2024/6/12 11:52
 */
@Data
@XmlRootElement(name = "class-room")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassRoom {
    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "teacher")
    private Teacher teacher;

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
