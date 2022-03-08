package com.wang.tracesource.service;

import com.wang.tracesource.dao.AllContractsDao;
import com.wang.tracesource.dao.TransationRecDao;
import com.wang.tracesource.pojo.AllConstracts;
import com.wang.tracesource.pojo.SlaughterRecord;
import com.wang.tracesource.pojo.Slaughter_sol_Slaughter;
import com.wang.tracesource.pojo.TransactionRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName SlaughterContractService
 * @Description TODO
 * @Author WY
 * @Date 2020/4/10 11:47
 * Version 1.0
 **/
@Service
public class SlaughterContractService {

    //存储部署的合约信息
    private AllConstracts allConstracts;



    //账户私钥
    private static final String PRIVATEKEY="0xfd2f4bea926c02e506e0e60ea71e06ee78e7b1c52bb92b8ebef5d59a24f51c18";
    private static final  int CONTRACTNUM=1002;

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

        Slaughter_sol_Slaughter farm=null;
        //创建ganache的钱包
        credentials1=Credentials.create(PRIVATEKEY);

        System.out.println("发起合约的账户地址是："+credentials1.getAddress());
        System.out.println("钱包的私钥是："+credentials1.getEcKeyPair().getPrivateKey());
        System.out.println("钱包的公钥是："+credentials1.getEcKeyPair().getPublicKey());
        System.out.println(farm);


        //部署智能合约2083218
        try {
            farm=Slaughter_sol_Slaughter.deploy(web3j,credentials1,new BigInteger("20832018"), new BigInteger("3000000")).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("部署合约的地址是："+farm.getContractAddress());


        //将部署得合约添加到类中
        allConstracts=new AllConstracts(CONTRACTNUM,farm.getContractAddress(),credentials1.getAddress());
        System.out.println(allConstracts.getCondtractsNums()+"\n合约地址："+allConstracts.getContractAddress());
        allContractsDao.save(allConstracts);
        //  System.out.println("allContractsDao:"+allContractsDao.getContractAddressById());
        return farm.getContractAddress();
    }


    //加载智能合约--->返回部署后的智能合约
    public Slaughter_sol_Slaughter loadContract()
    {

        // System.out.println(allContractsDao.getContractAddressById(allConstracts.getCondtractsNums()));
        //加载刚部署的智能合约
        Slaughter_sol_Slaughter f=Slaughter_sol_Slaughter.load(allConstracts.getContractAddress(),web3j,credentials1,new BigInteger("16953680"), new BigInteger("3000000"));


        System.out.println("加载完成!");
        try {
            System.out.println("f1.isValid():"+f.isValid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }

    //调用合约中添加屠宰记录的方法---》返回对屠宰记录自定义的交易类型
    public TransactionRec addSlaughterRecord(SlaughterRecord slaughterRecord)
    {


        //日期类型转字符串借助SimpleDateFormat   new Date当前时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        //将牛只信息转换为以太坊的数据类型
        BigInteger recordId=BigInteger.valueOf(slaughterRecord.getRecordId());
        BigInteger cattleId=BigInteger.valueOf(slaughterRecord.getCattleId());
        String cattleHealth=slaughterRecord.getCattleHealth();
        String quarantineDate=sdf.format(slaughterRecord.getQuarantineDate());
        BigInteger quarantinerId=BigInteger.valueOf(slaughterRecord.getQuarantinerId());
        String  slaughterDate=sdf.format(slaughterRecord.getSlaughterDate());
        BigInteger butcherId=BigInteger.valueOf(slaughterRecord.getButcherId());
        String slaughterShaString=slaughterRecord.getShaCode();//此处的哈希值已经在controller里边处理过了


        System.out.println("屠宰信息："+slaughterRecord.toString());

        TransactionReceipt transactionReceipt=new TransactionReceipt();
        Slaughter_sol_Slaughter f1=loadContract();
        System.out.println("f1:"+f1);
        //调用合约的addCattle方法来添加牛只信息
        try {
            transactionReceipt= f1.addSlaughterRecord(recordId,cattleId,cattleHealth,
                    quarantineDate,quarantinerId,slaughterDate,butcherId,slaughterShaString).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将哈希值添加到交易属性中
        transactionReceipt.setTransactionIndex(slaughterShaString);
        System.out.println("添加屠宰记录信息的交易："+transactionReceipt.toString());
        System.out.println("添加屠宰记录信息的交易的transationIndex："+transactionReceipt.getTransactionIndex().toString(16));



        //将交易添加到自定义类型
        TransactionRec transactionRec=new TransactionRec(slaughterRecord.getRecordId(),"addSlaughterRecord",transactionReceipt);
        transationRecDao.save(transactionRec);

        System.out.println("添加信息的交易："+transationRecDao.getTransationByIdType(slaughterRecord.getRecordId()).getTransactionIndex().toString(16));

        return transactionRec;
    }


    //jiang'ti监听事件
    //通过订单编号得到TransactionReceipt，然后调用事件函数得到哈希值
    public String getHash(int recordid)
    {
        //加载刚刚部署的合约
        Slaughter_sol_Slaughter f1=loadContract();
        //通过记录单得到交易
        TransactionReceipt transactionReceipt=transationRecDao.getTransationByIdType(recordid);

        System.out.println("TransactionReceipt:"+transactionReceipt);
        //调用智能合约函数得到
        List<Slaughter_sol_Slaughter.HashEventResponse> eventValues= f1.getHashEvents(transactionReceipt);

        System.out.println(eventValues.size()+"事件："+eventValues.get(0).recordHash );
        System.out.println("事件中交易的相关值eventValues.toString()："+eventValues.toString());
        System.out.println("事件中交易的相关值eventValues.get(0).toString()："+eventValues.get(0).toString());
        return eventValues.get(0).recordHash;
    }







    /**
     * 获取blockhash
     * @param txHash
     * @return
     */
    public String getBlockHash(String txHash) {
        try {

            EthTransaction transaction = web3j.ethGetTransactionByHash(txHash).sendAsync().get();
            Transaction result = transaction.getResult();
            String blockHash = result.getBlockHash();
            BigInteger transationInput=result.getTransactionIndex();
            System.out.println("getTransactionResult blockHash : " + blockHash);
            System.out.println("getTransactionResult transationInput : " + transationInput);
//            boolean isSuccess = Numeric.toBigInt(blockHash).compareTo(BigInteger.valueOf(0)) != 0;
//            if (isSuccess) {
//                getTransactionReceipt(txHash);
//                stopPolling(disposable);
//            }
            return blockHash;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
