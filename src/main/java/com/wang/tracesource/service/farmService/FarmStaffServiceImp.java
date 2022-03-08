package com.wang.tracesource.service.farmService;

import com.wang.tracesource.mapper.FarmStaffMapper;
import com.wang.tracesource.pojo.FarmStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName FarmStaffServiceImp
 * @Description TODO
 * @Author WY
 * @Date 2020/4/8 22:19
 * Version 1.0
 **/
@Service
public class FarmStaffServiceImp implements FarmStaffService {

    @Autowired
    FarmStaffMapper farmStaffMapper;

    //存储员工信息
    @Override
    public void saveFarmStaff(FarmStaff farmStaff) {
       farmStaffMapper.saveFarmStaff(farmStaff);
    }

    //通过id查找员工信息
    @Override
    public FarmStaff findFarmStaffById(int id) {

        return farmStaffMapper.findFarmStaffById(id);
    }

    // 通过工厂编号查询所有的养殖场员工信息
    @Override
    public Collection<FarmStaff> findAllFarmStaff(int id){

        return farmStaffMapper.findAllFarmStaff(id);
    }

    @Override
    public void deleteFarmStaffById(int id) {
        farmStaffMapper.deleteFarmStaffById(id);
    }

    @Override
    public void updateFarmStaffById(FarmStaff farmStaff) {

        farmStaffMapper.updateFarmStaffById(farmStaff);
       // return farmStaff.getWorkerPlantId();
    }
}
