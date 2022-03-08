package com.wang.tracesource.service;

import com.wang.tracesource.dao.AllContractsDao;
import com.wang.tracesource.dao.TransationRecDao;
import com.wang.tracesource.pojo.AllConstracts;
import com.wang.tracesource.pojo.LogisticsRecord;
import com.wang.tracesource.pojo.Logistics_sol_Logistics;
import com.wang.tracesource.pojo.TransactionRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName LogisticsContractService
 * @Description TODO
 * @Author WY
 * @Date 2020/5/3 8:49
 * Version 1.0
 **/
@Service
public class LogisticsContractService {
    //存储部署的合约信息
    private AllConstracts allConstracts;


    //账户私钥
    private static final String PRIVATEKEY="0x9a45071c8142131adf08c77b8ee29a2bc4b76cc1c8fb750423c7209b4782d8c5";
    private static final  int CONTRACTNUM=1003;

    @Autowired
    private Web3j web3j;
    @Autowired
    AllContractsDao allContractsDao;
    @Autowired
    TransationRecDao transationRecDao;

    // Credentials是来加载私钥钱包的
    private Credentials credentials1;

    //返回部署的合约地址--》通过ganache部署
//加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
     @PostConstruct
    public String createContract()
    {
        Logistics_sol_Logistics logistics=null;
        //创建ganache的钱包
        credentials1=Credentials.create(PRIVATEKEY);

        System.out.println("物流部门发起合约的账户地址是："+credentials1.getAddress());
        System.out.println("钱包的私钥是："+credentials1.getEcKeyPair().getPrivateKey());
        System.out.println("钱包的公钥是："+credentials1.getEcKeyPair().getPublicKey());
        System.out.println(logistics);

        //部署智能合约
        try {
            logistics=Logistics_sol_Logistics.deploy(web3j,credentials1,new BigInteger("20832018"), new BigInteger("3000000")).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("物流部门部署合约的地址是："+logistics.getContractAddress());
        //将部署得合约添加到类中
        allConstracts=new AllConstracts(CONTRACTNUM,logistics.getContractAddress(),credentials1.getAddress());
        System.out.println(allConstracts.getCondtractsNums()+"\n合约地址："+allConstracts.getContractAddress());
        allContractsDao.save(allConstracts);
        //  System.out.println("allContractsDao:"+allContractsDao.getContractAddressById());
        return logistics.getContractAddress();
    }

    //加载智能合约--->返回部署后的智能合约
    public Logistics_sol_Logistics loadContract() {
        //加载刚部署的智能合约
        Logistics_sol_Logistics f=Logistics_sol_Logistics.load(allConstracts.getContractAddress(),web3j,credentials1,new BigInteger("16953680"), new BigInteger("3000000"));


        System.out.println("加载完成!");
        try {
            System.out.println("f1.isValid():"+f.isValid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }

    //调用合约中添加物流记录的方法---》返回对物流信息自定义的交易类型
    public TransactionRec addLogisticsRecord(LogisticsRecord logisticsRecord) {
        //日期类型转字符串借助SimpleDateFormat   new Date当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //将物流信息转换为以太坊的数据类型
        BigInteger recordId=BigInteger.valueOf(logisticsRecord.getRecordId());
        BigInteger beefId=BigInteger.valueOf(logisticsRecord.getBeefId());
        BigInteger driverId=BigInteger.valueOf(logisticsRecord.getDriverId());
        String driverName=logisticsRecord.getDriverName();
        String  departureTime=sdf.format(logisticsRecord.getDepartureTime());
        String transitionTime=logisticsRecord.getTransitionTime();
        String carTemperature=logisticsRecord.getCarTemperature();
        String destination=logisticsRecord.getDestination();
       String logisticsShaString=logisticsRecord.getShaCode();//此处的哈希值已经在controller里边处理过了
//

        System.out.println("物流信息："+logisticsRecord.toString());
       //定义的交易类型
        TransactionReceipt transactionReceipt=new TransactionReceipt();
        //加载刚刚部署的合约
        Logistics_sol_Logistics f1=loadContract();
        System.out.println("f1:"+f1);
//        //调用合约的addLogisticsRecord方法来添加物流信息
        try {
            transactionReceipt= f1.addLogisticsRecord(recordId,beefId,driverId
                    ,driverName,departureTime,transitionTime,carTemperature,destination,logisticsShaString).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        //将哈希值添加到交易属性中
        transactionReceipt.setTransactionIndex(logisticsShaString);
        System.out.println("添加物流信息的交易："+transactionReceipt.toString());
//
//        //将交易添加到自定义类型
        TransactionRec transactionRec=new TransactionRec(logisticsRecord.getRecordId(),"addLogisticsRecord",transactionReceipt);
        transationRecDao.save(transactionRec);

        System.out.println("添加信息的交易："+transationRecDao.getTransationByIdType(logisticsRecord.getRecordId()).getTransactionIndex().toString(16));
        //return "0x"+transactionReceipt.getTransactionIndex().toString(16);
        return transactionRec;
//
    }


    //jiang'ti监听事件
    //通过订单编号得到TransactionReceipt，然后调用事件函数得到哈希值
    public String getHash(int recordid)
    {
        //加载刚刚部署的合约
        Logistics_sol_Logistics f1=loadContract();
        //通过记录单得到交易
        TransactionReceipt transactionReceipt=transationRecDao.getTransationByIdType(recordid);

        System.out.println("TransactionReceipt:"+transactionReceipt);
        //调用智能合约函数得到监听交易的事件
        List<Logistics_sol_Logistics.HashEventResponse> eventValues= f1.getHashEvents(transactionReceipt);

        System.out.println(eventValues.size()+"事件："+eventValues.get(0).recordHash );
        return eventValues.get(0).recordHash;
    }
}