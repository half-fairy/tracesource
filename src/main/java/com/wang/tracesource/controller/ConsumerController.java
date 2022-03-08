package com.wang.tracesource.controller;

import com.wang.tracesource.dao.TransationRecDao;
import com.wang.tracesource.pojo.Cattle;
import com.wang.tracesource.pojo.FarmRecord;
import com.wang.tracesource.pojo.LogisticsRecord;
import com.wang.tracesource.pojo.SlaughterRecord;
import com.wang.tracesource.service.FarmContractService;
import com.wang.tracesource.service.LogisticsContractService;
import com.wang.tracesource.service.LogisticsService.LogisticsServiceIml;
import com.wang.tracesource.service.SlaughterContractService;
import com.wang.tracesource.service.farmService.CattleServiceImp;
import com.wang.tracesource.service.farmService.FarmRecordServiceImp;
import com.wang.tracesource.service.slaughterService.SlaughterRecordServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @ClassName ConsumerController
 * @Description TODO
 * @Author WY
 * @Date 2020/3/18 10:35
 * Version 1.0
 **/
//处理的是servce层
@Controller
public class ConsumerController {


    @Autowired
    CattleServiceImp cattleServiceImp;
    @Autowired
    SlaughterRecordServiceImp slaughterRecordServiceImp;

    @Autowired
    FarmRecordServiceImp farmRecordServiceImp;
    @Autowired
    TransationRecDao transationRecDao;
    @Autowired
    FarmContractService farmContractService;
    @Autowired
    SlaughterContractService slaughterContractService;
    @Autowired
    LogisticsServiceIml logisticsServiceIml;
    @Autowired
    LogisticsContractService logisticsContractService;

    //处理首页消费者请求---》点击消费者跳转到consult页面
    @RequestMapping("/consumers")
    public String tocomsumer() {
        System.out.println("hello");
        return "consumerhtml/consult";
    }

    // 养殖场-----------------------------------------------------------------------------

    //跳转到牛只信息查询页面
    @GetMapping(value = {"/find1"})
    public String farmFind1() {
        return "consumerhtml/informFind";
    }

    //跳转到牛只信息验证页面
    @GetMapping(value = {"/find2"})
    public String farmFind2() {
        return "consumerhtml/informFind2";
    }

    //点击侧边栏的养殖信息验证跳转到养殖信息验证界面
    @GetMapping(value = {"/find2-1"})
    public String farmFind2_1() {
        return "consumerhtml/cattleRecordCheck";
    }


    //处理养殖记录信息验证
    @RequestMapping("/consumer/cattleRecordCheck")
    public String cattleRecordCheck1(Integer cattleId,Model model)
    {
        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/cattleRecordCheck";
        }
        model.addAttribute("cid", cattleId);
        //通过id查到牛只养殖记录信息
        Collection<FarmRecord>  farmRecord = farmRecordServiceImp.findFarmRecordByCattleId(cattleId);
        model.addAttribute("farmRecords", farmRecord);
        System.out.println(farmRecord + "\n" + farmRecord);

