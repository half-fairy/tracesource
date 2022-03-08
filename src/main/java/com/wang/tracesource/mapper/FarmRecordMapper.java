package com.wang.tracesource.mapper;


import com.wang.tracesource.pojo.FarmRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository("FarmRecordMapper")
public interface FarmRecordMapper {
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
