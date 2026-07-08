import request from '@/utils/request'

// 发送消息到AI
export function sendMessage(data) {
  return request({
    url: '/ai/chat/send',
    method: 'post',
    data: data
  })
}

// 获取支持的模型列表
export function getModels() {
  return request({
    url: '/ai/chat/models',
    method: 'get'
  })
}

// 测试模型连接
export function testModel(data) {
  return request({
    url: '/ai/chat/test',
    method: 'post',
    data: data
  })
}

// 查询对话历史列表
export function listHistory(query) {
  return request({
    url: '/ai/chat/history/list',
    method: 'get',
    params: query
  })
}

// 新增对话记录
export function addHistory(data) {
  return request({
    url: '/ai/chat/history',
    method: 'post',
    data: data
  })
}

// 删除对话记录
export function delHistory(id) {
  return request({
    url: '/ai/chat/history/' + id,
    method: 'delete'
  })
}

// 清空对话历史
export function clearHistory() {
  return request({
    url: '/ai/chat/history/clear',
    method: 'delete'
  })
}
