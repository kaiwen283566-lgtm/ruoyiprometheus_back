package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.RcaAnalysis;

public interface RcaAnalysisMapper {
    public RcaAnalysis selectRcaAnalysisById(Long id);
    public List<RcaAnalysis> selectRcaAnalysisList(RcaAnalysis rcaAnalysis);
    public int insertRcaAnalysis(RcaAnalysis rcaAnalysis);
    public int updateRcaAnalysis(RcaAnalysis rcaAnalysis);
    public int deleteRcaAnalysisById(Long id);
    public int deleteRcaAnalysisByIds(Long[] ids);
}
