package com.wang.tracesource.config;

import com.wang.tracesource.pojo.Department;
import com.wang.tracesource.service.DepartmentServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @ClassName DepartmentRealm
 * @Description TODO
 * @Author WY
 * @Date 2020/3/22 16:22
 * Version 1.0
 **/
//自定义UserRealm  需要继承extends AuthorizingRealm
public class DepartmentRealm extends AuthorizingRealm {

    @Autowired
    DepartmentServiceImpl departmentService;

    //授权
    //进入一些被拦截的页面时，就会被授权也就是会进入之歌方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了==》授权doGetAuthorizationInfo");

        //new一个授权的方法:代码是固定的
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //拿到当前登录的这个对象
        Subject subject=SecurityUtils.getSubject();
        Department currentDepartment=(Department)subject.getPrincipal();//从下面的认证方法中取出从数据库中核实2的user,本来时object，强转为User

        //设置当前用户的权限:是从数据库表中拿到的
        info.addStringPermission(currentDepartment.getPerms());
        System.out.println(currentDepartment.getPerms());

        //设置当前用户的角色
        info.addRole(currentDepartment.getPole());

        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了==》认证doGetAuthorizationInfo");
        //将参数的token转换为自己定义的token，也就是处理登录请求时拿到的令牌
        UsernamePasswordToken departToken=(UsernamePasswordToken) authenticationToken;//拿到了用户登录时提交的信息


        //连接真实的数据库==>传递参数是拿到令牌的用户名
        Department department= departmentService.queryDepartmentByName(departToken.getUsername());

        if(department==null)//用户名存在
        {
            return null;//UnknownAccountException
        }

        //密码认证，shiro自己做,也可以加密：MD5加密
        //三个参数：获取当前对象的认证，密码，认证名
        return new SimpleAuthenticationInfo(department,department.getDepartPwd(),"");


    }
}
