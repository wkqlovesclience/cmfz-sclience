package sclience.service;

import sclience.entity.Log;

import java.util.List;

public interface LogService {
    /**
     * 查询所有日志
     */
    List<Log> findAllLogs(Integer pageNum,Integer pageSize);
    /**
     * 查询日志总记录
     */
    Integer findTotals();
}
