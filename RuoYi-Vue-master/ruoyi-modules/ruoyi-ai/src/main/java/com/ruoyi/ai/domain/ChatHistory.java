package com.ruoyi.ai.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对话记录表 chat_history
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 用户消息 */
    private String userMessage;

    /** AI回复 */
    private String aiResponse;

    /** 模型 */
    private String model;

    /** 令牌数 */
    private Integer tokens;
}
