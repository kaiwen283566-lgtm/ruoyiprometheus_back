package com.ruoyi.logs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Loki配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "aiops.loki")
public class LokiProperties {
    
    private String url;
    
    private Boolean enabled = true;
}
