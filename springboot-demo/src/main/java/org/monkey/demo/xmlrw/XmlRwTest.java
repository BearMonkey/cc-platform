package org.monkey.demo.xmlrw;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * XmlRwTest
 *
 * @author cc
 * @since 2024/6/12 11:54
 */
@Slf4j
public class XmlRwTest {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("F:\\code\\cc-platform\\springboot-demo\\src\\main\\resources\\org\\monkey\\demo\\xmlrw\\TestXml.xml"))) {
            br.lines().forEach(line -> builder.append(line).append("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String xmlStr = builder.toString();
        ClassRoom classRoom = xmlToObj(xmlStr, ClassRoom.class);
        log.info("classRoom: {}", classRoom);

    }
    public static <T> T xmlToObj(String xmlContent, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (T) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (Exception e) {
            log.error("xml转换object异常：", e);
            return null;
        }
    }
}
