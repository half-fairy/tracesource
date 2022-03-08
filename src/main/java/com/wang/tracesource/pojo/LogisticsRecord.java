package com.wang.tracesource.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName LogisticsRecord
 * @Description TODO
 * @Author WY
 * @Date 2020/4/11 22:25
 * Version 1.0
 **/
@Data
public class LogisticsRecord {
    private   int recordId;//
    private   int beefId;//肉块编号这里的肉块编号也就是前面的牛只编号，方便整条信息追溯
    private   int driverId;//司机编号
    private   String driverName;//司机名字
    private Date departureTime;//车辆出发时间
    private   String transitionTime;//运输时长
    private   String carTemperature;//车辆温度
    private   String destination;//目的地
    //为了提供加密的字符串，存进数据库和区块链中
    private String shaCode;

    public LogisticsRecord(int recordId, int beefId, int driverId, String driverName, Date departureTime, String transitionTime, String carTemperature, String destination, String shaCode) {
        this.recordId = recordId;
        this.beefId = beefId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.departureTime = departureTime;
        this.transitionTime = transitionTime;
        this.carTemperature = carTemperature;
        this.destination = destination;
        this.shaCode = shaCode;
    }

    @Override
    public String toString() {
        return "LogisticsRecord{" +
                "recordId=" + recordId +
                ", beefId=" + beefId +
                ", driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", departureTime=" + departureTime +
                ", transitionTime='" + transitionTime + '\'' +
                ", carTemperature='" + carTemperature + '\'' +
                ", destination='" + destination + '\'' +
                ", shaCode='" + shaCode + '\'' +
                '}';
    }
}
