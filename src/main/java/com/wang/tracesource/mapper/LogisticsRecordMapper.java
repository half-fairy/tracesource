package com.wang.tracesource.mapper;


import com.wang.tracesource.pojo.LogisticsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface LogisticsRecordMapper {
    //存储屠宰场记录信息
    public void saveLogisticsRecord(LogisticsRecord logisticsRecord);

    //通过订单编号id查询物流记录信息
    public LogisticsRecord findLogisticsRecordById(int id);

    //    查询所有的物流记录信息
    public Collection<LogisticsRecord> findAllLogisticsRecord();

    //   通过牛只编号查找所有的物流记录信息
    public Collection<LogisticsRecord>findAllLogisticsRecordByCattleId(int id);

    //通过订单编号查询查询屠物流记录的哈希值
    public  String findLogisticsRecordShacodeById(int id);

}