        return "consumerhtml/cattleRecordCheck";
    }
    //处理每个一养殖记录的信息验证
    @RequestMapping("/farmrecord/{farmId}")
    public String deleteEmp(@PathVariable("farmId") Integer farmId, Model model) {


            //从数据库中查找哈希值
            String hash = farmRecordServiceImp.findFarmRecordShacodeById(farmId);

            //从区块链中查找哈希值
        String newHash = null;
        try {
            newHash = farmContractService.getHash(farmId);
        } catch (NullPointerException e) {
            newHash=" ";
        }


        model.addAttribute("hash", hash);
        System.out.println("hash：" + hash);
       model.addAttribute("newHash", newHash);
        System.out.println("newhash：" + newHash);
        return "consumerhtml/allRecordCheck";
    }
    //养殖场信息查询
    @PostMapping("/consumer/cattleFind")
    public String cattleFind(Integer cattleId, Model model) {
        if(cattleId==null )
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/informFind";
        }
        //通过id查到牛只信息
        Cattle cattle = cattleServiceImp.findCattleById(cattleId);

        if(cattle==null)
        {
            model.addAttribute("msg1", "牛只编号错误");
            return "consumerhtml/informFind2";
        }
        model.addAttribute("cid", cattleId);
        model.addAttribute("cattle", cattle);

        //通过id查到牛只养殖记录信息
        Collection<FarmRecord>  farmRecord = farmRecordServiceImp.findFarmRecordByCattleId(cattleId);
        model.addAttribute("farmRecords", farmRecord);
        System.out.println(cattle + "\n" + farmRecord);


        return "consumerhtml/cattleimformshow";

    }


    //养殖场信息验证--牛只信息验证/consumer/cattleCheck
    @RequestMapping("/consumer/cattleCheck")
    public String cattleCheck(Integer cattleId, Model model) {


        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/informFind2";
        }
        model.addAttribute("cid", cattleId);

        //信息验证
        //从数据库中查找哈希值
        String hash = cattleServiceImp.findCattleHahByid(cattleId);

        //从区块链中查找哈希值
        String newHash = null;
        try {
            newHash = farmContractService.getHash(cattleId);
        } catch (NullPointerException e) {
            newHash=" ";
        }

        model.addAttribute("hash", hash);
        System.out.println("hash" + hash);
        model.addAttribute("newHash", newHash);
        System.out.println("newhash" + newHash);


     //   return "redirect:/consumer-informFind2";/consumer/cattleHasCheck
        return "consumerhtml/informFind2";

    }

    //养殖场信息验证--牛只信息验证/consumer/cattleCheck
    @RequestMapping("/consumer/cattleHasCheck")
    public String cattleCheck() {
        return "redirect:/consumer-informFind2";
    }





    // 屠宰场-----------------------------------------------------------------------------

    //点击屠宰场下的信息查询跳转到输入牛只编号页面
    @RequestMapping("/find3")
    public String toSlaughterFind2() {

        return "consumerhtml/Slaughter/InputCattleId";
    }

    //根据提交的牛只id查询屠宰记录
    @RequestMapping("/consumer/slaughterFind")
    public String slaughterRecordFind(Integer cattleId, Model model) {

        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/Slaughter/InputCattleId";
        }

        //通过牛只id查到的该牛只的所有屠宰信息
      Collection<SlaughterRecord> slaughterRecords = slaughterRecordServiceImp.findSlaughterRecordByCattleId(cattleId);

        model.addAttribute("cid", cattleId);
        model.addAttribute("slaughterRecords", slaughterRecords);

        System.out.println("牛只编号："+cattleId + "\n"+"牛只屠宰记录数量：" + slaughterRecords.size());

        return "consumerhtml/Slaughter/slaughterRecordshow";
    }
    //点击屠宰场下的屠宰记录验证跳转到屠宰记录验证页面/consumer/slaughterRecordCheck
    @RequestMapping("/find4")
    public String toSlaughterCheck() {

        return "consumerhtml/Slaughter/slaughterRecordCheck";
    }

    //处理屠宰记录信息验证
    @RequestMapping("/consumer/slaughterRecordCheck")
    public String slaughterRecordCheck1(Integer cattleId,Model model)
    {
        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/Slaughter/slaughterRecordCheck";
        }
        model.addAttribute("cid", cattleId);
        //通过id查到牛只屠宰记录信息
        Collection<SlaughterRecord>  slaughterRecords = slaughterRecordServiceImp.findSlaughterRecordByCattleId(cattleId);
        model.addAttribute("slaughterRecords", slaughterRecords);
        System.out.println("牛只编号："+cattleId + "\n"+"牛只屠宰记录数量：" + slaughterRecords.size());

        return "consumerhtml/Slaughter/slaughterRecordCheck";
    }
    //处理每个一屠宰记录的信息验证
    @RequestMapping("/slaughterRecord/{recordId}")
    public String checkOneSlaughterRecord(@PathVariable("recordId") Integer recordId, Model model) {


        //从数据库中查找哈希值
        String hash = slaughterRecordServiceImp.findSlaughterRecordShacodeById(recordId);

        //从区块链中查找哈希值
        String newHash = null;
        try {
            newHash = slaughterContractService.getHash(recordId);
        } catch (NullPointerException e) {
            newHash=" ";
        }


        model.addAttribute("hash", hash);
        System.out.println("hash" + hash);
          model.addAttribute("newHash", newHash);
           System.out.println("newhash" + newHash);
        return "consumerhtml/allRecordCheck";
    }




    // 物流部门-----------------------------------------------------------------------------
    //点击物流部门下的信息查询跳转到输入牛只编号页面
    @RequestMapping("/find5")
    public String toLogisticsFind2() {

        return "consumerhtml/Logistics/InputCattleId1";
    }
    //根据提交的牛只id查询物流记录
    @RequestMapping("/consumer/LogisticsFind")
    public String logisticsRecordFind(Integer cattleId, Model model) {
        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/Logistics/InputCattleId1";
        }
        //通过牛只id查到的该牛只的所有物流信息
        Collection<LogisticsRecord> logisticsRecords = logisticsServiceIml.findAllLogisticsRecordByCattleId(cattleId);

        model.addAttribute("cid", cattleId);
        model.addAttribute("logisticsRecords", logisticsRecords);

        System.out.println("牛只编号："+cattleId + "\n"+"牛只物流记录数量：" + logisticsRecords.size());

        return "consumerhtml/Logistics/logisticsRecordshow";
    }

    //点击物流部门下的物流记录验证跳转到物流记录验证页面/consumer/slaughterRecordCheck
    @RequestMapping("/find6")
    public String tologisticsCheck() {

        return "consumerhtml/Logistics/logisticsRecordCheck";
    }

    //处理物流部门记录信息验证
    @RequestMapping("/consumer/logisticsRecordCheck")
    public String logisticsRecordCheck1(Integer cattleId,Model model)
    {
        if(cattleId==null)
        {
            model.addAttribute("msg1", "牛只编号不能为空");
            return "consumerhtml/Logistics/logisticsRecordCheck";
        }
        model.addAttribute("cid", cattleId);
        //通过id查到牛只物流记录信息
        Collection<LogisticsRecord>  logisticsRecords = logisticsServiceIml.findAllLogisticsRecordByCattleId(cattleId);
        model.addAttribute("logisticsRecords", logisticsRecords);
        System.out.println("牛只编号："+cattleId + "\n"+"牛只物流记录数量：" + logisticsRecords.size());

        return "consumerhtml/Logistics/logisticsRecordCheck";
    }

    //处理每个一物流记录的信息验证
    @RequestMapping("/logisticsRecord/{recordId}")
    public String checkOnelogisticsRecord(@PathVariable("recordId") Integer recordId, Model model) {


        //从数据库中查找哈希值
        String hash = logisticsServiceIml.findLogisticsRecordShacodeById(recordId);

        //从区块链中查找哈希值
        String newHash = null;
        try {
            newHash = logisticsContractService.getHash(recordId);
        } catch (NullPointerException e) {
            newHash=" ";
        }


        model.addAttribute("hash", hash);
        System.out.println("hash" + hash);
          model.addAttribute("newHash", newHash);
          System.out.println("newhash" + newHash);
        return "consumerhtml/allRecordCheck";
    }


}



