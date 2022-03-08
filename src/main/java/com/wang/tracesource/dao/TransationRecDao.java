package com.wang.tracesource.dao;

import com.wang.tracesource.pojo.TransactionRec;
import org.springframework.stereotype.Repository;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TransationRecDao
 * @Description TODO
 * @Author WY
 * @Date 2020/4/12 15:32
 * Version 1.0
 **/
//注入给spring：此处是为了后面消费者通过牛只id/记录id来验证信息正确性
@Repository
public class TransationRecDao {

    //存储牛只id或者记录（养殖）的id 和 交易的映射
    private static Map<Integer,TransactionRec> transactionRecMap=null;

    static {
        transactionRecMap=new HashMap<Integer, TransactionRec>();
    }

    //增加一个交易信息
    public void save(TransactionRec constracts)
    {
        transactionRecMap.put(constracts.getId(),constracts);
        System.out.println(constracts);
    }
    //通过牛只id和交易类型得到交易,返回交易的地址
    public TransactionReceipt getTransationByIdType(Integer id){

        System.out.println(transactionRecMap.get(id));
        //通过 牛只id或者记录（养殖）的id 获取交易信息
        return transactionRecMap.get(id).getTransactionReceipt();
    }







}
