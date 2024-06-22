package org.monkey.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.*;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * XmlUtil 处理http请求中xml格式的请求响应报文
 *
 * @author cc
 * @since 2024/6/13 11:44
 */
@Slf4j
public class XmlUtil {
    /**
     * xml文本转换为javaBean
     * @param xmlContent xml文本
     * @param clazz Java Bean的class对象
     * @return java bean
     * @param <T> javabean泛型定义
     */
    public static <T> T xmlToObj(String xmlContent, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (T) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("xml转换{}异常：", clazz.getName());
            return null;
        }
    }

    public static <T> void objToXml(Object obj, Class<T> clazz) {
        try {
            File file = new File("E:\\tmp\\tracks.xml");
            JAXB.marshal(obj, file);
        } catch (Exception e) {
            log.error("xml转换{}异常：", clazz.getName(), e);
        }
    }

    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            throw new RuntimeException(obj.getClass().getName() + "转换xml异常", e);
        }
        return sw.toString();
    }
}
