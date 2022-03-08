package com.wang.tracesource.service.farmService;

import com.wang.tracesource.pojo.FarmRecord;

import java.util.Collection;

public interface FarmRecordService {
    //存储养殖场员工信息
    public void saveFarmRecord(FarmRecord farmRecord);

    //通过id查询养殖场员工信息
    public FarmRecord findFarmRecordById(int id);

    //    查询所有的养殖场员工信息
    public Collection<FarmRecord> findAllFarmRecord();
    //通过牛只编号查找养殖记录
    public Collection<FarmRecord> findFarmRecordByCattleId(int id);

    //通过订单编号查询查询养殖记录的哈希值
    public  String findFarmRecordShacodeById(int id);
}
