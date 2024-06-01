package org.monkey.platform.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * SysConfig
 *
 * @author cc
 * @since 2024/6/1 17:17
 */
@Configuration
@Data
public class SysConfig {
    @Value("${hmacsha256key:}")
    private String hmacsha256key;
}
