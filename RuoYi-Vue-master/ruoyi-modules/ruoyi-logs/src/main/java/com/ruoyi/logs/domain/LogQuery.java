package com.ruoyi.logs.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志查询记录 log_query
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogQuery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 查询语句 */
    private String query;

    /** 时间范围(分钟) */
    private Integer minutes;

    /** 限制条数 */
    private Integer limit;

    /** 查询结果 */
    private String result;

    /** 查询状态 */
    private String status;

    /** 错误信息 */
    private String errorMsg;
}
