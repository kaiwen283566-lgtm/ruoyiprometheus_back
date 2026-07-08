package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.RcaKnowledge;

public interface RcaKnowledgeMapper {
    public RcaKnowledge selectRcaKnowledgeById(Long id);
    public List<RcaKnowledge> selectRcaKnowledgeList(RcaKnowledge rcaKnowledge);
    public int insertRcaKnowledge(RcaKnowledge rcaKnowledge);
    public int updateRcaKnowledge(RcaKnowledge rcaKnowledge);
    public int deleteRcaKnowledgeById(Long id);
    public int deleteRcaKnowledgeByIds(Long[] ids);
}
