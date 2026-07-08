import request from '@/utils/request'

// 查询日志查询记录列表
export function listQuery(query) {
  return request({
    url: '/logs/query/list',
    method: 'get',
    params: query
  })
}

// 新增日志查询记录
export function addQuery(data) {
  return request({
    url: '/logs/query',
    method: 'post',
    data: data
  })
}

// 删除日志查询记录
export function delQuery(id) {
  return request({
    url: '/logs/query/' + id,
    method: 'delete'
  })
}

// 执行LogQL查询
export function executeQuery(data) {
  return request({
    url: '/logs/query/execute',
    method: 'post',
    data: data
  })
}

// 执行即时查询
export function executeInstantQuery(data) {
  return request({
    url: '/logs/query/instant',
    method: 'post',
    data: data
  })
}

// 获取Loki标签
export function getLabels() {
  return request({
    url: '/logs/query/labels',
    method: 'get'
  })
}

// 获取标签值
export function getLabelValues(label) {
  return request({
    url: '/logs/query/labels/' + label + '/values',
    method: 'get'
  })
}

// 健康检查
export function healthCheck() {
  return request({
    url: '/logs/query/health',
    method: 'get'
  })
}
