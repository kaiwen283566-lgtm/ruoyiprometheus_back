package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.PrometheusRule;

public interface PrometheusRuleMapper {
    public PrometheusRule selectPrometheusRuleById(Long id);
    public List<PrometheusRule> selectPrometheusRuleList(PrometheusRule rule);
    public int insertPrometheusRule(PrometheusRule rule);
    public int updatePrometheusRule(PrometheusRule rule);
    public int deletePrometheusRuleById(Long id);
    public int deletePrometheusRuleByIds(Long[] ids);
}
