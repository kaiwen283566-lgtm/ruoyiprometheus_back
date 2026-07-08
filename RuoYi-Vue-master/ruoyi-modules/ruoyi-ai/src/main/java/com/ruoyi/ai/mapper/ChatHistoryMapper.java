package com.ruoyi.ai.mapper;

import com.ruoyi.ai.domain.ChatHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 对话记录Mapper
 */
@Mapper
public interface ChatHistoryMapper {
    
    /**
     * 查询对话记录列表
     */
    List<ChatHistory> selectChatHistoryList(ChatHistory chatHistory);
    
    /**
     * 新增对话记录
     */
    int insertChatHistory(ChatHistory chatHistory);
    
    /**
     * 删除对话记录
     */
    int deleteChatHistoryById(Long id);
    
    /**
     * 清空对话记录
     */
    int deleteChatHistoryAll();
}
