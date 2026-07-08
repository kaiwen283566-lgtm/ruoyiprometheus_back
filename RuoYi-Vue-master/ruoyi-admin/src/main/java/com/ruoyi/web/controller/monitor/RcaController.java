package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.RcaAnalysis;
import com.ruoyi.system.domain.RcaKnowledge;
import com.ruoyi.system.service.IRcaAnalysisService;
import com.ruoyi.system.service.IRcaKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor/rca")
public class RcaController extends BaseController {
    @Autowired
    private IRcaAnalysisService rcaAnalysisService;
    @Autowired
    private IRcaKnowledgeService rcaKnowledgeService;

    @PreAuthorize("@ss.hasPermi('monitor:rca:analyze')")
    @PostMapping("/analyze")
    public AjaxResult analyze(@RequestBody RcaAnalysis analysis) {
        return toAjax(rcaAnalysisService.insertRcaAnalysis(analysis));
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:query')")
    @GetMapping("/analysis/{id}")
    public AjaxResult getAnalysis(@PathVariable Long id) {
        return success(rcaAnalysisService.selectRcaAnalysisById(id));
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:list')")
    @GetMapping("/history")
    public TableDataInfo history(RcaAnalysis analysis) {
        startPage();
        List<RcaAnalysis> list = rcaAnalysisService.selectRcaAnalysisList(analysis);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:feedback')")
    @PostMapping("/feedback")
    public AjaxResult feedback(@RequestBody RcaAnalysis analysis) {
        return toAjax(rcaAnalysisService.updateRcaAnalysis(analysis));
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:list')")
    @GetMapping("/knowledge")
    public TableDataInfo knowledge(RcaKnowledge knowledge) {
        startPage();
        List<RcaKnowledge> list = rcaKnowledgeService.selectRcaKnowledgeList(knowledge);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:query')")
    @GetMapping("/knowledge/{id}")
    public AjaxResult getKnowledge(@PathVariable Long id) {
        return success(rcaKnowledgeService.selectRcaKnowledgeById(id));
    }

    @PreAuthorize("@ss.hasPermi('monitor:rca:add')")
    @PostMapping("/knowledge")
    public AjaxResult addKnowledge(@RequestBody RcaKnowledge knowledge) {
        return toAjax(rcaKnowledgeService.insertRcaKnowledge(knowledge));
    }
}
