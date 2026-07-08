import request from '@/utils/request'

// 查询Prometheus配置列表
export function listConfig(query) {
  return request({
    url: '/monitor/prometheus/list',
    method: 'get',
    params: query
  })
}

// 查询Prometheus配置详细
export function getConfig(id) {
  return request({
    url: '/monitor/prometheus/' + id,
    method: 'get'
  })
}

// 新增Prometheus配置
export function addConfig(data) {
  return request({
    url: '/monitor/prometheus',
    method: 'post',
    data: data
  })
}

// 修改Prometheus配置
export function updateConfig(data) {
  return request({
    url: '/monitor/prometheus',
    method: 'put',
    data: data
  })
}

// 删除Prometheus配置
export function delConfig(id) {
  return request({
    url: '/monitor/prometheus/' + id,
    method: 'delete'
  })
}

// 健康检查
export function healthCheck() {
  return request({
    url: '/monitor/prometheus/health',
    method: 'get'
  })
}

// 执行PromQL查询
export function queryPromql(data) {
  return request({
    url: '/monitor/prometheus/query',
    method: 'post',
    data: data
  })
}

// 执行范围查询
export function queryRange(data) {
  return request({
    url: '/monitor/prometheus/queryRange',
    method: 'post',
    data: data
  })
}

// 测试连接
export function testConnection(data) {
  return request({
    url: '/monitor/prometheus/test',
    method: 'post',
    data: data
  })
}
