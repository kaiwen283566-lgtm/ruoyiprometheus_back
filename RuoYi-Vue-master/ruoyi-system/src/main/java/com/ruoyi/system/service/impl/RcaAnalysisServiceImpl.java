package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RcaAnalysisMapper;
import com.ruoyi.system.domain.RcaAnalysis;
import com.ruoyi.system.service.IRcaAnalysisService;

@Service
public class RcaAnalysisServiceImpl implements IRcaAnalysisService {
    @Autowired
    private RcaAnalysisMapper rcaAnalysisMapper;

    @Override
    public RcaAnalysis selectRcaAnalysisById(Long id) {
        return rcaAnalysisMapper.selectRcaAnalysisById(id);
    }

    @Override
    public List<RcaAnalysis> selectRcaAnalysisList(RcaAnalysis rcaAnalysis) {
        return rcaAnalysisMapper.selectRcaAnalysisList(rcaAnalysis);
    }

    @Override
    public int insertRcaAnalysis(RcaAnalysis rcaAnalysis) {
        return rcaAnalysisMapper.insertRcaAnalysis(rcaAnalysis);
    }

    @Override
    public int updateRcaAnalysis(RcaAnalysis rcaAnalysis) {
        return rcaAnalysisMapper.updateRcaAnalysis(rcaAnalysis);
    }

    @Override
    public int deleteRcaAnalysisByIds(Long[] ids) {
        return rcaAnalysisMapper.deleteRcaAnalysisByIds(ids);
    }
}
