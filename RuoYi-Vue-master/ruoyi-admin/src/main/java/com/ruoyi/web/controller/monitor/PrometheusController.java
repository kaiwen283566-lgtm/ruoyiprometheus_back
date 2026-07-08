package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.config.AiOpsConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.PrometheusRule;
import com.ruoyi.system.service.IPrometheusRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitor/prometheus")
public class PrometheusController extends BaseController {
    @Autowired
    private AiOpsConfig aiOpsConfig;
    @Autowired
    private IPrometheusRuleService prometheusRuleService;

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:query')")
    @GetMapping("/query")
    public AjaxResult query(@RequestParam String query, @RequestParam(required = false) Long time) {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/query";
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        if (time != null) {
            params.put("time", String.valueOf(time));
        }
        String result = HttpUtils.sendGet(url, buildQueryString(params));
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:query')")
    @GetMapping("/query_range")
    public AjaxResult queryRange(@RequestParam String query, @RequestParam Long start, @RequestParam Long end, @RequestParam(required = false, defaultValue = "15") String step) {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/query_range";
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        params.put("start", String.valueOf(start));
        params.put("end", String.valueOf(end));
        params.put("step", step);
        String result = HttpUtils.sendGet(url, buildQueryString(params));
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/targets")
    public AjaxResult targets() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/targets";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/rules")
    public AjaxResult rules() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/rules";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/labels")
    public AjaxResult labels() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/labels";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/label/{name}/values")
    public AjaxResult labelValues(@PathVariable String name) {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/label/" + name + "/values";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/alerts")
    public AjaxResult alerts() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/alerts";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/series")
    public AjaxResult series(@RequestParam String match) {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/series?match[]=" + match;
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/status/buildinfo")
    public AjaxResult buildInfo() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/api/v1/status/buildinfo";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:edit')")
    @PostMapping("/reload")
    public AjaxResult reload() {
        String url = aiOpsConfig.getPrometheus().getUrl() + "/-/reload";
        String result = HttpUtils.sendPost(url, "", "application/json");
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:list')")
    @GetMapping("/rule/list")
    public TableDataInfo ruleList(PrometheusRule rule) {
        startPage();
        List<PrometheusRule> list = prometheusRuleService.selectPrometheusRuleList(rule);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:query')")
    @GetMapping("/rule/{id}")
    public AjaxResult getRule(@PathVariable Long id) {
        return success(prometheusRuleService.selectPrometheusRuleById(id));
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:add')")
    @PostMapping("/rule")
    public AjaxResult addRule(@RequestBody PrometheusRule rule) {
        return toAjax(prometheusRuleService.insertPrometheusRule(rule));
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:edit')")
    @PutMapping("/rule")
    public AjaxResult updateRule(@RequestBody PrometheusRule rule) {
        return toAjax(prometheusRuleService.updatePrometheusRule(rule));
    }

    @PreAuthorize("@ss.hasPermi('monitor:prometheus:remove')")
    @DeleteMapping("/rule/{ids}")
    public AjaxResult deleteRule(@PathVariable Long[] ids) {
        return toAjax(prometheusRuleService.deletePrometheusRuleByIds(ids));
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
