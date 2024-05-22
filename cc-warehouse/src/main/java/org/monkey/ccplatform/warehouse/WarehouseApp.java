package org.monkey.ccplatform.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WarehouseApp {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseApp.class, args);
    }
}
