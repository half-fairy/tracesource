package com.wang.tracesource.mapper;

import com.wang.tracesource.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//这个注解表示这是一个mybatis的mapper类--->是dao层数据，所以需要加@Repository注解
@Mapper
//将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
@Repository
public interface DepartmentMapper {

    //查询厂商
    public Department queryDepartmentByName(String name);

    //通过注册功能将新注册的用户写入数据库
    public void insertDepartment(Department department);

    //通过工厂id查询
    public Department queryDepartmentById(int id);
}
