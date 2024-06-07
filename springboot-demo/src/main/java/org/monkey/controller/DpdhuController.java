package org.monkey.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.monkey.dto.TestDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * StreamController
 *
 * @author cc
 * @since 2024/6/4 15:11
 */
@RestController
@RequestMapping("/dpdhu/parcel")
@Slf4j
public class DpdhuController {

    @PostMapping("/create")
    public String createParcel() {
        return "{\"Response\":{\"status\":\"ok\", \"errlog\":\"\",  \"pl_number\":[\"16407850875928\"] }}";
    }

    @PostMapping("/stream/print/{name}")
    public Object download(/*@RequestBody TestDto testDto*/@PathVariable("name") String name) {
        /*log.info("testDto: {}", testDto.toString());*/
        // 创建一个输入流，这里使用字节数组作为示例
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(getContent("E:\\tmp\\" + name + ".pdf"));
            // 设置响应头
            InputStreamResource resource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"test.pdf\"")
                    .body(resource);
        } catch (IOException e) {
            return "{\"status\":\"-1\"}";
        }
    }

    @PostMapping("/track/query")
    public String queryParcelTrack() {
        return "{\"parcel_status\":\"Shipped.\"}";
    }

    @GetMapping("/test/{name}")
    public String testStream(@PathVariable("name") String name) throws IOException {
        log.info("/stream/test/stream");
        String url = "http://localhost:8080/dpdhu/stream/download/" + name;
        TestDto testDto = new TestDto();
        testDto.setName("monkey");
        testDto.setNo("123");
        HttpResponse execute = HttpRequest.post(url)
                .contentType("application/octet-stream")
                //.body(testDto.toString())
                .timeout(50000).execute();

        Map<String, List<String>> headers = execute.headers();
        List<String> contentTypes = headers.get("Content-Type");
        String contentType = contentTypes.get(0);
        if (contentType.contains("application/octet-stream")) {
            InputStream inputStream = execute.bodyStream();
            OutputStream outputStream = Files.newOutputStream(Paths.get("E:\\tmp\\test_out.pdf"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return "E:\\tmp\\test_out.pdf";
        } else {
            String body = execute.body();
            log.info("=====: {}", body);
            return body;
        }
        /*String contentType = execute.headers().get("Content-type").get(0);
        if (contentType.contains("application/json")) {
            String body = execute.body();
            log.info("=====: {}", body);
            return body;
        } else {
            InputStream inputStream = execute.bodyStream();
            OutputStream outputStream = Files.newOutputStream(Paths.get("E:\\tmp\\test_out.pdf"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return "E:\\tmp\\test_out.pdf";
        }*/
    }
    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }
}
