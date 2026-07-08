package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.ChatHistory;
import com.ruoyi.ai.mapper.ChatHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对话记录Service
 */
@Service
public class ChatHistoryService {
    
    @Autowired
    private ChatHistoryMapper chatHistoryMapper;
    
    /**
     * 查询对话记录列表
     */
    public List<ChatHistory> selectChatHistoryList(ChatHistory chatHistory) {
        return chatHistoryMapper.selectChatHistoryList(chatHistory);
    }
    
    /**
     * 新增对话记录
     */
    public int insertChatHistory(ChatHistory chatHistory) {
        return chatHistoryMapper.insertChatHistory(chatHistory);
    }
    
    /**
     * 删除对话记录
     */
    public int deleteChatHistoryById(Long id) {
        return chatHistoryMapper.deleteChatHistoryById(id);
    }
    
    /**
     * 清空对话记录
     */
    public int deleteChatHistoryAll() {
        return chatHistoryMapper.deleteChatHistoryAll();
    }
}
