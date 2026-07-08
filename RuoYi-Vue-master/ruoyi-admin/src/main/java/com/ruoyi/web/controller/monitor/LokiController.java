package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.config.AiOpsConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitor/loki")
public class LokiController {
    @Autowired
    private AiOpsConfig aiOpsConfig;

    @PreAuthorize("@ss.hasPermi('monitor:loki:query')")
    @GetMapping("/query")
    public AjaxResult query(@RequestParam String query, @RequestParam(required = false) Long time, @RequestParam(required = false) String limit) {
        String url = aiOpsConfig.getLoki().getUrl() + "/loki/api/v1/query";
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        if (time != null) {
            params.put("time", String.valueOf(time));
        }
        if (limit != null) {
            params.put("limit", limit);
        }
        String result = HttpUtils.sendGet(url, buildQueryString(params));
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:loki:query')")
    @GetMapping("/query_range")
    public AjaxResult queryRange(@RequestParam String query, @RequestParam Long start, @RequestParam Long end, @RequestParam(required = false, defaultValue = "15") String step, @RequestParam(required = false) String limit) {
        String url = aiOpsConfig.getLoki().getUrl() + "/loki/api/v1/query_range";
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        params.put("start", String.valueOf(start));
        params.put("end", String.valueOf(end));
        params.put("step", step);
        if (limit != null) {
            params.put("limit", limit);
        }
        String result = HttpUtils.sendGet(url, buildQueryString(params));
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:loki:list')")
    @GetMapping("/labels")
    public AjaxResult labels() {
        String url = aiOpsConfig.getLoki().getUrl() + "/loki/api/v1/labels";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:loki:list')")
    @GetMapping("/label/{name}/values")
    public AjaxResult labelValues(@PathVariable String name) {
        String url = aiOpsConfig.getLoki().getUrl() + "/loki/api/v1/label/" + name + "/values";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    private String buildQueryString(Map<String, String> params) {
        return params.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
