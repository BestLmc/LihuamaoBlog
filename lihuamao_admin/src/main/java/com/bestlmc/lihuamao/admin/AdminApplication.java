package com.bestlmc.lihuamao.admin;

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
        "com.bestlmc.lihuamao.admin.config",
        "com.bestlmc.lihuamao.admin.controller",
        "com.bestlmc.lihuamao.services.service",
        "com.bestlmc.lihuamao.commons.config",
        "com.bestlmc.lihuamao.admin.config"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
