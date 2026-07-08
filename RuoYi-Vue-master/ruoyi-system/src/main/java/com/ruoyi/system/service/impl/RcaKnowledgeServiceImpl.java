package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RcaKnowledgeMapper;
import com.ruoyi.system.domain.RcaKnowledge;
import com.ruoyi.system.service.IRcaKnowledgeService;

@Service
public class RcaKnowledgeServiceImpl implements IRcaKnowledgeService {
    @Autowired
    private RcaKnowledgeMapper rcaKnowledgeMapper;

    @Override
    public RcaKnowledge selectRcaKnowledgeById(Long id) {
        return rcaKnowledgeMapper.selectRcaKnowledgeById(id);
    }

    @Override
    public List<RcaKnowledge> selectRcaKnowledgeList(RcaKnowledge rcaKnowledge) {
        return rcaKnowledgeMapper.selectRcaKnowledgeList(rcaKnowledge);
    }

    @Override
    public int insertRcaKnowledge(RcaKnowledge rcaKnowledge) {
        return rcaKnowledgeMapper.insertRcaKnowledge(rcaKnowledge);
    }

    @Override
    public int updateRcaKnowledge(RcaKnowledge rcaKnowledge) {
        return rcaKnowledgeMapper.updateRcaKnowledge(rcaKnowledge);
    }

    @Override
    public int deleteRcaKnowledgeByIds(Long[] ids) {
        return rcaKnowledgeMapper.deleteRcaKnowledgeByIds(ids);
    }
}
