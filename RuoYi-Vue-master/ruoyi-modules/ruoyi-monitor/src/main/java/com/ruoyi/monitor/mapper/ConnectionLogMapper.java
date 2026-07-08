package com.ruoyi.monitor.mapper;

import com.ruoyi.monitor.domain.ConnectionLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 连接日志Mapper
 */
@Mapper
public interface ConnectionLogMapper {
    
    /**
     * 查询连接日志列表
     */
    List<ConnectionLog> selectConnectionLogList(ConnectionLog connectionLog);
    
    /**
     * 新增连接日志
     */
    int insertConnectionLog(ConnectionLog connectionLog);
    
    /**
     * 删除连接日志
     */
    int deleteConnectionLogById(Long id);
    
    /**
     * 清空连接日志
     */
    int deleteConnectionLogAll();
}
