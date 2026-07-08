package com.ruoyi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "aiops")
public class AiOpsConfig {
    private Prometheus prometheus;
    private Loki loki;
    private OpenAI openai;

    public static class Prometheus {
        private String url;
        private Alertmanager alertmanager;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public Alertmanager getAlertmanager() { return alertmanager; }
        public void setAlertmanager(Alertmanager alertmanager) { this.alertmanager = alertmanager; }
    }

    public static class Alertmanager {
        private String url;
        private boolean enabled;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

    public static class Loki {
        private String url;
        private boolean enabled;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

    public static class OpenAI {
        private String baseUrl;
        private String apiKey;
        private String model;
        private int timeout;
        private int maxRetries;
        private double temperature;
        private int maxTokens;
        private boolean stream;
        private Map<String, ModelConfig> models;

        public String getBaseUrl() { return baseUrl; }
        public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public int getTimeout() { return timeout; }
        public void setTimeout(int timeout) { this.timeout = timeout; }
        public int getMaxRetries() { return maxRetries; }
        public void setMaxRetries(int maxRetries) { this.maxRetries = maxRetries; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
        public boolean isStream() { return stream; }
        public void setStream(boolean stream) { this.stream = stream; }
        public Map<String, ModelConfig> getModels() { return models; }
        public void setModels(Map<String, ModelConfig> models) { this.models = models; }
    }

    public static class ModelConfig {
        private String name;
        private String provider;
        private String baseUrl;
        private String apiKey;
        private int maxTokens;
        private double temperature;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getProvider() { return provider; }
        public void setProvider(String provider) { this.provider = provider; }
        public String getBaseUrl() { return baseUrl; }
        public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
    }

    public Prometheus getPrometheus() { return prometheus; }
    public void setPrometheus(Prometheus prometheus) { this.prometheus = prometheus; }
    public Loki getLoki() { return loki; }
    public void setLoki(Loki loki) { this.loki = loki; }
    public OpenAI getOpenai() { return openai; }
    public void setOpenai(OpenAI openai) { this.openai = openai; }
}
