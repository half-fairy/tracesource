package com.wang.tracesource.controller;

import com.wang.tracesource.pojo.*;
import com.wang.tracesource.service.DepartmentServiceImpl;
import com.wang.tracesource.service.FarmContractService;
import com.wang.tracesource.service.LogisticsService.LogisticsServiceIml;
import com.wang.tracesource.service.SlaughterContractService;
import com.wang.tracesource.service.farmService.CattleServiceImp;
import com.wang.tracesource.service.farmService.FarmRecordServiceImp;
import com.wang.tracesource.service.farmService.FarmStaffServiceImp;
import com.wang.tracesource.service.slaughterService.SlaughterRecordServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Hash;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @ClassName QuetientController
 * @Description TODO
 * @Author WY
 * @Date 2020/3/21 13:42
 * Version 1.0
 **/
//处理的是servce层
@Controller
public class QuetientController {

    @Autowired
    FarmContractService farmContractService;
    @Autowired
    SlaughterContractService slaughterContractService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    CattleServiceImp cattleServiceImp;
    @Autowired
    FarmStaffServiceImp farmStaffServiceImp;
    @Autowired
    FarmRecordServiceImp farmRecordServiceImp;
    @Autowired
    SlaughterRecordServiceImp slaughterRecordServiceImp;
    @Autowired
    LogisticsServiceIml logisticsServiceIml;


    //通过PlantId拿到登陆的工厂号
    public int PlantId;
    public String plantName;

    //点击加工商按钮跳转到到登陆页面
    @GetMapping("/quotients/tologin")
    public String tologin() {
        return "quotienthtml/login";
    }


    //点击登录跳转到加工商页面：此处应该是重定向，否则密码和用户名会明文
    @RequestMapping("/login")
    public String login(String username, String password,String pole,
                        Model model,HttpSession httpSession) {
        try {
        //通过username 拿到工厂的id
        //PlantId=departmentService.queryDepartmentByName(username).getDepartId();


        //通过id查询到工厂，并拿到工厂的名字
       // plantName="你好，"+departmentService.queryDepartmentById(PlantId).getDepartName();
       // model.addAttribute("plantName",plantName);

       // System.out.println("登录的用户名是："+username+" 工厂id:"+PlantId);

        //通过 SecurityUtils.getSubject()获得当前的用户
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();

        //封装用户登录的数据,相当于将用户名和密码封装成一个令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        httpSession.setAttribute("loginPole",pole);

            subject.login(token);//执行登录方法，

            return "redirect:/login.html";

          //  return "quotienthtml/farm/quotientIndex";


        } catch (UnknownAccountException uae) {//用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "quotienthtml/login";
        } catch (IncorrectCredentialsException ice) {//密码不对
            model.addAttribute("msg", "密码错误");
            return "quotienthtml/login";

        }

    }

    //点击登陆页面的Create an account跳转到注册页面
    @GetMapping("/toregister")
    public String toregister()
    {
        return"quotienthtml/register";
    }

    //处理注册
    @PostMapping("/register")
    public String register(String departPerson,String departPwd,
                           String departName,Model model)
    {

        if(departPerson=="")
        {
            model.addAttribute("msg1","用户名不能为空");
            return "quotienthtml/register";
        }
        if(departPwd=="")
        {
            System.out.println("密码不存在");
            model.addAttribute("msg2","密码不能为空");
            return "quotienthtml/register";
        }
        //如果该用户名已存在
        if(departmentService.queryDepartmentByName(departPerson)!=null)
        {
            System.out.println("查到当前用户");
            model.addAttribute("msg1","该用户名已存在");
            return "quotienthtml/register";
        }
        String perms="";
        switch (departName)
        {
            case "养殖场":
                perms="login:farm1";
                break;
            case "屠宰场":
                perms="login:slaughter1";
                break;
            case "物流部门":
                perms="login:logistics1";
                break;
             default:
                    break;
        }
        System.out.println("提交的信息："+departPerson+" "+departPwd+" "+departName+" "+perms);
        Department department=new Department(departName,departPerson,departPwd,perms,null);
        departmentService.insertDepartment(department);
        return "index";
    }
    @RequestMapping("/find")
    public String tofind()
    {
        return "consumerhtml/consult";
    }

