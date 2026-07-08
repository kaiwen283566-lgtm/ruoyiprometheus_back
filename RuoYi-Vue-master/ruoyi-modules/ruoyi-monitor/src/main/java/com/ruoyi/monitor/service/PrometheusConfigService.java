package com.ruoyi.monitor.service;

import com.ruoyi.monitor.domain.PrometheusConfig;
import com.ruoyi.monitor.mapper.PrometheusConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Prometheus配置Service
 */
@Service
public class PrometheusConfigService {
    
    @Autowired
    private PrometheusConfigMapper prometheusConfigMapper;
    
    /**
     * 查询Prometheus配置
     */
    public PrometheusConfig selectPrometheusConfigById(Long id) {
        return prometheusConfigMapper.selectPrometheusConfigById(id);
    }
    
    /**
     * 查询Prometheus配置列表
     */
    public List<PrometheusConfig> selectPrometheusConfigList(PrometheusConfig prometheusConfig) {
        return prometheusConfigMapper.selectPrometheusConfigList(prometheusConfig);
    }
    
    /**
     * 新增Prometheus配置
     */
    public int insertPrometheusConfig(PrometheusConfig prometheusConfig) {
        return prometheusConfigMapper.insertPrometheusConfig(prometheusConfig);
    }
    
    /**
     * 修改Prometheus配置
     */
    public int updatePrometheusConfig(PrometheusConfig prometheusConfig) {
        return prometheusConfigMapper.updatePrometheusConfig(prometheusConfig);
    }
    
    /**
     * 批量删除Prometheus配置
     */
    public int deletePrometheusConfigByIds(Long[] ids) {
        return prometheusConfigMapper.deletePrometheusConfigByIds(ids);
    }
    
    /**
     * 删除Prometheus配置
     */
    public int deletePrometheusConfigById(Long id) {
        return prometheusConfigMapper.deletePrometheusConfigById(id);
    }
}
