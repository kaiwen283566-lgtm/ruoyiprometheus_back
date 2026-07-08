package com.ruoyi.monitor.service;

import com.ruoyi.monitor.domain.ConnectionLog;
import com.ruoyi.monitor.mapper.ConnectionLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 连接日志Service
 */
@Service
public class ConnectionLogService {
    
    @Autowired
    private ConnectionLogMapper connectionLogMapper;
    
    /**
     * 查询连接日志列表
     */
    public List<ConnectionLog> selectConnectionLogList(ConnectionLog connectionLog) {
        return connectionLogMapper.selectConnectionLogList(connectionLog);
    }
    
    /**
     * 新增连接日志
     */
    public int insertConnectionLog(ConnectionLog connectionLog) {
        return connectionLogMapper.insertConnectionLog(connectionLog);
    }
    
    /**
     * 删除连接日志
     */
    public int deleteConnectionLogById(Long id) {
        return connectionLogMapper.deleteConnectionLogById(id);
    }
    
    /**
     * 清空连接日志
     */
    public int deleteConnectionLogAll() {
        return connectionLogMapper.deleteConnectionLogAll();
    }
}
