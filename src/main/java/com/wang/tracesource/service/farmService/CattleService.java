package com.wang.tracesource.service.farmService;

import com.wang.tracesource.pojo.Cattle;

import java.util.Collection;

public interface CattleService {
    //存储牛只信息
    public void saveCattle(Cattle cattle);

    //查询牛只信息
    public Cattle findCattleById(int id);

    //    查询所有的牛只信息
    public Collection<Cattle> findAllCattle();

    //通过牛只id查找hash值
    public String findCattleHahByid(int id);

    //更新牛只的在栏状态
    public void updateCattle(Cattle cattle);
}
