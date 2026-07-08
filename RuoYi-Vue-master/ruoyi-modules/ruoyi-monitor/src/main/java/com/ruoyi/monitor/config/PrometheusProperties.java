package com.ruoyi.monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Prometheus配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "aiops.prometheus")
public class PrometheusProperties {
    
    private String url;
    
    private Alertmanager alertmanager = new Alertmanager();
    
    @Data
    public static class Alertmanager {
        private String url;
        private Boolean enabled = false;
    }
}