    // 跳转到登记牛只信息页面--登记牛只信息
    @GetMapping("/login/farmEnroll/cattleImform")
    public String cattleImform()
    {
        return "quotienthtml/farm/cattleImform";
    }

    @RequestMapping(value = {"/login/farmEnroll/cancel","/login/slaughterEnroll/cancel","/login/logisticsEnroll/cancel"})
    public String clickCancel()
    {
        return "quotienthtml/farm/quotientIndex";
    }

    //添加牛只信息的post请求
    @PostMapping("/login/farmEnroll/addCattle")
    public String addCattle(Integer cattleId, Integer operatorId, String cattleType,
                                String cattleGender, Date cattledate,Model model)
    {
        if(cattleId==null)
        {
            model.addAttribute("msges1","牛只编号不能为空");
            return "quotienthtml/farm/cattleImform";
        }
        Cattle cattle1=cattleServiceImp.findCattleById(cattleId);

       // System.out.println("从数据库中查询到的牛只是："+cattle1.toString());
        //先从数据库中查询是否有输入的牛只和养殖场，没有就向前端页面打印消息
        if(cattle1!=null)
        {
            System.out.println(cattleServiceImp.findCattleById(cattleId));
            model.addAttribute("msges1","牛只编号错误");
            return "quotienthtml/farm/cattleImform";
        }
        if(cattleType==null|| cattledate==null ||cattleGender==null)
        {
            model.addAttribute("msges3","输入不为空");
            return "quotienthtml/farm/cattleImform";
        }

        //日期类型转字符串借助SimpleDateFormat   new Date当前时间
        //int cattleId, int operatorId, String cattleType,
        //                            String cattleGender, String cattledate
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date outDate=null;



        //加密牛只信息得到哈希值
        String catttlesha=String.valueOf(cattleId)+String.valueOf(operatorId)+cattleType+cattleGender+sdf.format(cattledate);
        //对要添加牛只信息经过sha算法得到的hash值
        String shaCode=Hash.sha3(catttlesha);
        System.out.println("加密前的信息："+catttlesha+"\n加密后的hash:"+shaCode);


        //创建一个牛只对象
        Cattle cattle=new Cattle(cattleId,operatorId,cattleType,cattleGender,cattledate,shaCode);
        Cattle cattle2=new Cattle(cattleId,operatorId,cattleType,cattleGender,cattledate,outDate,shaCode);
        System.out.println("添加的牛只信息为："+cattle2);
        //加到数据库--->
         cattleServiceImp.saveCattle(cattle2);

        //添加到区块链里
        farmContractService.addCattle(cattle);


        //重定向到牛只信息查询页面
        return "redirect:/login/farmSearch/cattleFind";
    }


    //跳转到牛只信息查询页面
    @RequestMapping("/login/farmSearch/cattleFind")
    public String findCattle(Model model)
    {
        //从数据库中查询到所有的牛只信息
        Collection<Cattle> cattles=cattleServiceImp.findAllCattle();

        model.addAttribute("cattles",cattles);



        return  "quotienthtml/farm/cattleFind";
    }


    //跳转到添加养殖厂员工页面、跳转到屠宰场、物流部门员工信息登记
    @RequestMapping(value = {"/login/farmEnroll/workerImform", "/login/slaughterEnroll/slaughWorker" ,"/login/logisticsEnroll/workerImform"})
    public String toFramStaff()
    {
        return "quotienthtml/farm/farmStaffImform";
    }



