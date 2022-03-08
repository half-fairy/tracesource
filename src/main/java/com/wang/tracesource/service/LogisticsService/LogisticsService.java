package com.wang.tracesource.service.LogisticsService;

import com.wang.tracesource.pojo.LogisticsRecord;

import java.util.Collection;

public interface LogisticsService {
    //存储屠宰场记录信息
    public void saveLogisticsRecord(LogisticsRecord logisticsRecord);

    //通过id查询屠宰场记录信息
    public LogisticsRecord findLogisticsRecordById(int id);

    //    查询所有的屠宰场记录信息
    public Collection<LogisticsRecord> findAllLogisticsRecord();

    //   通过牛只编号查找所有的物流记录信息
    public Collection<LogisticsRecord>findAllLogisticsRecordByCattleId(int id);

    //通过订单编号查询查询屠物流记录的哈希值
    public  String findLogisticsRecordShacodeById(int id);
}
