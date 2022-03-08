package com.wang.tracesource.service;


import com.wang.tracesource.dao.AllContractsDao;
import com.wang.tracesource.dao.TransationRecDao;
import com.wang.tracesource.pojo.*;
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
 * @ClassName contractService2
 * @Description TODO
 * @Author WY
 * @Date 2020/4/2 17:48
 * Version 1.0
 **/
@Service
public class FarmContractService {

    private FarmStaff farmStaff;

    //存储部署的合约信息
    private AllConstracts allConstracts;


    //账户私钥
    private static final String PRIVATEKEY="0x863bb720f275faecc746e23ae0e3156614dcb12fb4066879e8a5f8dd6999fa9a";
    private static final  int CONTRACTNUM=1001;



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

        Farm_sol_farm farm=null;
        //创建ganache的钱包
        credentials1=Credentials.create(PRIVATEKEY);

        System.out.println("养殖场发起合约的账户地址是："+credentials1.getAddress());
        System.out.println("钱包的私钥是："+credentials1.getEcKeyPair().getPrivateKey());
        System.out.println("钱包的公钥是："+credentials1.getEcKeyPair().getPublicKey());
        System.out.println(farm);


        //部署智能合约2083218
        try {
            farm=Farm_sol_farm.deploy(web3j,credentials1,new BigInteger("20832018"), new BigInteger("3000000")).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("养殖场部署合约的地址是："+farm.getContractAddress());


        //将部署得合约添加到类中
        allConstracts=new AllConstracts(CONTRACTNUM,farm.getContractAddress(),credentials1.getAddress());
        System.out.println(allConstracts.getCondtractsNums()+"\n合约地址："+allConstracts.getContractAddress());
        allContractsDao.save(allConstracts);
      //  System.out.println("allContractsDao:"+allContractsDao.getContractAddressById());
        return farm.getContractAddress();
    }


