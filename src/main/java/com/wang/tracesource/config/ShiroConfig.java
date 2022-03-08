package com.wang.tracesource.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author WY
 * @Date 2020/3/22 16:21
 * Version 1.0
 **/
@Configuration
public class ShiroConfig {
    //三大对象
    //
    //defaultWebSecurityManager方法里边要使用的名字，@Qualifier("securityManager")是具体引入的，securityManager是DefaultWebSecurityManager类型
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);


        //添加shiro的内置过滤器
        /*
         *anon:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须拥有记住我功能才能使用
         * perms:拥有某个资源的权限才能访问
         * role:拥有耨个角色权限才能访问
         */

//        //由于是链式的，所以new一个链式的hashmap(new LinkedHashMap<>())
        Map<String, String> filterMap=new LinkedHashMap<>();
//        //setFilterChainDefinitionMap:设置一个过滤器的链，通过map设置，函数参数传递map
//
         filterMap.put("/consumers","anon");//"/consumers"这个请求所有人都可以访问
        filterMap.put("/login/*","authc");//"/user/update"这个请求需要认证才可以访问

        //授权：正常情况下会跳转到未授权的页面
        //带了user:add字符串的用户才有权限访问
        filterMap.put("/login/farmEnroll/*","perms[login:farm1]");
        filterMap.put("/login/farmSearch/*","perms[login:farm1]");
        filterMap.put("/login/slaughterEnroll/*","perms[login:slaughter1]");
        filterMap.put("/login/slaughterSearch/*","perms[login:slaughter1]");
        filterMap.put("/login/logisticsEnroll/*","perms[login:logistics1]");
        filterMap.put("/login/logisticsSearch/*","perms[login:logistics1]");

        bean.setFilterChainDefinitionMap(filterMap);
//        //设置登陆页面请求
        bean.setLoginUrl("/quotients/tologin");
        //设置未授权的请求页面
        bean.setUnauthorizedUrl("/noauth");


        return bean;
    }


    //第二步：DefaultWebSecurityManager,因为该类要使用到realm对象，所以第一步定义realm对象
    //代码基本上是死的
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("departmentRealm") DepartmentRealm departmentRealm)
    {
        //有个接口叫做HelloInterface，然后有两个bean都实现了HelloInterface接口。用@Autowired注解和@Qualifier(“你要注入的bean的名称”)注解
        //如果我们只使用@Autowired注解，Spring就不知道到底要注入哪一个bean
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm，已经注入到springIOC容器了，但是使用不能直接用，需要传递参数
        securityManager.setRealm(departmentRealm);
        return securityManager;
    }



    //第一步：创建realm对象，需要自定义
    //注入springIOC容器，被spring托管，userRealm相当于是UserRealm的别名，后面访问只需要调用userRealm
    @Bean
    public DepartmentRealm departmentRealm()
    {
        return new DepartmentRealm();
    }

    //整合ShiroDialect:用来整和shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }


}

