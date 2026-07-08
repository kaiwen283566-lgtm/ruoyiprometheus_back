package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RcaAnalysis;

public interface IRcaAnalysisService {
    public RcaAnalysis selectRcaAnalysisById(Long id);
    public List<RcaAnalysis> selectRcaAnalysisList(RcaAnalysis rcaAnalysis);
    public int insertRcaAnalysis(RcaAnalysis rcaAnalysis);
    public int updateRcaAnalysis(RcaAnalysis rcaAnalysis);
    public int deleteRcaAnalysisByIds(Long[] ids);
}
