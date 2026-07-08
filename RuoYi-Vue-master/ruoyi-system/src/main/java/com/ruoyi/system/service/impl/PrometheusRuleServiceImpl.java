package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PrometheusRuleMapper;
import com.ruoyi.system.domain.PrometheusRule;
import com.ruoyi.system.service.IPrometheusRuleService;

@Service
public class PrometheusRuleServiceImpl implements IPrometheusRuleService {
    @Autowired
    private PrometheusRuleMapper prometheusRuleMapper;

    @Override
    public PrometheusRule selectPrometheusRuleById(Long id) {
        return prometheusRuleMapper.selectPrometheusRuleById(id);
    }

    @Override
    public List<PrometheusRule> selectPrometheusRuleList(PrometheusRule rule) {
        return prometheusRuleMapper.selectPrometheusRuleList(rule);
    }

    @Override
    public int insertPrometheusRule(PrometheusRule rule) {
        return prometheusRuleMapper.insertPrometheusRule(rule);
    }

    @Override
    public int updatePrometheusRule(PrometheusRule rule) {
        return prometheusRuleMapper.updatePrometheusRule(rule);
    }

    @Override
    public int deletePrometheusRuleByIds(Long[] ids) {
        return prometheusRuleMapper.deletePrometheusRuleByIds(ids);
    }
}
