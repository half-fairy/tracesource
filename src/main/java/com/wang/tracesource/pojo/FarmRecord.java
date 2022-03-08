package com.wang.tracesource.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName FarmRecord
 * @Description TODO
 * @Author WY
 * @Date 2020/4/9 10:41
 * Version 1.0
 **/
@Data
@NoArgsConstructor
public class FarmRecord {
    private  int farmId;//记录单编号
    private  int cattleId;//牛只编号
    private  int plantId;//养殖场编号
    private  int enployeeId;//操作员编号
    private String feedMed;//养殖用药
    private Date operaterDate;//用药日期
    private String foodType;//投放饲料种类
    private String isIn;//是否在栏
    //为了提供加密的字符串，存进数据库和区块链中
    private String shaCode;

    public FarmRecord(int farmId, int cattleId, int plantId, int enployeeId, String feedMed,
                      Date operaterDate, String foodType, String shaCode) {
        this.farmId = farmId;
        this.cattleId = cattleId;
        this.plantId = plantId;
        this.enployeeId = enployeeId;
        this.feedMed = feedMed;
        this.operaterDate = operaterDate;
        this.foodType = foodType;
        this.shaCode = shaCode;
    }
    public FarmRecord(int farmId, int cattleId, int plantId, int enployeeId, String feedMed,
                      Date operaterDate, String foodType, String isIn,String shaCode) {
        this.farmId = farmId;
        this.cattleId = cattleId;
        this.plantId = plantId;
        this.enployeeId = enployeeId;
        this.feedMed = feedMed;
        this.operaterDate = operaterDate;
        this.foodType = foodType;
        this.isIn=isIn;
        this.shaCode = shaCode;
    }
}
