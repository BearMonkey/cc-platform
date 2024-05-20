package org.monkey.demo.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // nacos 服务注册发现
public class NacosDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosDemoApp.class, args);
    }
}
