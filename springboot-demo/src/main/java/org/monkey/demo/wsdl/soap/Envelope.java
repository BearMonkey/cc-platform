package org.monkey.demo.wsdl.soap;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * Envelope
 *
 * @author Monkey
 * @since 2024/6/22
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Envelope")
@XmlType(
        name = "Envelope",
        propOrder = {
                "header", "body"
        },
        namespace = "http://schemas.xmlsoap.org/soap/envelope/"
)
public class Envelope {
    @JSONField(name = "Header")
    @XmlElement(name = "Header", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private Header header;

    @JSONField(name = "Body")
    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private Body body;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
