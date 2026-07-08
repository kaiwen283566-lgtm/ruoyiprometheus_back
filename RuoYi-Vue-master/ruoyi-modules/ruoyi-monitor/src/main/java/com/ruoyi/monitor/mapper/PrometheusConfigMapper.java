package com.ruoyi.monitor.mapper;

import com.ruoyi.monitor.domain.PrometheusConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Prometheus配置Mapper
 */
@Mapper
public interface PrometheusConfigMapper {
    
    /**
     * 查询Prometheus配置列表
     */
    List<PrometheusConfig> selectPrometheusConfigList(PrometheusConfig prometheusConfig);
    
    /**
     * 查询Prometheus配置
     */
    PrometheusConfig selectPrometheusConfigById(Long id);
    
    /**
     * 新增Prometheus配置
     */
    int insertPrometheusConfig(PrometheusConfig prometheusConfig);
    
    /**
     * 修改Prometheus配置
     */
    int updatePrometheusConfig(PrometheusConfig prometheusConfig);
    
    /**
     * 删除Prometheus配置
     */
    int deletePrometheusConfigById(Long id);
    
    /**
     * 批量删除Prometheus配置
     */
    int deletePrometheusConfigByIds(Long[] ids);
}
