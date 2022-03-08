package com.wang.tracesource.controller;

import com.wang.tracesource.pojo.LogisticsRecord;
import com.wang.tracesource.service.LogisticsContractService;
import com.wang.tracesource.service.LogisticsService.LogisticsServiceIml;
import com.wang.tracesource.service.farmService.CattleServiceImp;
import com.wang.tracesource.service.farmService.FarmStaffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Hash;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName LogisticsController
 * @Description TODO
 * @Author WY
 * @Date 2020/4/26 11:53
 * Version 1.0
 **/
@Controller
public class LogisticsController {

    @Autowired
    LogisticsServiceIml logisticsServiceIml;
    @Autowired
    CattleServiceImp cattleServiceImp;
    @Autowired
    FarmStaffServiceImp farmStaffServiceImp;
    @Autowired
    LogisticsContractService logisticsContractService;

    //跳转到物流信息登记页面/login/logisticsEnroll/workerImform
    @RequestMapping("/login/logisticsEnroll/logistImform")
    public String tologistics()
    {
        return "quotienthtml/logistics/logisticsRecordImform";
    }

    //处理w物流信息提交请求
    //处理添加物流记录表单的post请求
    @PostMapping("/login/logisticsEnroll/addLogisticsRecord")
    public String addLogisticsRecord(Integer recordId, Integer beefId, Integer driverId,
                                     String driverName,Date departureTime, String transitionTime,
                                     String carTemperature,String destination, Model model)
    {
        if(recordId==null)
        {
            model.addAttribute("msges4","该记录单编号不能为空");
            return "quotienthtml/logistics/logisticsRecordImform";
        }
        if(beefId==null)
        {
            model.addAttribute("msges1","肉制编号不能为空");
            return "quotienthtml/logistics/logisticsRecordImform";
        }
        if(driverId==null || driverName==null|| departureTime==null||transitionTime==null
                || carTemperature==null ||destination==null)
        {
            model.addAttribute("msges3","输入不为空");
            return "quotienthtml/logistics/logisticsRecordImform";
        }
        if(farmStaffServiceImp.findFarmStaffById(driverId)==null)
        {
            model.addAttribute("msges5","该员工不存在");
            return "quotienthtml/logistics/logisticsRecordImform";
        }

        System.out.println(recordId+beefId+driverId+driverName+departureTime+transitionTime+carTemperature+destination);
        //先从数据库中查询是否有输入的牛只和养殖场，没有就向前端页面打印消息
        if(logisticsServiceIml.findLogisticsRecordById(recordId)!=null)
        {
            model.addAttribute("msges4","该记录单编号已存在");
            return "quotienthtml/logistics/logisticsRecordImform";
        }
        if(cattleServiceImp.findCattleById(beefId)==null)
        {
            model.addAttribute("msges1","牛只编号错误");
            return "quotienthtml/logistics/logisticsRecordImform";
        }
//


        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //加密物流信息得到哈希值
        String farmRecordsha=String.valueOf(recordId)+String.valueOf(beefId)+String.valueOf(driverId)
                +driverName+sdf.format(departureTime)+transitionTime+carTemperature+destination;
        //对要添加物流信息经过sha算法得到的hash值
        String shaCode=Hash.sha3(farmRecordsha);
        System.out.println("加密前的信息："+farmRecordsha+"\n加密后的hash:"+shaCode);

        //生成一个FramRecord对象，
        LogisticsRecord logisticsRecord=new LogisticsRecord( recordId, beefId,driverId,
                            driverName, departureTime,transitionTime,
                                     carTemperature,destination,shaCode);
        System.out.println("物流记录："+logisticsRecord.toString());

        //添加到数据库中
        logisticsServiceIml.saveLogisticsRecord(logisticsRecord);


        //添加到区块链中
        logisticsContractService.addLogisticsRecord(logisticsRecord);

        //重定向到显示养殖记录页面
       return "redirect:/login/logisticsSearch/logistFind";
   }

    //物流记录查询--->在QuetientController里边


    ///跳转到物流部门员工信息登记在QuetientController里边

}
