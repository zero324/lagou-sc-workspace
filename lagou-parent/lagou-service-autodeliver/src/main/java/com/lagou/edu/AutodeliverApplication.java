package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AutodeliverApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication.class,args);
    }

    @Bean
    @LoadBalanced  //加这个注解是使用ribbon做负载均衡
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
