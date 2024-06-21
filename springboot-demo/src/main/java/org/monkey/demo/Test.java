package org.monkey.demo;

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
}
