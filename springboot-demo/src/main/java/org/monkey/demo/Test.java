package org.monkey.demo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Test
 *
 * @author cc
 * @since 2024/6/19 17:48
 */
public class Test {
    /*public static void main(String[] args) {
        SoapClient soapClient = SoapClient.create("https://tsx.lapker.hu/PudoTest/PudoDocumentService?singlewsdl");
        // 设置SOAP请求的header，包含多个命名空间
        soapClient.
        SOAPHeader header = new SOAPHeader()
                .set("{http://namespace1.com}", "Username", "user1")
                .set("{http://namespace1.com}", "Password", "pass1")
                .set("{http://namespace2.com}", "SessionId", "sessionId123");

        // 设置header
        soapClient.setHeader(header);
    }*/
    // wsdl2java.bat -p org.monkey.demo.wsdl2 -d F:\code\cc-platform\springboot-demo\src\main\java -client https://tsx.lapker.hu/PudoTest/PartnerPudoService?singleWsdl

    // wsimport -keep -encoding UTF-8 -d F:\code\cc-platform\springboot-demo\target\classes -s F:\code\cc-platform\springboot-demo\src\main\java -p org.monkey.demo.wsdl -verbose https://tsx.lapker.hu/PudoTest/PartnerPudoService?singleWsdl

    public static void main(String[] args) {

        List<BoxDto> list = new ArrayList<>();
        BoxDto boxDto1 = new BoxDto("name1", "2024062811");
        BoxDto boxDto2 = new BoxDto("name2", "2024062812");
        BoxDto boxDto3 = new BoxDto("name2", "2024062810");
        list.add(boxDto1);
        list.add(boxDto2);
        list.add(boxDto3);
        System.out.println(JSONObject.toJSONString(list));
        list.sort(new Comparator<BoxDto>() {
            @Override
            public int compare(BoxDto o1, BoxDto o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        System.out.println(JSONObject.toJSONString(list));
    }
}

@Data
class BoxDto {
    private String name;
    private String time;

    public BoxDto(String name, String time) {
        this.name = name;
        this.time = time;
    }
}