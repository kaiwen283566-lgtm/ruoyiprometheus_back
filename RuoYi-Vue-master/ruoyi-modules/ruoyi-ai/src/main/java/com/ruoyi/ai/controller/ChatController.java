package com.ruoyi.ai.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ai.domain.ChatHistory;
import com.ruoyi.ai.service.AiChatService;
import com.ruoyi.ai.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 智能问答Controller - 支持自定义模型
 */
@RestController
@RequestMapping("/ai/chat")
public class ChatController {
    
    @Autowired
    private ChatHistoryService chatHistoryService;
    
    @Autowired
    private AiChatService aiChatService;
    
    /**
     * 发送消息到AI
     */
    @PostMapping("/send")
    public AjaxResult sendMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String model = request.get("model"); // 可选，指定使用的模型
        
        if (message == null || message.isEmpty()) {
            return AjaxResult.error("消息不能为空");
        }
        
        try {
            // 调用AI服务
            String response = aiChatService.sendMessage(message, model);
            
            // 保存对话历史
            ChatHistory chatHistory = new ChatHistory();
            chatHistory.setUserMessage(message);
            chatHistory.setAiResponse(response);
            chatHistory.setModel(model != null ? model : "default");
            chatHistory.setTokens(message.length() + response.length());
            chatHistoryService.insertChatHistory(chatHistory);
            
            Map<String, Object> result = new HashMap<>();
            result.put("response", response);
            result.put("model", model != null ? model : "default");
            result.put("tokens", message.length() + response.length());
            
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("AI服务异常: " + e.getMessage());
        }
    }
    
    /**
     * 获取支持的模型列表
     */
    @GetMapping("/models")
    public AjaxResult getModels() {
        try {
            List<String> models = aiChatService.getSupportedModels();
            return AjaxResult.success(models);
        } catch (Exception e) {
            return AjaxResult.error("获取模型列表失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试模型连接
     */
    @PostMapping("/test")
    public AjaxResult testModel(@RequestBody Map<String, String> request) {
        String modelName = request.get("model");
        
        if (modelName == null || modelName.isEmpty()) {
            return AjaxResult.error("模型名称不能为空");
        }
        
        try {
            boolean connected = aiChatService.testModelConnection(modelName);
            Map<String, Object> result = new HashMap<>();
            result.put("model", modelName);
            result.put("connected", connected);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("测试连接失败: " + e.getMessage());
        }
    }
    
    /**
     * 查询对话记录列表
     */
    @GetMapping("/history/list")
    public AjaxResult list(ChatHistory chatHistory) {
        List<ChatHistory> list = chatHistoryService.selectChatHistoryList(chatHistory);
        return AjaxResult.success(list);
    }
    
    /**
     * 新增对话记录
     */
    @PostMapping("/history")
    public AjaxResult addHistory(@RequestBody ChatHistory chatHistory) {
        return toAjax(chatHistoryService.insertChatHistory(chatHistory));
    }
    
    /**
     * 删除对话记录
     */
    @DeleteMapping("/history/{id}")
    public AjaxResult removeHistory(@PathVariable Long id) {
        return toAjax(chatHistoryService.deleteChatHistoryById(id));
    }
    
    /**
     * 清空对话记录
     */
    @DeleteMapping("/history/clear")
    public AjaxResult clearHistory() {
        return toAjax(chatHistoryService.deleteChatHistoryAll());
    }
    
    private AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
