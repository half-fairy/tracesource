package com.wang.tracesource.mapper;


import com.wang.tracesource.pojo.Cattle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface CattleMapper {
    //存储牛只信息
    public void saveCattle(Cattle cattle);

    //通过id查询牛只信息
    public Cattle findCattleById(int id);

//    查询所有的牛只信息
    public Collection<Cattle> findAllCattle();

    //通过牛只id查找hash值
    public String findCattleHahByid(int id);

    //更新牛只的在栏状态
    public void updateCattle(Cattle cattle);
}
