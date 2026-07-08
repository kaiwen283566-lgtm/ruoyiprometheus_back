package com.ruoyi.monitor.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.monitor.domain.ConnectionLog;
import com.ruoyi.monitor.domain.PrometheusConfig;
import com.ruoyi.monitor.service.ConnectionLogService;
import com.ruoyi.monitor.service.PrometheusConfigService;
import com.ruoyi.monitor.service.PrometheusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prometheus配置Controller
 */
@RestController
@RequestMapping("/monitor/prometheus")
public class PrometheusConfigController {
    
    @Autowired
    private PrometheusConfigService prometheusConfigService;
    
    @Autowired
    private PrometheusService prometheusService;
    
    @Autowired
    private ConnectionLogService connectionLogService;
    
    /**
     * 查询Prometheus配置列表
     */
    @GetMapping("/list")
    public AjaxResult list(PrometheusConfig prometheusConfig) {
        List<PrometheusConfig> list = prometheusConfigService.selectPrometheusConfigList(prometheusConfig);
        return AjaxResult.success(list);
    }
    
    /**
     * 获取Prometheus配置详细信息
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(prometheusConfigService.selectPrometheusConfigById(id));
    }
    
    /**
     * 新增Prometheus配置
     */
    @PostMapping
    public AjaxResult add(@RequestBody PrometheusConfig prometheusConfig) {
        return toAjax(prometheusConfigService.insertPrometheusConfig(prometheusConfig));
    }
    
    /**
     * 修改Prometheus配置
     */
    @PutMapping
    public AjaxResult edit(@RequestBody PrometheusConfig prometheusConfig) {
        return toAjax(prometheusConfigService.updatePrometheusConfig(prometheusConfig));
    }
    
    /**
     * 删除Prometheus配置
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(prometheusConfigService.deletePrometheusConfigByIds(ids));
    }
    
    /**
     * 健康检查
     */
    @GetMapping("/health")
    public AjaxResult healthCheck() {
        boolean healthy = prometheusService.healthCheck();
        Map<String, Object> result = new HashMap<>();
        result.put("healthy", healthy);
        result.put("version", prometheusService.getVersion());
        return AjaxResult.success(result);
    }
    
    /**
     * 执行PromQL查询
     */
    @PostMapping("/query")
    public AjaxResult query(@RequestBody Map<String, String> request) {
        String promql = request.get("promql");
        if (promql == null || promql.isEmpty()) {
            return AjaxResult.error("PromQL查询语句不能为空");
        }
        
        String result = prometheusService.query(promql);
        return AjaxResult.success(result);
    }
    
    /**
     * 执行范围查询
     */
    @PostMapping("/queryRange")
    public AjaxResult queryRange(@RequestBody Map<String, String> request) {
        String promql = request.get("promql");
        String start = request.get("start");
        String end = request.get("end");
        String step = request.get("step");
        
        if (promql == null || promql.isEmpty()) {
            return AjaxResult.error("PromQL查询语句不能为空");
        }
        
        // 使用默认值
        if (start == null || start.isEmpty()) {
            start = prometheusService.getTimestampMinutesAgo(30);
        }
        if (end == null || end.isEmpty()) {
            end = prometheusService.getCurrentTimestamp();
        }
        if (step == null || step.isEmpty()) {
            step = "15";
        }
        
        String result = prometheusService.queryRange(promql, start, end, step);
        return AjaxResult.success(result);
    }
    
    /**
     * 测试连接
     */
    @PostMapping("/test")
    public AjaxResult testConnection(@RequestBody PrometheusConfig config) {
        long startTime = System.currentTimeMillis();
        
        try {
            boolean healthy = prometheusService.healthCheck();
            String version = prometheusService.getVersion();
            long latency = System.currentTimeMillis() - startTime;
            
            // 记录连接日志
            ConnectionLog log = new ConnectionLog();
            log.setType("prometheus");
            log.setPrometheusStatus(healthy ? "success" : "failed");
            log.setLatency((int) latency);
            log.setVersion(version);
            log.setMessage(healthy ? "连接成功" : "连接失败");
            log.setOperator("system");
            connectionLogService.insertConnectionLog(log);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", healthy);
            result.put("version", version);
            result.put("latency", latency);
            result.put("message", healthy ? "连接成功" : "连接失败");
            
            return AjaxResult.success(result);
        } catch (Exception e) {
            long latency = System.currentTimeMillis() - startTime;
            
            // 记录失败日志
            ConnectionLog log = new ConnectionLog();
            log.setType("prometheus");
            log.setPrometheusStatus("failed");
            log.setLatency((int) latency);
            log.setMessage(e.getMessage());
            log.setOperator("system");
            connectionLogService.insertConnectionLog(log);
            
            return AjaxResult.error("连接测试失败: " + e.getMessage());
        }
    }
    
    private AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
