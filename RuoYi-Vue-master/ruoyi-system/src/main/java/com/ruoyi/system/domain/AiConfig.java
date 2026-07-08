package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class AiConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String configKey;
    private String configValue;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getConfigKey() { return configKey; }
    public void setConfigKey(String configKey) { this.configKey = configKey; }

    public String getConfigValue() { return configValue; }
    public void setConfigValue(String configValue) { this.configValue = configValue; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
