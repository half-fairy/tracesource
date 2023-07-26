package com.wang.tracesource.controller;

import com.wang.tracesource.dao.User;
import com.wang.tracesource.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/userFind")
    public String findAllUser(Model model){
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser",allUser);
        return "userhtml/userFind";
    }

    //先进入信息录入页面
    @RequestMapping("/toUserAdd")
    public String toUserAdd(){
        return "userhtml/userAdd";
    }

    //添加用户请求
    @PostMapping("/userAdd")
    public String addUser(Integer id, String name, String  nickname, String sex, Integer age,
        String notes, Date createTime,Date endEditTime){
        User user=new User(id,name,nickname,sex,age,notes,createTime,endEditTime);
        if(id==null || userService.findUserById(id)!=null){
            LOGGER.error("该用户id有误，或者该用户不存在");
        }
    //写入数据库
    userService.addUser(user);
    return  "redirect:/userFind";
    }
    //跳转到员工编辑页面
    @RequestMapping("/user/update/{id}")
    public String toUserEdite(@PathVariable("id") Integer id,Model model)
    {

        User allUser=userService.findUserById(id);
        System.out.println(allUser.toString());
        model.addAttribute("allUser",allUser);

        return "userhtml/userUpdate";

    }

    //编辑员工
    @RequestMapping(value = {"/userUpdate"})
    public String updateUser(Integer id, String name, String  nickname, String sex, Integer age,
                             String notes, String createTime,String endEditTime){
        User user=new User(id,name,nickname,sex,age,notes,new Date(createTime),new Date(endEditTime));

        System.out.println("当前用户的信息为："+user);
         userService.updateUser(user);
        return "redirect:/userFind";
    }

    //删除员工信息
    @RequestMapping("/user/delete/{id}")
    public String toUserDelete(@PathVariable("id") Integer id,Model model)
    {

        userService.deleteUser(id);
        return "redirect:/userFind";

    }


    @GetMapping("/aa")
    public String aa(){
        return "clientCollect";
    }
}
