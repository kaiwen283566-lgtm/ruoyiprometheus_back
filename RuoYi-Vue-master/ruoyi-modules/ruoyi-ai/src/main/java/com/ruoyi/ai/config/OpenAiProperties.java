package com.ruoyi.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * OpenAI配置属性 - 支持自定义模型接入
 */
@Data
@Component
@ConfigurationProperties(prefix = "aiops.openai")
public class OpenAiProperties {
    
    /**
     * API基础URL
     * 支持多种提供商：
     * - OpenAI: https://api.openai.com/v1
     * - DeepSeek: https://api.deepseek.com/v1
     * - 自定义: https://your-custom-api.com/v1
     */
    private String baseUrl = "https://api.openai.com/v1";
    
    /**
     * API密钥
     */
    private String apiKey;
    
    /**
     * 默认模型
     * 支持多种模型：
     * - OpenAI: gpt-4o, gpt-4o-mini, gpt-3.5-turbo
     * - DeepSeek: deepseek-chat, deepseek-coder
     * - 自定义: your-custom-model
     */
    private String model = "gpt-4o-mini";
    
    /**
     * 请求超时时间（秒）
     */
    private Integer timeout = 60;
    
    /**
     * 最大重试次数
     */
    private Integer maxRetries = 3;
    
    /**
     * 温度参数 (0.0-2.0)
     */
    private Double temperature = 0.7;
    
    /**
     * 最大Token数
     */
    private Integer maxTokens = 2000;
    
    /**
     * 自定义模型配置
     * 支持配置多个自定义模型
     */
    private Map<String, ModelConfig> models = new HashMap<>();
    
    /**
     * 是否启用流式响应
     */
    private Boolean stream = false;
    
    /**
     * 自定义请求头
     */
    private Map<String, String> customHeaders = new HashMap<>();
    
    /**
     * 模型配置
     */
    @Data
    public static class ModelConfig {
        /**
         * 模型名称
         */
        private String name;
        
        /**
         * 模型提供商
         */
        private String provider;
        
        /**
         * API基础URL（如果与默认不同）
         */
        private String baseUrl;
        
        /**
         * API密钥（如果与默认不同）
         */
        private String apiKey;
        
        /**
         * 最大Token数
         */
        private Integer maxTokens;
        
        /**
         * 温度参数
         */
        private Double temperature;
        
        /**
         * 其他自定义参数
         */
        private Map<String, Object> extraParams = new HashMap<>();
    }
    
    /**
     * 获取指定模型的配置
     */
    public ModelConfig getModelConfig(String modelName) {
        if (models.containsKey(modelName)) {
            return models.get(modelName);
        }
        // 返回默认配置
        ModelConfig defaultConfig = new ModelConfig();
        defaultConfig.setName(modelName);
        defaultConfig.setProvider("default");
        defaultConfig.setBaseUrl(this.baseUrl);
        defaultConfig.setApiKey(this.apiKey);
        defaultConfig.setMaxTokens(this.maxTokens);
        defaultConfig.setTemperature(this.temperature);
        return defaultConfig;
    }
}
