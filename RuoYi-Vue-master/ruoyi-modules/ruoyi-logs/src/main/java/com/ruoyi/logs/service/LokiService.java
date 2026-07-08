package com.ruoyi.logs.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.logs.config.LokiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Loki日志服务
 */
@Service
public class LokiService {
    
    @Autowired
    private LokiProperties lokiProperties;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 健康检查
     */
    public boolean healthCheck() {
        try {
            String url = lokiProperties.getUrl() + "/ready";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 执行LogQL查询
     */
    public String query(String logql, int minutes, int limit) {
        try {
            String url = lokiProperties.getUrl() + "/loki/api/v1/query_range";
            
            // 构建查询参数
            long endTime = System.currentTimeMillis() * 1000000; // 纳秒
            long startTime = endTime - minutes * 60 * 1000 * 1000000;
            
            String queryUrl = url + "?query=" + logql + "&start=" + startTime + "&end=" + endTime + "&limit=" + limit;
            
            ResponseEntity<String> response = restTemplate.getForEntity(queryUrl, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
    
    /**
     * 执行即时查询
     */
    public String queryInstant(String logql, int limit) {
        try {
            String url = lokiProperties.getUrl() + "/loki/api/v1/query";
            String queryUrl = url + "?query=" + logql + "&limit=" + limit + "&time=" + (System.currentTimeMillis() * 1000000);
            
            ResponseEntity<String> response = restTemplate.getForEntity(queryUrl, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
    
    /**
     * 获取标签
     */
    public String getLabels() {
        try {
            String url = lokiProperties.getUrl() + "/loki/api/v1/labels";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
    
    /**
     * 获取标签值
     */
    public String getLabelValues(String label) {
        try {
            String url = lokiProperties.getUrl() + "/loki/api/v1/label/" + label + "/values";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
}
