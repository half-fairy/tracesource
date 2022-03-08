package com.wang.tracesource.service.farmService;

import com.wang.tracesource.mapper.FarmRecordMapper;
import com.wang.tracesource.pojo.FarmRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName FarmRecordServiceImp
 * @Description TODO
 * @Author WY
 * @Date 2020/4/9 10:48
 * Version 1.0
 **/
@Service
public class FarmRecordServiceImp implements FarmRecordService {

    @Autowired
    FarmRecordMapper farmRecordMapper;

    @Override
    public FarmRecord findFarmRecordById(int id) {

        return farmRecordMapper.findFarmRecordById(id);
    }

    @Override
    public void saveFarmRecord(FarmRecord farmRecord) {
        farmRecordMapper.saveFarmRecord(farmRecord);
    }

    @Override
    public Collection<FarmRecord> findAllFarmRecord() {

        return farmRecordMapper.findAllFarmRecord();
    }

    @Override
    public Collection<FarmRecord> findFarmRecordByCattleId(int id) {
        return farmRecordMapper.findFarmRecordByCattleId(id);
    }

    @Override
    public String findFarmRecordShacodeById(int id) {
        return farmRecordMapper.findFarmRecordShacodeById(id);
    }
}
