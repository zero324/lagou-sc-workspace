package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启注册中心服务端
@EnableDiscoveryClient
//@EnableHystrix  开启hystrix熔断器
//@EnableCircuitBreaker  //开启熔断器
//@SpringCloudApplication 是 @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker 组合注解
public class AutodeliverApplication8094 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8094.class, args);
    }
}
