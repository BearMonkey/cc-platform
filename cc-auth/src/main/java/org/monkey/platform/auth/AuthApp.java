package org.monkey.platform.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * AuthApp
 *
 * @author cc
 * @since 2024/5/30 16:48
 */
@SpringBootApplication
@EnableFeignClients("org.monkey.platform.auth.feign")
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
