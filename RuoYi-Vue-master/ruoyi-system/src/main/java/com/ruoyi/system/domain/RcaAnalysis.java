package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class RcaAnalysis extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String alertName;
    private String alertFingerprint;
    private String alertSummary;
    private String rootCause;
    private String confidence;
    private String evidence;
    private String source;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlertName() { return alertName; }
    public void setAlertName(String alertName) { this.alertName = alertName; }

    public String getAlertFingerprint() { return alertFingerprint; }
    public void setAlertFingerprint(String alertFingerprint) { this.alertFingerprint = alertFingerprint; }

    public String getAlertSummary() { return alertSummary; }
    public void setAlertSummary(String alertSummary) { this.alertSummary = alertSummary; }

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }

    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }

    public String getEvidence() { return evidence; }
    public void setEvidence(String evidence) { this.evidence = evidence; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("alertName", getAlertName())
                .append("alertFingerprint", getAlertFingerprint())
                .append("alertSummary", getAlertSummary())
                .append("rootCause", getRootCause())
                .append("confidence", getConfidence())
                .append("evidence", getEvidence())
                .append("source", getSource())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
