package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //开启 eureka client
@EnableFeignClients //开启feign
public class AutodeliverApplication8092 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8092.class,args);
    }
}
