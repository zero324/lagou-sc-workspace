package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
//
//@EnableEurekaClient  eureka 专属的注解
@EnableDiscoveryClient  //spring cloud 通用的 注册中心客户端注解    spring cloud 在edgware 版本及其之后的  可以不添加注解
public class ResumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class,args);
    }
}
