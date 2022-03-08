package com.wang.tracesource.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FarmStaff
 * @Description TODO
 * @Author WY
 * @Date 2020/4/6 15:15
 * Version 1.0
 **/
@Data
@NoArgsConstructor
public class FarmStaff {
    private int workerId;
    private int workerPlantId;
    private String workerName;
    private String workerPosition;
    private String workerHealth;//健康状况


    public FarmStaff(int workerId, int workerPlantId, String workerName,  String workerPosition, String workerHealth) {
        this.workerId = workerId;
        this.workerPlantId = workerPlantId;
        this.workerPosition=workerPosition;
        this.workerName = workerName;
        this.workerHealth = workerHealth;

    }

}
