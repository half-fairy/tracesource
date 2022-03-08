package com.wang.tracesource.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName SlaughterRecord
 * @Description TODO
 * @Author WY
 * @Date 2020/4/10 11:47
 * Version 1.0
 **/
@Data
@NoArgsConstructor
public class SlaughterRecord {
    private int recordId;//记录单编号
    private int cattleId;//牛只编号
     private String cattleHealth;//牛只健康状况
     private Date quarantineDate;//检疫日期
     private int quarantinerId;//检疫员id
     private Date slaughterDate;//屠宰日期
    private int butcherId;//屠宰员编号
    //为了提供加密的字符串，存进数据库和区块链中
    private String shaCode;

    public SlaughterRecord(int recordId, int cattleId, String cattleHealth, Date quarantineDate, int quarantinerId, Date slaughterDate, int butcherId, String shaCode) {
        this.recordId = recordId;
        this.cattleId = cattleId;
        this.cattleHealth = cattleHealth;
        this.quarantineDate = quarantineDate;
        this.quarantinerId = quarantinerId;
        this.slaughterDate = slaughterDate;
        this.butcherId = butcherId;
        this.shaCode = shaCode;
    }

    @Override
    public String toString() {
        return "SlaughterRecord{" +
                "recordId=" + recordId +
                ", cattleId=" + cattleId +
                ", cattleHealth='" + cattleHealth + '\'' +
                ", quarantineDate='" + quarantineDate + '\'' +
                ", quarantinerId='" + quarantinerId + '\'' +
                ", slaughterDate='" + slaughterDate + '\'' +
                ", butcherId=" + butcherId +
                ", shaCode='" + shaCode + '\'' +
                '}';
    }
}
