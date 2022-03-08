package com.wang.tracesource.mapper;


import com.wang.tracesource.pojo.SlaughterRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface SlaughterRecordMapper {

    //存储屠宰场记录信息
    public void saveSlaughterRecord(SlaughterRecord slaughterRecord);

    //通过i订单编号查询屠宰场记录信息
    public SlaughterRecord findSlaughterRecordById(int id);

    //    查询所有的屠宰场记录信息findSlaughterRecordByCattleId
    public Collection<SlaughterRecord> findAllSlaughterRecord();

    //    通过牛只编号查找所有的屠宰场记录信息
    public Collection<SlaughterRecord> findSlaughterRecordByCattleId(int id);

    //通过订单编号查询查询屠宰记录的哈希值
    public  String findSlaughterRecordShacodeById(int id);
}
