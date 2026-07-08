package com.ruoyi.monitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Prometheus配置属性
 */
@Component
@ConfigurationProperties(prefix = "aiops.prometheus")
public class PrometheusProperties {
    
    private String url;
    
    private Alertmanager alertmanager = new Alertmanager();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Alertmanager getAlertmanager() {
        return alertmanager;
    }

    public void setAlertmanager(Alertmanager alertmanager) {
        this.alertmanager = alertmanager;
    }
    
    public static class Alertmanager {
        private String url;
        private Boolean enabled = false;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
}
