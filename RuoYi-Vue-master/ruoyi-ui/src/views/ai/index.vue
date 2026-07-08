<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card>
          <div slot="header" class="card-header">
            <span>智能对话</span>
            <div class="header-actions">
              <el-select v-model="selectedModel" placeholder="选择模型" size="small" style="width: 200px; margin-right: 10px">
                <el-option label="默认模型" value="" />
                <el-option v-for="model in availableModels" :key="model" :label="model" :value="model" />
              </el-select>
              <el-button type="primary" plain icon="el-icon-delete" size="mini" @click="handleClearHistory">清空历史</el-button>
            </div>
          </div>

          <div class="chat-container">
            <div ref="messageListRef" class="message-list">
              <div v-if="messages.length === 0" class="empty-state">
                <i class="el-icon-chat-dot-round empty-icon"></i>
                <p>开始对话吧！</p>
              </div>
              <div v-for="(msg, index) in messages" :key="index" class="message" :class="msg.role">
                <div class="message-content">
                  <div class="message-header">
                    <span class="role">{{ msg.role === 'user' ? '我' : 'AI' }}</span>
                    <span class="time">{{ msg.time }}</span>
                  </div>
                  <div class="message-text">{{ msg.content }}</div>
                  <div v-if="msg.model" class="message-model">模型: {{ msg.model }}</div>
                </div>
              </div>
            </div>
            <div class="input-area">
              <el-input
                v-model="inputMessage"
                type="textarea"
                :rows="3"
                placeholder="输入您的问题... (Ctrl+Enter 发送)"
                @keydown.enter.ctrl.native="handleSendMessage"
              />
              <el-button type="primary" icon="el-icon-s-promotion" :loading="sending" @click="handleSendMessage">发送</el-button>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card>
          <div slot="header">对话历史</div>
          <el-timeline v-if="historyList.length > 0">
            <el-timeline-item v-for="(history, index) in historyList" :key="index" :timestamp="history.createTime" placement="top">
              <div class="history-item">
                <div class="history-question">{{ history.userMessage }}</div>
                <div class="history-answer">{{ history.aiResponse }}</div>
                <div v-if="history.model" class="history-model">模型: {{ history.model }}</div>
              </div>
            </el-timeline-item>
          </el-timeline>
          <el-empty v-else description="暂无对话历史" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { sendMessage as sendAiMessage, getModels, listHistory, clearHistory as clearChatHistory } from '@/api/ai/chat'

export default {
  name: 'AiChat',
  data() {
    return {
      messages: [],
      inputMessage: '',
      sending: false,
      historyList: [],
      selectedModel: '',
      availableModels: []
    }
  },
  created() {
    this.loadModels()
    this.getHistoryList()
  },
  methods: {
    handleSendMessage() {
      if (!this.inputMessage.trim()) {
        this.$modal.msgWarning('请输入问题')
        return
      }
      const userMessage = this.inputMessage
      this.messages.push({
        role: 'user',
        content: userMessage,
        time: new Date().toLocaleTimeString()
      })
      this.inputMessage = ''
      this.sending = true
      sendAiMessage({
        message: userMessage,
        model: this.selectedModel || null
      }).then(response => {
        const data = response.data || {}
        this.messages.push({
          role: 'assistant',
          content: data.response || '暂无回复',
          time: new Date().toLocaleTimeString(),
          model: data.model
        })
        this.getHistoryList()
      }).catch(error => {
        this.$modal.msgError('AI服务异常: ' + (error.message || '请求失败'))
      }).finally(() => {
        this.sending = false
        this.scrollToBottom()
      })
    },
    handleClearHistory() {
      this.$modal.confirm('确定要清空对话历史吗？').then(() => {
        return clearChatHistory()
      }).then(() => {
        this.$modal.msgSuccess('清空成功')
        this.messages = []
        this.historyList = []
      }).catch(() => {})
    },
    getHistoryList() {
      listHistory({}).then(response => {
        this.historyList = response.rows || response.data || []
      })
    },
    loadModels() {
      getModels().then(response => {
        this.availableModels = response.data || []
      }).catch(() => {})
    },
    scrollToBottom() {
      this.$nextTick(() => {
        if (this.$refs.messageListRef) {
          this.$refs.messageListRef.scrollTop = this.$refs.messageListRef.scrollHeight
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.chat-container {
  height: 600px;
  display: flex;
  flex-direction: column;
}
.message-list {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 4px;
}
.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  color: #909399;
}
.empty-icon {
  font-size: 48px;
  color: #dcdfe6;
}
.message {
  margin-bottom: 20px;
}
.message.user {
  display: flex;
  justify-content: flex-end;
}
.message.assistant {
  display: flex;
  justify-content: flex-start;
}
.message-content {
  max-width: 70%;
  background: #fff;
  border-radius: 8px;
  padding: 12px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  font-size: 12px;
  color: #909399;
}
.role {
  font-weight: 600;
}
.message-text {
  line-height: 1.6;
  word-break: break-word;
}
.message-model,
.history-model {
  margin-top: 8px;
  font-size: 11px;
  color: #909399;
  font-style: italic;
}
.input-area {
  margin-top: 20px;
  display: flex;
  gap: 10px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.header-actions {
  display: flex;
  align-items: center;
}
.history-item {
  margin-bottom: 15px;
  padding: 10px;
  background: #f5f7fa;
  border-radius: 4px;
}
.history-question {
  font-weight: 600;
  margin-bottom: 5px;
  color: #303133;
}
.history-answer {
  color: #606266;
  font-size: 14px;
}
</style>
