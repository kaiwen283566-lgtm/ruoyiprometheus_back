package com.ruoyi.monitor.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 连接日志表 connection_log
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConnectionLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 时间 */
    private String time;

    /** 类型 */
    private String type;

    /** Prometheus状态 */
    private String prometheusStatus;

    /** Alertmanager状态 */
    private String alertmanagerStatus;

    /** 延迟(ms) */
    private Integer latency;

    /** 版本 */
    private String version;

    /** 结果消息 */
    private String message;

    /** 操作人 */
    private String operator;
}
