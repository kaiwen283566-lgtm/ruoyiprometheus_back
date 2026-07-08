package com.ruoyi.monitor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.monitor.config.PrometheusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Prometheus服务
 */
@Service
public class PrometheusService {
    
    @Autowired
    private PrometheusProperties prometheusProperties;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 健康检查
     */
    public boolean healthCheck() {
        try {
            String url = prometheusProperties.getUrl() + "/-/healthy";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 查询Prometheus版本
     */
    public String getVersion() {
        try {
            String url = prometheusProperties.getUrl() + "/api/v1/status/buildinfo";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.path("data").path("version").asText();
        } catch (Exception e) {
            return "unknown";
        }
    }
    
    /**
     * 执行PromQL查询
     */
    public String query(String promql) {
        try {
            String url = prometheusProperties.getUrl() + "/api/v1/query";
            String queryUrl = url + "?query=" + promql;
            ResponseEntity<String> response = restTemplate.getForEntity(queryUrl, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
    
    /**
     * 执行范围查询
     */
    public String queryRange(String promql, String start, String end, String step) {
        try {
            String url = prometheusProperties.getUrl() + "/api/v1/query_range";
            String queryUrl = url + "?query=" + promql + "&start=" + start + "&end=" + end + "&step=" + step;
            ResponseEntity<String> response = restTemplate.getForEntity(queryUrl, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
    
    /**
     * 获取当前时间戳
     */
    public String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 获取N分钟前的时间戳
     */
    public String getTimestampMinutesAgo(int minutes) {
        return String.valueOf((System.currentTimeMillis() - minutes * 60 * 1000) / 1000);
    }
}
