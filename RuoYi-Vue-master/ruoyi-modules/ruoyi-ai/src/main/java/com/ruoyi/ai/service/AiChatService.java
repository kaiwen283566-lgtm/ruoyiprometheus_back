package com.ruoyi.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.ai.config.OpenAiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AI对话服务 - 支持自定义模型接入
 */
@Service
public class AiChatService {
    
    @Autowired
    private OpenAiProperties openAiProperties;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 发送消息到AI模型
     * @param message 用户消息
     * @param model 模型名称（可选，默认使用配置的默认模型）
     * @return AI回复
     */
    public String sendMessage(String message, String model) {
        try {
            // 如果没有指定模型，使用默认模型
            String modelName = (model != null && !model.isEmpty()) ? model : openAiProperties.getModel();
            
            // 获取模型配置
            OpenAiProperties.ModelConfig modelConfig = openAiProperties.getModelConfig(modelName);
            
            // 构建请求体
            Map<String, Object> requestBody = buildRequestBody(message, modelConfig);
            
            // 构建请求头
            HttpHeaders headers = buildHeaders(modelConfig);
            
            // 构建请求
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            // 发送请求
            String url = modelConfig.getBaseUrl() + "/chat/completions";
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            
            // 解析响应
            return parseResponse(response.getBody());
            
        } catch (Exception e) {
            return "AI服务异常: " + e.getMessage();
        }
    }
    
    /**
     * 发送消息（使用默认模型）
     */
    public String sendMessage(String message) {
        return sendMessage(message, null);
    }
    
    /**
     * 构建请求体
     */
    private Map<String, Object> buildRequestBody(String message, OpenAiProperties.ModelConfig modelConfig) {
        Map<String, Object> requestBody = new HashMap<>();
        
        // 添加消息
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", message);
        messages.add(userMessage);
        requestBody.put("messages", messages);
        
        // 添加模型
        requestBody.put("model", modelConfig.getName());
        
        // 添加温度参数
        if (modelConfig.getTemperature() != null) {
            requestBody.put("temperature", modelConfig.getTemperature());
        }
        
        // 添加最大Token数
        if (modelConfig.getMaxTokens() != null) {
            requestBody.put("max_tokens", modelConfig.getMaxTokens());
        }
        
        // 添加流式参数
        requestBody.put("stream", openAiProperties.getStream());
        
        // 添加自定义参数
        if (modelConfig.getExtraParams() != null && !modelConfig.getExtraParams().isEmpty()) {
            requestBody.putAll(modelConfig.getExtraParams());
        }
        
        return requestBody;
    }
    
    /**
     * 构建请求头
     */
    private HttpHeaders buildHeaders(OpenAiProperties.ModelConfig modelConfig) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // 添加Authorization头
        String apiKey = modelConfig.getApiKey() != null ? modelConfig.getApiKey() : openAiProperties.getApiKey();
        if (apiKey != null && !apiKey.isEmpty()) {
            headers.setBearerAuth(apiKey);
        }
        
        // 添加自定义请求头
        if (openAiProperties.getCustomHeaders() != null) {
            openAiProperties.getCustomHeaders().forEach(headers::set);
        }
        
        return headers;
    }
    
    /**
     * 解析响应
     */
    private String parseResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode choices = root.path("choices");
            if (choices.isArray() && choices.size() > 0) {
                JsonNode firstChoice = choices.get(0);
                JsonNode message = firstChoice.path("message");
                return message.path("content").asText();
            }
            return "无法解析AI响应";
        } catch (Exception e) {
            return "响应解析失败: " + e.getMessage();
        }
    }
    
    /**
     * 获取支持的模型列表
     */
    public List<String> getSupportedModels() {
        List<String> models = new ArrayList<>();
        models.add(openAiProperties.getModel()); // 默认模型
        
        if (openAiProperties.getModels() != null) {
            models.addAll(openAiProperties.getModels().keySet());
        }
        
        return models;
    }
    
    /**
     * 测试模型连接
     */
    public boolean testModelConnection(String modelName) {
        try {
            OpenAiProperties.ModelConfig modelConfig = openAiProperties.getModelConfig(modelName);
            String testMessage = "Hello";
            String response = sendMessage(testMessage, modelName);
            return !response.contains("异常") && !response.contains("失败");
        } catch (Exception e) {
            return false;
        }
    }
}
