package com.wang.tracesource.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName web3jConfig
 * @Description TODO
 * @Author WY
 * @Date 2020/3/29 15:51
 * Version 1.0
 **/
@Configuration
public class web3jConfig {

    @Value("${web3j.client-address}")
    private String rpc;
    //用于HTTP连接的OKH


    @Bean
    public Web3j web3j()
    {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(30*10000,TimeUnit.MILLISECONDS);
        builder.writeTimeout(30*10000,TimeUnit.MILLISECONDS);
        builder.readTimeout(30*10000,TimeUnit.MILLISECONDS);
        OkHttpClient httpClient=builder.build();
        Web3j web3j=Web3j.build(new HttpService(rpc,httpClient,false));
        return web3j;
    }

    @Bean
    public Admin admin()
    {
        return Admin.build(new HttpService(rpc));
    }

}
