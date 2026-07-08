-- Prometheus 规则管理表
CREATE TABLE IF NOT EXISTS monitor_prometheus_rules (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    type        VARCHAR(20) NOT NULL COMMENT 'alert:告警规则,record:记录规则',
    expr        TEXT NOT NULL COMMENT 'PromQL表达式',
    for         VARCHAR(20) COMMENT '持续时间，例如: 5m',
    labels      JSON COMMENT '标签，例如: {severity: warning}',
    annotations JSON COMMENT '注解，例如: {summary: 告警摘要}',
    enabled     BOOLEAN DEFAULT TRUE COMMENT '是否启用',
    create_by   VARCHAR(64),
    create_time DATETIME,
    update_time DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Prometheus规则管理';
