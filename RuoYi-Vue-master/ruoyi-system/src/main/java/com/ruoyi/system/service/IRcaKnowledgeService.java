package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RcaKnowledge;

public interface IRcaKnowledgeService {
    public RcaKnowledge selectRcaKnowledgeById(Long id);
    public List<RcaKnowledge> selectRcaKnowledgeList(RcaKnowledge rcaKnowledge);
    public int insertRcaKnowledge(RcaKnowledge rcaKnowledge);
    public int updateRcaKnowledge(RcaKnowledge rcaKnowledge);
    public int deleteRcaKnowledgeByIds(Long[] ids);
}
