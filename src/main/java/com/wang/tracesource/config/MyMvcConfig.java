package com.wang.tracesource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Author WY
 * @Date 2020/3/18 8:55
 * Version 1.0
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/consult.html").setViewName("consumerhtml/consult");
        registry.addViewController("/search1.html").setViewName("consumerhtml/informFind");
        registry.addViewController("/login.html").setViewName("quotienthtml/farm/quotientIndex");
        registry.addViewController("/consumer-informFind2").setViewName("consumerhtml/informFind2");

    }
}
