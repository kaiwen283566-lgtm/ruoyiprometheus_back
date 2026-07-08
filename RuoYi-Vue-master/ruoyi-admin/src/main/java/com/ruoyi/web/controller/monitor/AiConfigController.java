package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.AiConfig;
import com.ruoyi.system.service.IAiConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor/ai")
public class AiConfigController extends BaseController {
    @Autowired
    private IAiConfigService aiConfigService;

    @PreAuthorize("@ss.hasPermi('monitor:ai:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiConfig config) {
        startPage();
        List<AiConfig> list = aiConfigService.selectAiConfigList(config);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:ai:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(aiConfigService.selectAiConfigById(id));
    }

    @PreAuthorize("@ss.hasPermi('monitor:ai:query')")
    @GetMapping("/key/{configKey}")
    public AjaxResult getByKey(@PathVariable String configKey) {
        return success(aiConfigService.selectAiConfigByKey(configKey));
    }

    @PreAuthorize("@ss.hasPermi('monitor:ai:add')")
    @PostMapping
    public AjaxResult add(@RequestBody AiConfig config) {
        return toAjax(aiConfigService.insertAiConfig(config));
    }

    @PreAuthorize("@ss.hasPermi('monitor:ai:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody AiConfig config) {
        return toAjax(aiConfigService.updateAiConfig(config));
    }

    @PreAuthorize("@ss.hasPermi('monitor:ai:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(aiConfigService.deleteAiConfigByIds(ids));
    }
}
