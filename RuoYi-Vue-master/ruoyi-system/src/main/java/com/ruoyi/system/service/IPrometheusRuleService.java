package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PrometheusRule;

public interface IPrometheusRuleService {
    public PrometheusRule selectPrometheusRuleById(Long id);
    public List<PrometheusRule> selectPrometheusRuleList(PrometheusRule rule);
    public int insertPrometheusRule(PrometheusRule rule);
    public int updatePrometheusRule(PrometheusRule rule);
    public int deletePrometheusRuleByIds(Long[] ids);
}
