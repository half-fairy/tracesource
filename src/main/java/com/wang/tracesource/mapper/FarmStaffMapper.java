package com.wang.tracesource.mapper;


import com.wang.tracesource.pojo.FarmStaff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface FarmStaffMapper {

    //存储养殖场员工信息
    public void saveFarmStaff(FarmStaff cattle);

    //通过id查询养殖场员工信息
    public FarmStaff findFarmStaffById(int id);

    // 通过工厂编号查询所有的养殖场员工信息
    public Collection<FarmStaff> findAllFarmStaff(int id);

    //通过id删除该员工
    public void deleteFarmStaffById(int id);

    //更新员工信息
    public void updateFarmStaffById(FarmStaff farmStaff);




}
