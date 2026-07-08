package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class PrometheusRule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private String expr;
    private String forDuration;
    private String labels;
    private String annotations;
    private Boolean enabled;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getExpr() { return expr; }
    public void setExpr(String expr) { this.expr = expr; }

    public String getForDuration() { return forDuration; }
    public void setForDuration(String forDuration) { this.forDuration = forDuration; }

    public String getLabels() { return labels; }
    public void setLabels(String labels) { this.labels = labels; }

    public String getAnnotations() { return annotations; }
    public void setAnnotations(String annotations) { this.annotations = annotations; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
