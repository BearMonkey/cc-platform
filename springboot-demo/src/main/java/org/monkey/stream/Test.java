package org.monkey.stream;

import cn.hutool.http.HttpUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.monkey.util.HttpClientNewUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Test
 *
 * @author cc
 * @since 2024/6/19 10:42
 */
public class Test {

    public static void main(String[] args) {
        /*Test test = new Test();
        test.bvase64Decode(test.base64Encode(test.readFile("E:\\tmp\\test.pdf")));*/
        readFileAsString();
    }
    private void bvase64Decode(String str) {
        String fileName = "E:\\tmp\\test_out.pdf";
        File file = new File(fileName);
        byte[] decode = Base64.getDecoder().decode(str);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(decode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String base64Encode(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[1024];
            while ((inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes));
            }
            return new String(Base64.getEncoder().encode(sb.toString().getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream readFile(String fileName) {
        try (InputStream in = Files.newInputStream(Paths.get(fileName))) {
            byte[] bytes = new byte[in.available()];
            int read = in.read(bytes);
            return new ByteArrayInputStream(bytes, 0, read);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readFileAsString() {
        String url= "http://52.52.160.230/QD_express/apitopdf.asp?oids=FAD48344-A986-4876-89A6-5A22E802F714152";

        String s = HttpUtil.get(url);
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);

        String filePath = "E:\\tmp\\test.pdf";
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(s);

        /*InputStream inputStream = null;
        try {
            inputStream = HttpClientNewUtils.doGetStream(url);
            byte[] bytes = new byte[inputStream.available()];
            OutputStream outputStream = new FileOutputStream("E:\\tmp\\test.pdf");
            int read = inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
