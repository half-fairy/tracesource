package com.wang.tracesource.service.farmService;

import com.wang.tracesource.pojo.FarmStaff;

import java.util.Collection;

public interface FarmStaffService {

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
