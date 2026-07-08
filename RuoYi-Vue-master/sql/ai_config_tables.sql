-- AI 配置表
CREATE TABLE IF NOT EXISTS monitor_ai_config (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    config_key  VARCHAR(100) NOT NULL UNIQUE COMMENT '配置键，例如: default.baseUrl, deepseek.apiKey',
    config_value TEXT COMMENT '配置值',
    description VARCHAR(500) COMMENT '配置说明',
    create_by   VARCHAR(64),
    create_time DATETIME,
    update_time DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI配置';
