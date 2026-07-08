-- RCA 分析记录表
CREATE TABLE IF NOT EXISTS monitor_rca_analysis (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    alert_name  VARCHAR(255),
    alert_fingerprint VARCHAR(255),
    alert_summary TEXT,
    root_cause  TEXT,
    confidence  VARCHAR(20)  DEFAULT 'LOW',
    evidence    JSON,
    source      VARCHAR(20)  DEFAULT 'MANUAL',
    status      VARCHAR(20)  DEFAULT 'PENDING',
    create_time DATETIME,
    update_time DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='RCA分析记录';

-- RCA 知识库表
CREATE TABLE IF NOT EXISTS monitor_rca_knowledge (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(500),
    alert_pattern VARCHAR(500),
    symptoms    TEXT,
    root_cause  TEXT NOT NULL,
    solution    TEXT NOT NULL,
    tags        VARCHAR(500),
    confidence  VARCHAR(20) DEFAULT 'HUMAN_CONFIRMED',
    hit_count   INT DEFAULT 0,
    created_by  VARCHAR(64),
    create_time DATETIME,
    update_time DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='RCA知识库';
