package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RcaKnowledge extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String alertPattern;
    private String symptoms;
    private String rootCause;
    private String solution;
    private String tags;
    private String confidence;
    private Integer hitCount;
    private String createdBy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAlertPattern() { return alertPattern; }
    public void setAlertPattern(String alertPattern) { this.alertPattern = alertPattern; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }

    public Integer getHitCount() { return hitCount; }
    public void setHitCount(Integer hitCount) { this.hitCount = hitCount; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("alertPattern", getAlertPattern())
                .append("symptoms", getSymptoms())
                .append("rootCause", getRootCause())
                .append("solution", getSolution())
                .append("tags", getTags())
                .append("confidence", getConfidence())
                .append("hitCount", getHitCount())
                .append("createdBy", getCreatedBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
