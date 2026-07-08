package com.ruoyi.monitor.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Prometheus配置表 prometheus_config
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrometheusConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 配置名称 */
    private String name;

    /** Prometheus URL */
    private String prometheusUrl;

    /** Alertmanager URL */
    private String alertmanagerUrl;

    /** 是否启用Alertmanager */
    private Boolean alertmanagerEnabled;

    /** 采集间隔 */
    private String scrapeInterval;

    /** 是否启用 */
    private Boolean enabled;

    /** 最后同步时间 */
    private String lastSync;
}
