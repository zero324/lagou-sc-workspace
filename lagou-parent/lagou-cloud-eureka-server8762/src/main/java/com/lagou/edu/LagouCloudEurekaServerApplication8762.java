package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//声明本项目是一个eureka server服务
@EnableEurekaServer
public class LagouCloudEurekaServerApplication8762 {
    public static void main(String[] args) {
        SpringApplication.run(LagouCloudEurekaServerApplication8762.class,args);
    }
}
