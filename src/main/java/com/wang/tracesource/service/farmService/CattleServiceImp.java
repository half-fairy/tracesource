package com.wang.tracesource.service.farmService;

import com.wang.tracesource.mapper.CattleMapper;
import com.wang.tracesource.pojo.Cattle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName CattleServiceImp
 * @Description TODO
 * @Author WY
 * @Date 2020/4/8 11:47
 * Version 1.0
 **/
@Service
public class CattleServiceImp implements CattleService {
    @Autowired
    CattleMapper cattleMapper;


    //通过这个方法来调mapper层，也就是调用dao曾数据
    @Override
    public void saveCattle(Cattle cattle) {
        cattleMapper.saveCattle(cattle);
    }

    @Override
    public Cattle findCattleById(int id) {
        return cattleMapper.findCattleById(id);
    }

    @Override
    public Collection<Cattle> findAllCattle() {
        return cattleMapper.findAllCattle();
    }

    @Override
    public String findCattleHahByid(int id) {
        return cattleMapper.findCattleHahByid(id);
    }

    @Override
    public void updateCattle(Cattle cattle) {
        cattleMapper.updateCattle(cattle);
    }
}
