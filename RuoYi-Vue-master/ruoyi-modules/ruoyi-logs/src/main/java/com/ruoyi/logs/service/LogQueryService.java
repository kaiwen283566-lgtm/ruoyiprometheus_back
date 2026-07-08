package com.ruoyi.logs.service;

import com.ruoyi.logs.domain.LogQuery;
import com.ruoyi.logs.mapper.LogQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志查询Service
 */
@Service
public class LogQueryService {
    
    @Autowired
    private LogQueryMapper logQueryMapper;
    
    /**
     * 查询日志查询记录列表
     */
    public List<LogQuery> selectLogQueryList(LogQuery logQuery) {
        return logQueryMapper.selectLogQueryList(logQuery);
    }
    
    /**
     * 新增日志查询记录
     */
    public int insertLogQuery(LogQuery logQuery) {
        return logQueryMapper.insertLogQuery(logQuery);
    }
    
    /**
     * 删除日志查询记录
     */
    public int deleteLogQueryById(Long id) {
        return logQueryMapper.deleteLogQueryById(id);
    }
}
