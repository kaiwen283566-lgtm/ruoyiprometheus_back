package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.config.AiOpsConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/monitor/alert")
public class AlertmanagerController {
    @Autowired
    private AiOpsConfig aiOpsConfig;

    @PreAuthorize("@ss.hasPermi('monitor:alert:list')")
    @GetMapping("/alerts")
    public AjaxResult alerts() {
        String url = aiOpsConfig.getPrometheus().getAlertmanager().getUrl() + "/api/v2/alerts";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:alert:list')")
    @GetMapping("/silences")
    public AjaxResult silences() {
        String url = aiOpsConfig.getPrometheus().getAlertmanager().getUrl() + "/api/v2/silences";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:alert:add')")
    @PostMapping("/silences")
    public AjaxResult addSilence(@RequestBody Map<String, Object> payload) {
        String url = aiOpsConfig.getPrometheus().getAlertmanager().getUrl() + "/api/v2/silences";
        String result = HttpUtils.sendPost(url, payload);
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:alert:remove')")
    @DeleteMapping("/silences/{id}")
    public AjaxResult deleteSilence(@PathVariable String id) {
        String url = aiOpsConfig.getPrometheus().getAlertmanager().getUrl() + "/api/v2/silences/" + id;
        String result = HttpUtils.sendPost(url, "", "application/json");
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('monitor:alert:list')")
    @GetMapping("/status")
    public AjaxResult status() {
        String url = aiOpsConfig.getPrometheus().getAlertmanager().getUrl() + "/api/v2/status";
        String result = HttpUtils.sendGet(url);
        return AjaxResult.success(result);
    }
}
