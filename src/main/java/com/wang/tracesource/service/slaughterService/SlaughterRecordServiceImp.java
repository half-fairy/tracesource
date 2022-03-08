package com.wang.tracesource.service.slaughterService;

import com.wang.tracesource.mapper.SlaughterRecordMapper;
import com.wang.tracesource.pojo.SlaughterRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName SlaughterRecordServiceImp
 * @Description TODO
 * @Author WY
 * @Date 2020/4/10 11:58
 * Version 1.0
 **/
@Service
public class SlaughterRecordServiceImp implements SlaughterRecordService{

    @Autowired
    SlaughterRecordMapper slaughterRecordMapper;


    @Override
    public void saveSlaughterRecord(SlaughterRecord slaughterRecord) {
        slaughterRecordMapper.saveSlaughterRecord(slaughterRecord);
    }

    @Override
    public SlaughterRecord findSlaughterRecordById(int id) {
        return slaughterRecordMapper.findSlaughterRecordById(id);
    }


    @Override
    public Collection<SlaughterRecord> findAllSlaughterRecord() {
        return slaughterRecordMapper.findAllSlaughterRecord();
    }

    @Override
    public Collection<SlaughterRecord> findSlaughterRecordByCattleId(int id) {
        return slaughterRecordMapper.findSlaughterRecordByCattleId(id);
    }

    @Override
    public String findSlaughterRecordShacodeById(int id) {
        return slaughterRecordMapper.findSlaughterRecordShacodeById(id);
    }
}