    //处理添加养殖场员工\屠宰场员工\物流部门员工表单的post请求
    @PostMapping(value = {"/login/farmEnroll/addFramStaff",
            "/login/slaughterEnroll/addslaughterStaff" ,"/login/logisticsEnroll/addlogisticsStaff"})
    public String addFramStaff(Integer workerId, Integer workerPlantId,String workerName
    ,String workerPosition,String workerHealth ,Model model)
    {
        if(workerId==null )
        {
            model.addAttribute("msg1","该员工编号不能为空");
            return "quotienthtml/farm/farmStaffImform";
        }
        if( farmStaffServiceImp.findFarmStaffById(workerId)!=null)
        {
            model.addAttribute("msg1","该员工编号已存在");
            return "quotienthtml/farm/farmStaffImform";
        }

        if(workerPlantId==null || workerName==null)
        {
            model.addAttribute("msges3","输入不为空");
            return "quotienthtml/farm/farmStaffImform";
        }
        //如果从department表中查询不到就把错误信息写回页面
       Department department= departmentService.queryDepartmentById(workerPlantId);

        if(workerHealth==null || department==null)
        {
            model.addAttribute("msg","所在工厂编号错误");
            return "quotienthtml/farm/farmStaffImform";
        }


        //创建养殖场员工对象
        FarmStaff farmStaff1=new FarmStaff(workerId,workerPlantId,workerName,workerPosition,workerHealth);
        //添加到数据库里
        farmStaffServiceImp.saveFarmStaff(farmStaff1);
        if(department.getDepartName()=="屠宰场")
        {
            return "redirect:/login/slaughterSearch/slaughWorkerFind";
        }else if(department.getDepartName()=="养殖场")
        {
            return "redirect:/login/farmSearch/workerFind";
        }
        return "redirect:/login/logisticsSearch/workerFind";
    }

    //跳转到养殖场员工查询页面--->并在页面显示所有员工
    @RequestMapping(value={"/login/farmSearch/workerFind",
            "/login/slaughterSearch/slaughWorkerFind","/login/logisticsSearch/workerFind"})
    public String findFramStaff(Model model)
    {

      Collection<FarmStaff> farmStaffs= farmStaffServiceImp.findAllFarmStaff(PlantId);
        System.out.println("通过PlantId="+PlantId+" 查询到的员工有：");
        for (FarmStaff f:farmStaffs) {
            System.out.print(f.toString()+" ");
        }
      model.addAttribute("farmstaffs",farmStaffs);

        return "quotienthtml/farm/staffFind";
    }


    //跳转到添加养殖记录的页面
    @RequestMapping("/login/farmEnroll/feedRecordImform")
    public String tofeedRecord()
    {
        return "quotienthtml/farm/farmRecordImform";
    }



    //处理添加养殖记录表单的post请求
    @PostMapping("/login/farmEnroll/addFramRecord")
    public String addFramRecord(Integer farmId, Integer cattleId, Integer plantId, Integer enployeeId,
                                String isIn,String feedMed,Date operaterDate,String foodType,Model model)
    {

        System.out.println("isIn:"+isIn);

        if(farmId==null )
        {
            model.addAttribute("msges4","记录单编号不能为空");
            return "quotienthtml/farm/farmRecordImform";
        }

        //先从数据库中查询是否有输入的牛只和养殖场，没有就向前端页面打印消息
        if(farmRecordServiceImp.findFarmRecordById(farmId)!=null)
        {
            model.addAttribute("msges4","该记录单编号已存在");
            return "quotienthtml/farm/farmRecordImform";
        }

        Cattle cattle=cattleServiceImp.findCattleById(cattleId);

        if(cattleId==null || cattle==null)
        {
            model.addAttribute("msges1","牛只编号错误");
            return "quotienthtml/farm/farmRecordImform";
        }
        if(plantId==null || departmentService.queryDepartmentById(plantId)==null)
        {
            model.addAttribute("msges2","养殖场编号错误");
            return "quotienthtml/farm/farmRecordImform";
        }
        if(enployeeId==null || farmStaffServiceImp.findFarmStaffById(enployeeId)==null)
        {
            model.addAttribute("msges5","该员工错误");
            return "quotienthtml/farm/farmRecordImform";
        }

        if(feedMed==null|| operaterDate==null||foodType==null)
        {
            model.addAttribute("msges3","输入不为空");
            return "quotienthtml/farm/farmRecordImform";
        }
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //加密牛只信息得到哈希值
        String farmRecordsha=String.valueOf(farmId)+String.valueOf(cattleId)+String.valueOf(plantId)
                +String.valueOf(enployeeId)+feedMed+sdf.format(operaterDate)+foodType;
        //对要添加牛只信息经过sha算法得到的hash值
        String shaCode=Hash.sha3(farmRecordsha);
        System.out.println("加密前的信息："+farmRecordsha+"\n加密后的hash:"+shaCode);

        //生成一个FramRecord对象，给区块链存的
        FarmRecord farmRecord=new FarmRecord( farmId,  cattleId,  plantId,  enployeeId,
         feedMed, operaterDate, foodType,shaCode);

        //生成一个FramRecord对象，给数据库存的
        FarmRecord farmRecord1=new FarmRecord( farmId,  cattleId,  plantId,  enployeeId,
                feedMed, operaterDate, foodType,isIn,shaCode);

        System.out.println("farmRecord："+farmRecord.toString());
        System.out.println("farmRecord1："+farmRecord1.toString());
        System.out.println("isIn.equals(\"是\")："+isIn.equals("是"));
        //如果即将出栏就更新牛只信息
        if(isIn.equals("是")==true)
        {
            Date date=new Date();
            cattle.setOutDate(date);
            cattleServiceImp.updateCattle(cattle);
            System.out.println("更新牛只的信息："+cattle.toString());
        }


        //添加到数据库中
        farmRecordServiceImp.saveFarmRecord(farmRecord1);


        //添加到区块链中
        farmContractService.addFarmRecord(farmRecord);

        //重定向到显示养殖记录页面
        return "redirect:/login/farmSearch/feedRecordFind";

    }



