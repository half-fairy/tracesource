package com.wang.tracesource.pojo;

import lombok.Data;

/**
 * @ClassName AllConstracts
 * @Description TODO
 * @Author WY
 * @Date 2020/4/6 20:20
 * Version 1.0
 **/
//存储的是所部署的智能合约的信息
    @Data//所有的get set方法


public class AllConstracts {

    //智能合约的数量
    private int condtractsNums;

    //智能合约的地址
    private String contractAddress;

    //智能合约的创建者
    private String creator;

    public void contractNum()
    {
        this.condtractsNums++;
    }
    public AllConstracts(int condtractsNums, String contractAddress, String creator)
    {
        this.condtractsNums=condtractsNums;
        this.contractAddress=contractAddress;
        this.creator=creator;
    }

    @Override
    public String toString() {
        return "AllConstracts{" +
                "condtractsNums=" + condtractsNums +
                ", contractAddress='" + contractAddress + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
