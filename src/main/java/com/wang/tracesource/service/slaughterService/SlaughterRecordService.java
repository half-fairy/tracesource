package com.wang.tracesource.service.slaughterService;

import com.wang.tracesource.pojo.SlaughterRecord;

import java.util.Collection;

public interface SlaughterRecordService {
    //存储屠宰场记录信息
    public void saveSlaughterRecord(SlaughterRecord slaughterRecord);


    //通过id查询屠宰场记录信息
    public SlaughterRecord findSlaughterRecordById(int id);

    //    查询所有的屠宰场记录信息
    public Collection<SlaughterRecord> findAllSlaughterRecord();

    //    通过牛只编号查找所有的屠宰场记录信息
    public Collection<SlaughterRecord> findSlaughterRecordByCattleId(int id);

    //通过订单编号查询查询屠宰记录的哈希值
    public  String findSlaughterRecordShacodeById(int id);

}
