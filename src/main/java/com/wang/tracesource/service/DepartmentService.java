package com.wang.tracesource.service;

import com.wang.tracesource.pojo.Department;

public interface DepartmentService {
    public Department queryDepartmentByName(String name);

    public void insertDepartment(Department department);


    //通过工厂id查询
    public Department queryDepartmentById(int id);
}
