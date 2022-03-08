package com.wang.tracesource.dao;

import com.wang.tracesource.pojo.AllConstracts;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AllContractsDao
 * @Description TODO
 * @Author WY
 * @Date 2020/4/6 20:59
 * Version 1.0
 **/
//注入给spring：获取部署合约的哈希值来加载合约
@Repository
public class AllContractsDao {
    //模拟数据库中的数据
    private static Map<Integer,AllConstracts> constractsMap=null;


    static {
        constractsMap=new HashMap<Integer,AllConstracts>();
    }
    //增加一个合约信息
    public void save(AllConstracts constracts)
    {

        constractsMap.put(constracts.getCondtractsNums(),constracts);
    }

    //通过id得到合约,返回合约的地址
    public String getContractAddressById(Integer id){
        return constractsMap.get(id).getContractAddress();
    }




}
