package com.wang.tracesource.controller;

import com.wang.tracesource.pojo.SlaughterRecord;
import com.wang.tracesource.service.DepartmentServiceImpl;
import com.wang.tracesource.service.SlaughterContractService;
import com.wang.tracesource.service.farmService.CattleServiceImp;
import com.wang.tracesource.service.farmService.FarmRecordServiceImp;
import com.wang.tracesource.service.farmService.FarmStaffServiceImp;
import com.wang.tracesource.service.slaughterService.SlaughterRecordServiceImp;
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
 * @ClassName SlaughterController
 * @Description TODO
 * @Author WY
 * @Date 2020/4/10 14:27
 * Version 1.0
 **/
@Controller
public class SlaughterController {

    @Autowired
    SlaughterContractService slaughterContractService;

    @Autowired
    SlaughterRecordServiceImp slaughterRecordServiceImp;
    @Autowired
    FarmRecordServiceImp farmRecordServiceImp;
    @Autowired
    CattleServiceImp cattleServiceImp;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    FarmStaffServiceImp farmStaffServiceImp;



    //跳转到屠宰首页
    @RequestMapping("/login/slaughterEnroll/slaughImform")
    public String toslaughter()
    {
        return "quotienthtml/slaughter/slaughterRecordImform";
    }


    //添加屠宰记录
    @PostMapping("/login/slaughterEnroll/addSlaughterRecord")
    public String addslaughterRecord(Integer recordId, Integer cattleId,
                                     String cattleHealth, Date quarantineDate, Integer quarantinerId,
                                     Date slaughterDate, Integer butcherId, Model model)
    {
        if(recordId==null)
        {
            model.addAttribute("msges4","输入不为空");
            return "quotienthtml/slaughter/slaughterRecordImform";
        }
        if(cattleHealth==null|| slaughterDate==null||quarantineDate==null || butcherId==null)
        {
            model.addAttribute("msges3","输入不为空");
            return "quotienthtml/slaughter/slaughterRecordImform";
        }


        //先从数据库中查询是否有输入的牛只和养殖场，没有就向前端页面打印消息
        if(slaughterRecordServiceImp.findSlaughterRecordById(recordId)!=null)
        {
            System.out.println("qqqqqqqqq");
            model.addAttribute("msges4","该记录单编号已存在");
            return "quotienthtml/slaughter/slaughterRecordImform";
        }
        if(cattleId==null || cattleServiceImp.findCattleById(cattleId)==null)
        {
            model.addAttribute("msges1","牛只编号错误");
            return "quotienthtml/slaughter/slaughterRecordImform";
        }



        System.out.println("从前端页面获取的信息："+recordId+cattleId+
                cattleHealth+quarantineDate+  quarantinerId+ slaughterDate+ butcherId);
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //加密牛只信息得到哈希值
        String slaughterRecordsha=String.valueOf(recordId)+String.valueOf(cattleId)+String.valueOf(butcherId)
                +String.valueOf(quarantinerId)+cattleHealth+sdf.format(slaughterDate)+sdf.format(quarantineDate);
        //对要添加牛只信息经过sha算法得到的hash值
        String shaCode=Hash.sha3(slaughterRecordsha);
        System.out.println("加密前的信息："+slaughterRecordsha+"\n加密后的hash:"+shaCode);

        //生成一个slaughterRecord对象，
        SlaughterRecord slaughterRecord=new SlaughterRecord(recordId,cattleId,
       cattleHealth,quarantineDate,  quarantinerId, slaughterDate, butcherId,  shaCode);


        //添加到数据库中
        slaughterRecordServiceImp.saveSlaughterRecord(slaughterRecord);

        //添加到区块链中
        slaughterContractService.addSlaughterRecord(slaughterRecord);

          return "redirect:/login/slaughterSearch/feedRecordFind";
    }









}
