package com.wang.tracesource.pojo;

import lombok.Data;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @ClassName TransactionRec
 * @Description TODO
 * @Author WY
 * @Date 2020/4/12 15:29
 * Version 1.0
 **/
@Data//所有的get set方法

public class TransactionRec {

    //牛的id
    private int Id;
    //交易类型
    private String type;
    //交易接收者
    private TransactionReceipt transactionReceipt;



    public TransactionRec( int cattleId, String type, TransactionReceipt transactionReceipt) {
        this.Id = cattleId;
        this.type = type;
        this.transactionReceipt = transactionReceipt;
    }
}
