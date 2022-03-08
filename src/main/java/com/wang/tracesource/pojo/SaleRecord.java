package com.wang.tracesource.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SaleRecord
 * @Description TODO
 * @Author WY
 * @Date 2020/5/2 18:08
 * Version 1.0
 **/
@Data
public class SaleRecord {
    private   int recordId;//
    private   int beefId;//肉块编号这里的肉块编号也就是前面的牛只编号，方便整条信息追溯
    private   int storageTem;//存储温度
    private   int beefWeight;//肉块重量
    private Date depotTime;//入库时间
    private   String saleLocatin;//销售地点
    private   Date saleTime;//上架时间
    private   int  operatorId;//操作员编号
    //为了提供加密的字符串，存进数据库和区块链中
    private String shaCode;

    public SaleRecord(int recordId, int beefId, int storageTem, int beefWeight, Date depotTime, String saleLocatin, Date saleTime, int operatorId, String shaCode) {
        this.recordId = recordId;
        this.beefId = beefId;
        this.storageTem = storageTem;
        this.beefWeight = beefWeight;
        this.depotTime = depotTime;
        this.saleLocatin = saleLocatin;
        this.saleTime = saleTime;
        this.operatorId = operatorId;
        this.shaCode = shaCode;
    }


    @Override
    public String toString() {
        return "LogisticsRecord{" +
                "recordId=" + recordId +
                ", beefId=" + beefId +
                ", storageTem=" + storageTem +
                ", beefWeight=" + beefWeight +
                ", depotTime=" + depotTime +
                ", saleLocatin='" + saleLocatin + '\'' +
                ", saleTime=" + saleTime +
                ", operatorId=" + operatorId +
                ", shaCode='" + shaCode + '\'' +
                '}';
    }
}
