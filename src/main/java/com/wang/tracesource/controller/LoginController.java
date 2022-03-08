package com.wang.tracesource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author WY
 * @Date 2020/4/10 16:44
 * Version 1.0
 **/
@Controller
public class LoginController {
    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized()
    {
        return "未经授权的页面无法访问";
    }

    @RequestMapping("/login/logout")
    public String tologout(HttpSession httpSession) {
        //session.invalidate()，是某一个用户调用的，比如说S1这个用户，
        // 调用了这个方法，那么，就只有s1用户的session 被删除，其他用户的session，跟s1没关系。
        httpSession.invalidate();
        return "index";
    }

    @RequestMapping("/consumer/logout")
    public String tologout2() {
        return "index";
    }

}
