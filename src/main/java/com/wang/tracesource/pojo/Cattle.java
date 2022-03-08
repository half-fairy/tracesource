package com.wang.tracesource.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Cattle
 * @Description TODO
 * @Author WY
 * @Date 2020/4/5 12:47
 * Version 1.0
 **/
@Data
@NoArgsConstructor
public class Cattle {
   private Integer  cattleId;
    private Integer  operatorId;
    private String  cattleType;
    private String cattleGender;
    private Date cattledate;
    private Date outDate;
    //为了提供加密的字符串，存进数据库和区块链中
    private String shaCode;


    public Cattle(int cattleId, int operatorId, String cattleType, String cattleGender,Date cattledate,
                  Date outDate,String shaCode) {
        this.cattleId = cattleId;
        this.operatorId = operatorId;
        this.cattleType = cattleType;
        this.cattleGender = cattleGender;
        this.cattledate = cattledate;
        this.outDate=outDate;
        this.shaCode=shaCode;
    }
    public Cattle(int cattleId, int operatorId, String cattleType, String cattleGender,Date cattledate,
                 String shaCode) {
        this.cattleId = cattleId;
        this.operatorId = operatorId;
        this.cattleType = cattleType;
        this.cattleGender = cattleGender;
        this.cattledate = cattledate;
        this.shaCode=shaCode;
    }
    @Override
    public String toString() {
        return "Cattle{" +
                "cattleId=" + cattleId +
                ", operatorId=" + operatorId +
                ", cattleType='" + cattleType + '\'' +
                ", cattleGender='" + cattleGender + '\'' +
                ", cattledate=" + cattledate +
                ", outDate=" + outDate +
                ", shaCode='" + shaCode + '\'' +
                '}';
    }
}
