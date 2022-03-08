package com.wang.tracesource.service.LogisticsService;

import com.wang.tracesource.mapper.LogisticsRecordMapper;
import com.wang.tracesource.pojo.LogisticsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName LogisticsServiceIml
 * @Description TODO
 * @Author WY
 * @Date 2020/4/11 22:43
 * Version 1.0
 **/
@Service
public class LogisticsServiceIml implements LogisticsService {

    @Autowired
    LogisticsRecordMapper logisticsRecordMapper;
    @Override
    public void saveLogisticsRecord(LogisticsRecord logisticsRecord) {
            logisticsRecordMapper.saveLogisticsRecord(logisticsRecord);
    }

    @Override
    public LogisticsRecord findLogisticsRecordById(int id) {
        return logisticsRecordMapper.findLogisticsRecordById(id);
    }

    @Override
    public Collection<LogisticsRecord> findAllLogisticsRecord() {
        return logisticsRecordMapper.findAllLogisticsRecord();
    }

    @Override
    public Collection<LogisticsRecord> findAllLogisticsRecordByCattleId(int id) {
        return logisticsRecordMapper.findAllLogisticsRecordByCattleId(id);
    }

    @Override
    public String findLogisticsRecordShacodeById(int id) {
        return logisticsRecordMapper.findLogisticsRecordShacodeById(id);
    }
}
