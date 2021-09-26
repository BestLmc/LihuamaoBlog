package com.bestlmc.lihuamao.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Description: 启动类
 * @Author bestlmc
 * @Date 2021/6/18 0:36
 */
@SpringBootApplication
//@EnableOpenApi
@ComponentScan(basePackages = {
        "com.bestlmc.lihuamao.api.admin",
        "com.bestlmc.lihuamao.biz.service",
        "com.bestlmc.lihuamao.biz.mapper",
        "com.bestlmc.lihuamao.commons.config",
        "com.bestlmc.lihuamao.api.config"})
public class APIApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIApplication.class,args);
    }
}
