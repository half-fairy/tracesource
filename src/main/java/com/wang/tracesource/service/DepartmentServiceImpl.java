package com.wang.tracesource.service;

import com.wang.tracesource.mapper.DepartmentMapper;
import com.wang.tracesource.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO
 * @Author WY
 * @Date 2020/3/22 16:28
 * Version 1.0
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentMapper departmentMapper;

    //通过这个方法来调mapper层，也就是调用dao曾数据
    @Override
    public Department queryDepartmentByName(String name) {
        return departmentMapper.queryDepartmentByName(name);
    }

    @Override
    public Department queryDepartmentById(int id) {
        return departmentMapper.queryDepartmentById(id);
    }

    @Override
    public void insertDepartment(Department department)
    {
        departmentMapper.insertDepartment(department);
    }
}
