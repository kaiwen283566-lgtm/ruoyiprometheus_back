package com.ruoyi.logs.mapper;

import com.ruoyi.logs.domain.LogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 日志查询Mapper
 */
@Mapper
public interface LogQueryMapper {
    
    /**
     * 查询日志查询记录列表
     */
    List<LogQuery> selectLogQueryList(LogQuery logQuery);
    
    /**
     * 新增日志查询记录
     */
    int insertLogQuery(LogQuery logQuery);
    
    /**
     * 删除日志查询记录
     */
    int deleteLogQueryById(Long id);
}
