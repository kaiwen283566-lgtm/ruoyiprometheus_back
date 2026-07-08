package com.ruoyi.logs.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.logs.domain.LogQuery;
import com.ruoyi.logs.service.LokiService;
import com.ruoyi.logs.service.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志查询Controller
 */
@RestController
@RequestMapping("/logs/query")
public class LogQueryController {
    
    @Autowired
    private LogQueryService logQueryService;
    
    @Autowired
    private LokiService lokiService;
    
    /**
     * 查询日志查询记录列表
     */
    @GetMapping("/list")
    public AjaxResult list(LogQuery logQuery) {
        List<LogQuery> list = logQueryService.selectLogQueryList(logQuery);
        return AjaxResult.success(list);
    }
    
    /**
     * 新增日志查询记录
     */
    @PostMapping
    public AjaxResult add(@RequestBody LogQuery logQuery) {
        return toAjax(logQueryService.insertLogQuery(logQuery));
    }
    
    /**
     * 删除日志查询记录
     */
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(logQueryService.deleteLogQueryById(id));
    }
    
    /**
     * 执行LogQL查询
     */
    @PostMapping("/execute")
    public AjaxResult executeQuery(@RequestBody Map<String, Object> request) {
        String logql = (String) request.get("logql");
        Integer minutes = request.get("minutes") != null ? (Integer) request.get("minutes") : 30;
        Integer limit = request.get("limit") != null ? (Integer) request.get("limit") : 100;
        
        if (logql == null || logql.isEmpty()) {
            return AjaxResult.error("LogQL查询语句不能为空");
        }
        
        try {
            // 执行查询
            String result = lokiService.query(logql, minutes, limit);
            
            // 保存查询记录
            LogQuery logQuery = new LogQuery();
            logQuery.setQuery(logql);
            logQuery.setMinutes(minutes);
            logQuery.setLimit(limit);
            logQuery.setResult(result);
            logQuery.setStatus("success");
            logQueryService.insertLogQuery(logQuery);
            
            Map<String, Object> response = new HashMap<>();
            response.put("result", result);
            response.put("queryId", logQuery.getId());
            
            return AjaxResult.success(response);
        } catch (Exception e) {
            // 保存失败记录
            LogQuery logQuery = new LogQuery();
            logQuery.setQuery(logql);
            logQuery.setMinutes(minutes);
            logQuery.setLimit(limit);
            logQuery.setStatus("failed");
            logQuery.setErrorMsg(e.getMessage());
            logQueryService.insertLogQuery(logQuery);
            
            return AjaxResult.error("查询失败: " + e.getMessage());
        }
    }
    
    /**
     * 执行即时查询
     */
    @PostMapping("/instant")
    public AjaxResult executeInstantQuery(@RequestBody Map<String, Object> request) {
        String logql = (String) request.get("logql");
        Integer limit = request.get("limit") != null ? (Integer) request.get("limit") : 100;
        
        if (logql == null || logql.isEmpty()) {
            return AjaxResult.error("LogQL查询语句不能为空");
        }
        
        try {
            String result = lokiService.queryInstant(logql, limit);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("查询失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取Loki标签
     */
    @GetMapping("/labels")
    public AjaxResult getLabels() {
        try {
            String result = lokiService.getLabels();
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("获取标签失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取标签值
     */
    @GetMapping("/labels/{label}/values")
    public AjaxResult getLabelValues(@PathVariable String label) {
        try {
            String result = lokiService.getLabelValues(label);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("获取标签值失败: " + e.getMessage());
        }
    }
    
    /**
     * 健康检查
     */
    @GetMapping("/health")
    public AjaxResult healthCheck() {
        try {
            boolean healthy = lokiService.healthCheck();
            Map<String, Object> result = new HashMap<>();
            result.put("healthy", healthy);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("健康检查失败: " + e.getMessage());
        }
    }
    
    private AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
