-- AIOps 业务模块数据库表结构

-- 监控告警模块
CREATE TABLE IF NOT EXISTS prometheus_config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name VARCHAR(255) NOT NULL COMMENT '配置名称',
    prometheus_url VARCHAR(512) NOT NULL COMMENT 'Prometheus URL',
    alertmanager_url VARCHAR(512) COMMENT 'Alertmanager URL',
    alertmanager_enabled TINYINT(1) DEFAULT 0 COMMENT '是否启用Alertmanager',
    scrape_interval VARCHAR(32) DEFAULT '15s' COMMENT '采集间隔',
    enabled TINYINT(1) DEFAULT 1 COMMENT '是否启用',
    last_sync TIMESTAMP NULL COMMENT '最后同步时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Prometheus配置表';

CREATE TABLE IF NOT EXISTS connection_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
    type VARCHAR(32) NOT NULL COMMENT '类型',
    prometheus_status VARCHAR(32) COMMENT 'Prometheus状态',
    alertmanager_status VARCHAR(32) COMMENT 'Alertmanager状态',
    latency INT COMMENT '延迟(ms)',
    version VARCHAR(64) COMMENT '版本',
    message TEXT COMMENT '结果消息',
    operator VARCHAR(64) DEFAULT 'system' COMMENT '操作人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='连接日志表';

-- 日志检索模块
CREATE TABLE IF NOT EXISTS log_query (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    query TEXT NOT NULL COMMENT '查询语句',
    minutes INT DEFAULT 30 COMMENT '时间范围(分钟)',
    query_limit INT DEFAULT 100 COMMENT '限制条数',
    result TEXT COMMENT '查询结果',
    status VARCHAR(32) DEFAULT 'success' COMMENT '查询状态',
    error_msg TEXT COMMENT '错误信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志查询记录表';

-- 智能问答模块
CREATE TABLE IF NOT EXISTS chat_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    user_id BIGINT COMMENT '用户ID',
    user_message TEXT NOT NULL COMMENT '用户消息',
    ai_response TEXT COMMENT 'AI回复',
    model VARCHAR(64) COMMENT '模型',
    tokens INT COMMENT '令牌数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对话记录表';