    //跳转到养殖记录查询页面--->并在页面显示所有养殖记录/login/farmSearch/feedRecordFind
    @RequestMapping(value={"/login/farmSearch/feedRecordFind",
            "/login/slaughterSearch/feedRecordFind","/login/logisticsSearch/logistFind"})
    public String findFramRecord(Model model)
    {

        //养殖场记录单
        Collection<FarmRecord> farmRecords= farmRecordServiceImp.findAllFarmRecord();
        model.addAttribute("farmRecords",farmRecords);

        //屠宰场记录单
        Collection<SlaughterRecord> slaughterRecords= slaughterRecordServiceImp.findAllSlaughterRecord();
        model.addAttribute("slaughterRecords",slaughterRecords);

        //物流记录查询
        Collection<LogisticsRecord> logisticsRecords=logisticsServiceIml.findAllLogisticsRecord();
        model.addAttribute("logisticsRecords",logisticsRecords);


        return "quotienthtml/farm/recordFind";
    }



    //跳转到员工编辑页面
    @RequestMapping("/farmstaff/{workerId}")
    public String toWorkerEditor(@PathVariable("workerId") Integer workerId,Model model)
    {
        //直接跳转到员工添加信息页面，并把信息显示到输入框

        //查询出原来的数据并把信息显示到输入框/login/farmEnroll/updateFramStaff
        FarmStaff farmStaff=farmStaffServiceImp.findFarmStaffById(workerId);
        System.out.println(farmStaff.toString());
        model.addAttribute("farmStaff",farmStaff);

        return "quotienthtml/farm/farmStaffUpdate";

    }

    //编辑员工
    @RequestMapping(value = {"/login/farmEnroll/updateFramStaff",
    "/login/logisticsEnroll/updatelogisticsStaff","/login/slaughterEnroll/updateslaughterStaff"})
    public String workerEditor(FarmStaff farmStaff,Model model)
    {
        //如果从department表中查询不到就把错误信息写回页面
        Department department= departmentService.queryDepartmentById(farmStaff.getWorkerPlantId());


        //添加到数据库里,返回值是员工的工厂号
        farmStaffServiceImp.updateFarmStaffById(farmStaff);

        System.out.println(department.getDepartName()+" "+farmStaff.toString());
        if(department.getDepartName().equals("屠宰场"))
        {
            return "redirect:/login/slaughterSearch/slaughWorkerFind";
        }else if(department.getDepartName().equals("养殖场"))
        {
            return "redirect:/login/farmSearch/workerFind";
        }
        return "redirect:/login/logisticsSearch/workerFind";
    }

    //员工删除
    @RequestMapping("/delfarmstaff/{workerId}")
    public String workerDelete(@PathVariable("workerId") Integer workerId, Model model)
    {
        //通过PlantId查到该工厂

        //如果从department表中查询不到就把错误信息写回页面
        Department department= departmentService.queryDepartmentById(PlantId);

        farmStaffServiceImp.deleteFarmStaffById(workerId);
        System.out.println("已删除该员工");
        if(department.getDepartName().equals("屠宰场"))
        {
            return "redirect:/login/slaughterSearch/slaughWorkerFind";
        }else if(department.getDepartName().equals("养殖场"))
        {
            return "redirect:/login/farmSearch/workerFind";
        }
        return "redirect:/login/logisticsSearch/workerFind";

    }



}