    //加载智能合约--->返回部署后的智能合约
    public Farm_sol_farm loadContract()
    {

       // System.out.println(allContractsDao.getContractAddressById(allConstracts.getCondtractsNums()));
        //加载刚部署的智能合约
        Farm_sol_farm f=Farm_sol_farm.load(allConstracts.getContractAddress(),web3j,credentials1,new BigInteger("16953680"), new BigInteger("3000000"));


        System.out.println("加载完成!");
        try {
            System.out.println("f1.isValid():"+f.isValid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }


    //调用合约中添加牛只信息的方法---》返回对牛只信息加密的哈希码
    public String addCattle(Cattle cattle)
    {


        //日期类型转字符串借助SimpleDateFormat   new Date当前时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        //将牛只信息转换为以太坊的数据类型
        BigInteger cattleId=BigInteger.valueOf(cattle.getCattleId());
        BigInteger operatorId=BigInteger.valueOf(cattle.getOperatorId());
        String cattleType=cattle.getCattleType();
        String cattleGender=cattle.getCattleGender();
        String  cattledate=sdf.format(cattle.getCattledate());
        String cattleShaString=cattle.getShaCode();//此处的哈希值已经在controller里边处理过了

        //交易的接收者：交易地址
        TransactionReceipt transactionReceipt=new TransactionReceipt();
        Farm_sol_farm f1=loadContract();
        System.out.println("f1:"+f1);
        //调用合约的addCattle方法来添加牛只信息
        try {
            transactionReceipt= f1.addCattle(cattleId,operatorId,cattleType,cattleGender,cattledate,cattleShaString).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //以这个哈希值作为交易的唯一标志
        transactionReceipt.setTransactionIndex(cattleShaString);

        System.out.println("添加牛只信息的交易："+transactionReceipt.toString());
        //设置添加牛只信息的交易地址
      //  cattle.setTransationAddress(transactionReceipt.getContractAddress());
        //TransactionReceipt{
        // transactionHash='0x320fefbcfc1f6e00511cd9edf55dc8c21d5296762c6e2d4c43654a224aa29b0c',
        // transactionIndex='0x0',
        // blockHash='0x02bcd3624d5d45ca49388dac063975d871072cba590c028ea659c3e4eb64da12',
        // blockNumber='0x3',
        // cumulativeGasUsed='0x249eb',
        // gasUsed='0x249eb',
        // contractAddress='null',
        // root='null', status='0x1',
        // from='0xcc4b371f1da382aa8532704083e34f6406155a49',
        // to='0xc93e74499d3c5f379e559bcd19e41114eb440970',
        // logs=[],
        // logsBloom='0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000'}

//        //得到交易的区块号
//       BigInteger blockNumber=transactionReceipt.getBlockNumber();
//        //通过区块号和区块哈希得到交易地址
//       Request<?, EthTransaction> transaction= web3j.ethGetTransactionByBlockHashAndIndex(transactionReceipt.getBlockHash(),blockNumber);
//
//        System.out.println("通过区块号和区块哈希得到交易地址是："+transaction.g+"");

        System.out.println("添加牛只信息的交易的transationIndex："+transactionReceipt.getTransactionIndex().toString(16));


        TransactionRec transactionRec=new TransactionRec(cattle.getCattleId(),"addSlaughterRecord",transactionReceipt);
        transationRecDao.save(transactionRec);

        System.out.println("添加信息的交易："+transationRecDao.getTransationByIdType(cattle.getCattleId()).getTransactionIndex().toString(16));

//        List<EthBlock.TransactionResult> txs = null;
//        try {
//            txs = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        txs.forEach(tx -> {
//            EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();
//
//            System.out.println("1111111111111:"+transaction.getTransactionIndex());
//        });


//
//        EthGetTransactionReceipt ethGetTransactionReceipt=null;
//        try {
//            //通过交易hash获得交易
//            ethGetTransactionReceipt=web3j.ethGetTransactionReceipt(transactionReceipt.getTransactionHash()).send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("通过交易hash获得交易:"+ethGetTransactionReceipt.toString());
//        System.out.println(ethGetTransactionReceipt.getResult());
    //   TransactionReceipt receipt=ethGetTransactionReceipt.getTransactionReceipt();
        return "0x"+transactionReceipt.getTransactionIndex().toString(16);
    }


    //jiang'ti监听事件
    //通过订单编号得到TransactionReceipt，然后调用事件函数得到哈希值
    public String getHash(int recordid)
    {
        //加载刚刚部署的合约
        Farm_sol_farm f1=loadContract();
        //通过记录单得到交易，在交易类中有一个map，是记录id和交易的映射
        TransactionReceipt transactionReceipt=transationRecDao.getTransationByIdType(recordid);
        System.out.println("TransactionReceipt:"+transactionReceipt);
        //通过交易得到该交易的事件，再通过事件获取该交易的哈希值
        List<Farm_sol_farm.HashEventResponse> eventValues= f1.getHashEvents(transactionReceipt);

        System.out.println(eventValues.size()+"事件："+eventValues.get(0).recordHash );

        return eventValues.get(0).recordHash;
    }





    //调用合约中的添加养殖记录信息的方法===》返回添加养殖记录交易信息加密的哈希码
    public  TransactionRec addFarmRecord(FarmRecord farmRecord)
    {
        //日期类型转字符串借助SimpleDateFormat   new Date当前时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        BigInteger farmId=BigInteger.valueOf(farmRecord.getFarmId());
        BigInteger cattleId=BigInteger.valueOf(farmRecord.getCattleId());
        BigInteger plantId=BigInteger.valueOf(farmRecord.getPlantId());
        BigInteger enployeeId=BigInteger.valueOf(farmRecord.getEnployeeId());
        String feedMed=farmRecord.getFeedMed();
        String operaterDate=sdf.format(farmRecord.getOperaterDate());
        String foodType=farmRecord.getFoodType();
        String farmRecordShaCode=farmRecord.getShaCode();//已经是加密之后的哈希码

        TransactionReceipt transactionReceipt=new TransactionReceipt();
        Farm_sol_farm f1=loadContract();
        System.out.println("f1:"+f1);
        //调用合约的addPlantStaff方法来添加员工信息

        try {
            transactionReceipt= f1.addFeedRecord(farmId,cattleId,plantId,enployeeId,feedMed,
                    operaterDate,foodType,farmRecordShaCode).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        transactionReceipt.setTransactionIndex(farmRecordShaCode);
        System.out.println("添加养殖记录信息的交易是："+transactionReceipt);
        System.out.println("TransactionIndex()是："+"0x"+transactionReceipt.getTransactionIndex().toString(16));

        //将交易添加到自定义类型
        TransactionRec transactionRec=new TransactionRec(farmRecord.getFarmId(),"addFarmRecord",transactionReceipt);
        transationRecDao.save(transactionRec);

        System.out.println("添加信息的交易："+transationRecDao.getTransationByIdType(farmRecord.getFarmId()).getTransactionIndex().toString(16));
        //return "0x"+transactionReceipt.getTransactionIndex().toString(16);
        return transactionRec;
    }






}
